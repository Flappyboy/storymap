if (!CONTEXT_PATH) {
    var CONTEXT_PATH = "\/";
}

var STORY_MAP_ID = getUrlParam('id');
if (STORY_MAP_ID === null) {
    window.location.href = CONTEXT_PATH + "";
}

var boardActivities;
var boardSortableReleases;
var messagesDom;

//用于标记发光的空card，避免在拖动整个map后触发其click事件。
var lightDom;

var CART_ID_ATTR_NAME = 'cardid';

var storyMap = {
    activities: [
        {
            id: 0,
            title: 'a1',
            tasks: [
                {
                    id: 0,
                    title: 'a1-t1',
                },
                {
                    id: 1,
                    title: 'a1-t2',
                }
            ],
        },
        {
            id: 1,
            title: 'a3',
            tasks: [],
        },
        {
            id: 2,
            title: 'a2',
            tasks: [
                {
                    id: 2,
                    title: 'a2-t1',
                },
            ],
        },
    ],
    releases: [
        {
            id: 0,
            title: 'Release1',
            activities: [
                {
                    tasks: [
                        {
                            subtasks: [
                                {
                                    id: 0,
                                    title: 'a1-t1-s1-r1',
                                },
                                {
                                    id: 1,
                                    title: 'a1-t1-s2-r1',
                                }
                            ],
                        },
                        {
                            subtasks: [
                                {
                                    id: 2,
                                    title: 'a1-t1-s1-r1',
                                },
                            ],
                        },
                    ]
                },
                {
                    tasks: []
                },
                {
                    tasks: [
                        {
                            subtasks: [
                                {
                                    id: 3,
                                    title: 'a2-t1-s1-r1',
                                },
                            ],
                        },
                    ]
                },
            ]
        },
        {
            id: 1,
            title: 'Release2',
            activities: [
                {
                    tasks: [
                        {
                            subtasks: [
                                {
                                    id: 4,
                                    title: 'a1-t1-s1-r2',
                                },
                                {
                                    id: 5,
                                    title: 'a1-t1-s2-r2',
                                }
                            ],
                        },
                        {
                            subtasks: [],
                        },
                    ]
                },

                {
                    tasks: []
                },
                {
                    tasks: [
                        {
                            subtasks: [
                                {
                                    id: 7,
                                    title: 'a2-t1-s1-r2',
                                },
                            ],
                        },
                    ]
                },
            ]
        },
    ],
};

var model = {
    "status": 0, "message": "success", "result": {
        "id": 1,
        "title": "story map",
        "activities": [{"id": 0, "title": "activity 0", "tasks": []}, {
            "id": 1,
            "title": "activity 1",
            "tasks": [{
                "id": 0,
                "title": "task 0",
                "subtasks": [{"id": 0, "title": "subtask 0", "releaseId": 0}, {
                    "id": 1,
                    "title": "subtask 1",
                    "releaseId": 0
                }, {"id": 2, "title": "subtask 2", "releaseId": 1}, {
                    "id": 3,
                    "title": "subtask 3",
                    "releaseId": 0
                }, {"id": 4, "title": "subtask 4", "releaseId": 0}]
            }, {"id": 1, "title": "task 1", "subtasks": []}]
        }, {
            "id": 2,
            "title": "activity 2",
            "tasks": [{
                "id": 2,
                "title": "task 2",
                "subtasks": [{"id": 5, "title": "subtask 5", "releaseId": 1}, {
                    "id": 6,
                    "title": "subtask 6",
                    "releaseId": 0
                }, {"id": 7, "title": "subtask 7", "releaseId": 1}, {
                    "id": 8,
                    "title": "subtask 8",
                    "releaseId": 0
                }, {"id": 9, "title": "subtask 9", "releaseId": 1}]
            }, {
                "id": 3,
                "title": "task 3",
                "subtasks": [{"id": 10, "title": "subtask 10", "releaseId": 1}, {
                    "id": 11,
                    "title": "subtask 11",
                    "releaseId": 0
                }, {"id": 12, "title": "subtask 12", "releaseId": 0}]
            }, {
                "id": 4,
                "title": "task 4",
                "subtasks": [{"id": 13, "title": "subtask 13", "releaseId": 1}, {
                    "id": 14,
                    "title": "subtask 14",
                    "releaseId": 0
                }, {"id": 15, "title": "subtask 15", "releaseId": 0}]
            }]
        }],
        "releases": [{
            "id": 0,
            "title": "Release1",
            "activities": [{"id": 0, "title": "activity 0", "tasks": []}, {
                "id": 1,
                "title": "activity 1",
                "tasks": [{
                    "id": 0,
                    "title": "task 0",
                    "subtasks": [{"id": 0, "title": "subtask 0", "releaseId": 0}, {
                        "id": 1,
                        "title": "subtask 1",
                        "releaseId": 0
                    }, {"id": 3, "title": "subtask 3", "releaseId": 0}, {"id": 4, "title": "subtask 4", "releaseId": 0}]
                }, {"id": 1, "title": "task 1", "subtasks": []}]
            }, {
                "id": 2,
                "title": "activity 2",
                "tasks": [{
                    "id": 2,
                    "title": "task 2",
                    "subtasks": [{"id": 6, "title": "subtask 6", "releaseId": 0}, {
                        "id": 8,
                        "title": "subtask 8",
                        "releaseId": 0
                    }]
                }, {
                    "id": 3,
                    "title": "task 3",
                    "subtasks": [{"id": 11, "title": "subtask 11", "releaseId": 0}, {
                        "id": 12,
                        "title": "subtask 12",
                        "releaseId": 0
                    }]
                }, {
                    "id": 4,
                    "title": "task 4",
                    "subtasks": [{"id": 14, "title": "subtask 14", "releaseId": 0}, {
                        "id": 15,
                        "title": "subtask 15",
                        "releaseId": 0
                    }]
                }]
            }]
        }, {
            "id": 1,
            "title": "Release2",
            "activities": [{"id": 0, "title": "activity 0", "tasks": []}, {
                "id": 1,
                "title": "activity 1",
                "tasks": [{
                    "id": 0,
                    "title": "task 0",
                    "subtasks": [{"id": 2, "title": "subtask 2", "releaseId": 1}]
                }, {"id": 1, "title": "task 1", "subtasks": []}]
            }, {
                "id": 2,
                "title": "activity 2",
                "tasks": [{
                    "id": 2,
                    "title": "task 2",
                    "subtasks": [{"id": 5, "title": "subtask 5", "releaseId": 1}, {
                        "id": 7,
                        "title": "subtask 7",
                        "releaseId": 1
                    }, {"id": 9, "title": "subtask 9", "releaseId": 1}]
                }, {
                    "id": 3,
                    "title": "task 3",
                    "subtasks": [{"id": 10, "title": "subtask 10", "releaseId": 1}]
                }, {"id": 4, "title": "task 4", "subtasks": [{"id": 13, "title": "subtask 13", "releaseId": 1}]}]
            }]
        }]
    }
};

storyMap = model.result;


var zoom = 21; // 1 - 40
var zoomList = '';

for (var i = 0; i < 40; i++) {
    zoomList = zoomList + 'board-zoom-' + (i + 1) + ' ';
}

// 请求的基本url
var apiBase = CONTEXT_PATH + 'mock';
var apiBase = CONTEXT_PATH + 'api';

$(function () {
    if (STORY_MAP_ID === null)
        return;
    $.getJSON(apiBase + "/storymap/" + STORY_MAP_ID, function (data, status) {
        console.log('status: ' + status);
        if (status == "success") {
            if (data.status == 0) {
                storyMap = data.result;
                // 预处理storyMap对象
                for (var i = 0; i < storyMap.activities.length; i++) {
                    var tasks = storyMap.activities[i].tasks;
                    for (var j = 0; j < tasks.length; j++) {
                        tasks[j].task = [];
                    }
                }
                init(storyMap);
            }
        }
    });
});

function init(storyMap) {
    $("#board").draggable(
        {
            distance: 5,
            scroll: false,
            start: function () {

            },
            drag: function () {
            },
            stop: function () {
                if (lightDom) {
                    lightDom.removeClass('board-light-yellow board-light-blue board-light-green');
                }
            }
        }
    );
    $("#right-content").bind("mousewheel",
        function (event, delta, deltaX, deltaY) {
            console.log(delta, deltaX, deltaY);
            if (delta < 0) {
                if (zoom > 1) {
                    zoom--;
                    $("#right-content").removeClass(zoomList);
                    $("#right-content").addClass("board-zoom-" + zoom);
                }
            } else {
                if (zoom < 40) {
                    zoom++;
                    $("#right-content").removeClass(zoomList);
                    $("#right-content").addClass("board-zoom-" + zoom);
                }
            }

            console.log(zoomList)
            return false;
        });

    boardActivities = $("#board-activities");
    boardSortableReleases = $("#board-sortable-releases");
    messagesDom = $('#messagebox');
    var activities = storyMap.activities;
    activities.forEach(function (activity, i) {
        boardActivities.append(newBoardActivity(activity));
    });
    var releases = storyMap.releases;
    releases.forEach(function (release, i) {
        boardSortableReleases.append(newReleaseWithSubstasks(release));
    });
}

function addRelease() {
    console.log('add release');
    var release = {
        title: 'Release' + (boardSortableReleases.children().length + 1),
        activities: storyMap.activities,
    }
    var element = newReleaseWithSubstasks(release);
    boardSortableReleases.append(element);
    requestAddRelease(release, boardSortableReleases.children().length, element);
}


/**
 *
 * @param activityIndex 要添加的activity index 0-n
 */
function addActivityToReleases(activityIndex, activity) {
    var releases = boardSortableReleases.children();
    if (!activity) {
        activity = {
            tasks: [],
        };
    }
    for (var i = 1; i <= releases.length; i++) {
        var release = boardSortableReleases.children(':nth-child(' + i + ')');
        if (activityIndex === 0) {
            release.find('.board-subtasks-activities').append(newBoardSubTaskActivity(activity));
        } else {
            var activityDom = release.find('.board-subtasks-activities').children(':nth-child(' + (activityIndex) + ')');
            activityDom.after(newBoardSubTaskActivity(activity));
        }
    }
}

function delActivityFromReleases(activityIndex) {
    var releases = boardSortableReleases.children();
    for (var i = 1; i <= releases.length; i++) {
        var release = boardSortableReleases.children(':nth-child(' + i + ')');
        var activityDom = release.find('.board-subtasks-activities').children(':nth-child(' + (activityIndex + 1) + ')');
        activityDom.remove();
    }
}

/**
 *
 * @param activityIndex 所以加入的activity index 0-n
 * @param taskIndex 最终要添加的task的index 0-m
 */
function addTaskToReleases(activityIndex, taskIndex, task) {
    var releases = boardSortableReleases.children();
    if (!task) {
        task = {
            subtasks: [],
        };
    }
    for (var i = 1; i <= releases.length; i++) {
        var release = boardSortableReleases.children(':nth-child(' + i + ')');
        var activity = release.find('.board-subtasks-activities').children(':nth-child(' + (activityIndex + 1) + ')');
        if (taskIndex === 0) {
            activity.children().append(newBoardSubTaskTask(task));
        } else {
            var taskDom = activity.children().children(':nth-child(' + (taskIndex + 1 - 1) + ')');
            taskDom.after(newBoardSubTaskTask(task));
        }
    }
}

function delTaskToReleases(activityIndex, taskIndex) {
    var releases = boardSortableReleases.children();
    for (var i = 1; i <= releases.length; i++) {
        var release = boardSortableReleases.children(':nth-child(' + i + ')');
        var activity = release.find('.board-subtasks-activities').children(':nth-child(' + (activityIndex + 1) + ')');
        var taskDom = activity.children().children(':nth-child(' + (taskIndex + 1) + ')');
        taskDom.remove();
    }
}

function newBoardActivity(activity, insertIndex) {
    var element = $('<li class="board-activity"></li>');
    element.append(newPersons());
    element.append(newBoardActivityCard(activity));
    element.append(newBoardTasksOuter(activity.tasks));
    if (activity && 'id' in activity) {
        console.log('init activity ' + activity.id);
        element.attr(CART_ID_ATTR_NAME, activity.id);
    } else if (insertIndex != undefined) {
        requestAddActivity(activity, insertIndex, element);
    } else {
        console.error('newBoardActivity error');
    }
    return element;
}

function newPersons() {
    $('<div class="personas-wrapper"></div>');
}

function newBoardActivityCard(activity) {

    var nextBottomCallback = function (e) {
        // TODO
    };
    var nextRightCallback = function (e) {
        var activityIndex = e.parents('.board-activity').prevAll().length;
        var activity = {
            title: '',
            tasks: [],
            preDom: e.parent(),
        };
        var newElement = newBoardActivity(activity, activityIndex + 1);
        e.parents('.board-activity').after(newElement);
        console.log(activityIndex);
        addActivityToReleases(activityIndex + 1, activity);
    };
    var closeCallback = function (e) {
        var activityIndex = e.parents('.board-activity').prevAll().length;
        var id = e.parents('.board-activity').attr(CART_ID_ATTR_NAME);
        if (id == undefined) {
            addMessage(MESSAGE_WAIT_DELETE_COMPLETE, MESSAGE_WAIT_DELETE_COMPLETE_TYPE, MESSAGE_WAIT_DELETE_COMPLETE_LEVEL);
            return;
        }
        requestDelActivity(id, activityIndex);

        delActivityFromReleases(activityIndex);
        e.parents('.board-activity').remove();
        if (boardActivities.children().length === 0) {
            var activity = {
                title: '',
                tasks: [],
            };
            boardActivities.append(newBoardActivity(activity, 0));
            addActivityToReleases(0, activity);
        }
    };
    var element = newBoardCard($('<li></li>'), activity, closeCallback, null, nextRightCallback);
    element.addClass('board-activity-card');
    element.addClass('board-card-color-blue');
    element.attr('cardtype', 'activity');
    return element;
}

function newBoardTasksOuter(tasks) {
    var element = $('<div class="board-tasks-outer"></div>');
    element.append(newBoardTasks(tasks));
    return element;
}

function newBoardTasks(tasks) {
    var element = $('<ul class="board-tasks ui-sortable"></ul>');
    element.sortable({
        connectWith: ".ui-sortable",
        distance: 5,
    });
    if (tasks) {
        tasks.forEach(function (task, i) {
            element.append(newBoardTaskCard(task));
        })
    }
    element.hover(function () {
        if ($(this).children().length > 0)
            return;
        $(this).addClass('board-light-green');
        lightDom = $(this);
    }, function () {
        $(this).removeClass('board-light-green');
        lightDom = null;
    });
    element.click(function () {
        if (!$(this).hasClass('board-light-green') || $(this).children().length > 0) {
            if ($(this).children().length === 0) {
                $(this).addClass('board-light-green');
                lightDom = $(this);
            }
            return;
        }
        var task = {
            title: '',
            subtasks: [],
        }
        var activityIndex = $(this).parents('.board-activity').prevAll().length;
        $(this).append(newBoardTaskCard(task, $(this).parents('.board-activity'), 0));
        $(this).removeClass('board-light-green');

        console.log('activityIndex ' + activityIndex);
        addTaskToReleases(activityIndex, 0, task);
    });
    return element;
}

function newBoardTaskCard(task, activityDom, insertIndex) {
    var nextBottomCallback = function (e) {
        // TODO
    };
    var nextRightCallback = function (e) {
        var task = {
            title: '',
            subtasks: []
        };
        var activityIndex = e.parents('.board-activity').prevAll().length;
        var taskIndex = e.parent().prevAll().length;

        var newCard = newBoardTaskCard(task, e.parents('.board-activity'), taskIndex + 1);
        e.parent().after(newCard);

        console.log('taskIndex ' + taskIndex);
        console.log('activityIndex ' + activityIndex);
        addTaskToReleases(activityIndex, taskIndex + 1, task);
    };
    var closeCallback = function (e) {
        var id = e.parent().attr(CART_ID_ATTR_NAME);
        if (id == undefined) {
            addMessage(MESSAGE_WAIT_DELETE_COMPLETE, MESSAGE_WAIT_DELETE_COMPLETE_TYPE, MESSAGE_WAIT_DELETE_COMPLETE_LEVEL);
            return;
        }
        var taskIndex = e.parent().prevAll().length;
        var activityIndex = e.parents('.board-activity').prevAll().length;
        console.log('taskIndex ' + taskIndex);
        console.log('activityIndex ' + activityIndex);
        delTaskToReleases(activityIndex, taskIndex);


        requestDelTask(id, activityIndex, taskIndex);

        e.parent().remove();
    };
    var element = newBoardCard($('<li></li>'), task, closeCallback, null, nextRightCallback);
    element.addClass('board-task');
    element.addClass('board-task-card');
    element.addClass('board-card-color-green');
    element.attr('cardtype', 'task');

    if (task && 'id' in task) {
        console.log('init task ' + task.id);
        element.attr(CART_ID_ATTR_NAME, task.id);
    } else if (activityDom != undefined && insertIndex != undefined) {
        requestAddTask(task, activityDom, insertIndex, element);
    } else {
        console.error('newBoardTaskCard error');
    }
    return element;
}

function newReleaseWithSubstasks(release) {
    var element = $('<div class="release-with-subtasks"></div>');
    element.append(newBoardRelease(release));
    element.append($('<div></div>').append(newBoardSubtaskActivities(release.activities)));
    if (release && 'id' in release)
        element.attr(CART_ID_ATTR_NAME, release.id);
    return element;
}

function newBoardRelease(release) {
    var element = $('<div class="board-release"></div>');
    element.append(newBoardReleaseRelative(release));
    return element;
}

function newBoardReleaseRelative(release) {
    var element = $('<div class="board-release-relative"></div>');
    element.append(newDropdownMenu(release));

    return element;
}


function newDropdownMenu(release) {
    var element = $('<div class="dropdown-menu"></div>');
    element.append(newBoardReleasePrefix(release));
    element.append(newBoardReleaseName(release));
    element.append(newReleaseDel(release));
    return element;
}

function newReleaseDel(release) {
    var element = $('<span class="board-release-del"></span>');
    element.click(function () {
        var e = $(this).parents('.release-with-subtasks');
        var id = e.attr(CART_ID_ATTR_NAME);
        if (id == undefined) {
            addMessage(MESSAGE_WAIT_DELETE_COMPLETE, MESSAGE_WAIT_DELETE_COMPLETE_TYPE, MESSAGE_WAIT_DELETE_COMPLETE_LEVEL);
            return;
        }
        var delIndex = e.prevAll().length;
        e.remove();
        requestDelRelease(id, delIndex);
    });
    return element;
}

function newBoardReleasePrefix(release) {
    var element = $('<span class="board-release-prefix"></span>');
    return element;
}

function newBoardReleaseName(release) {
    var element = $('<span class="board-release-name">' + release.title + '</span>');
    return element;
}

function newBoardSubtaskActivities(activities) {
    var element = $('<ul class="board-subtasks-activities data-release-content"></ul>');
    if (activities) {
        activities.forEach(function (activity, i) {
            element.append(newBoardSubTaskActivity(activity));
        })
    }
    return element;
}

function newBoardSubTaskActivity(activity) {
    var element = $('<li class="board-activity"></li>');
    element.append(newBoardSubtaskTasks(activity.tasks));
    return element;
}

function newBoardSubtaskTasks(tasks) {
    var element = $('<ul class="board-tasks"></ul>');
    if (tasks) {
        tasks.forEach(function (task, i) {
            element.append(newBoardSubTaskTask(task));
        })
    }
    return element;
}

function newBoardSubTaskTask(task) {
    var element = $('<li class="board-task"></li>');
    element.append(newBoardSubTasks(task.subtasks));
    return element;
}

function newBoardSubTasks(subtasks) {
    var element = $('<ul class="board-subtasks ui-sortable"></ul>');
    element.sortable({
        connectWith: ".ui-sortable",
        distance: 5,
    });
    if (subtasks) {
        subtasks.forEach(function (subtask, i) {
            element.append(newBoardSubtaskCard(subtask));
        })
    }
    element.hover(function () {
        if ($(this).children().length > 0)
            return;
        $(this).addClass('board-light-yellow');
        lightDom = $(this);
    }, function () {
        $(this).removeClass('board-light-yellow');
        lightDom = null;
    });
    element.click(function () {
        if (!$(this).hasClass('board-light-yellow') || $(this).children().length > 0) {
            if ($(this).children().length === 0) {
                $(this).addClass('board-light-yellow');
                lightDom = $(this);
            }
            return;
        }
        var subtask = {
            title: '',
        };
        var activityIndex = $(this).parents('.board-activity').prevAll().length;
        var taskIndex = $(this).parents('.board-task').prevAll().length;
        var activityDom = boardActivities.children(':nth-child(' + (activityIndex + 1) + ')');
        var taskDom = activityDom.find('.board-tasks').children(':nth-child(' + (taskIndex + 1) + ')');
        var releaseDom = $(this).parents('.release-with-subtasks');
        $(this).append(newBoardSubtaskCard(subtask, activityDom, taskDom, releaseDom, 0));
        $(this).removeClass('board-light-yellow');
    });
    return element;
}

function newBoardSubtaskCard(subtask, activityDom, taskDom, releaseDom, insertIndex) {
    var closeCallBack = function (e) {
        var id = e.parent().attr(CART_ID_ATTR_NAME);
        if (id == undefined) {
            addMessage(MESSAGE_WAIT_DELETE_COMPLETE, MESSAGE_WAIT_DELETE_COMPLETE_TYPE, MESSAGE_WAIT_DELETE_COMPLETE_LEVEL);
            return;
        }
        var activityIndex = e.parents('.board-activity').prevAll().length;
        var taskIndex = e.parents('.board-task').prevAll().length;
        var delIndex = e.parent().prevAll().length;
        var releaseIndex = e.parents('.release-with-subtasks').prevAll().length;

        requestDelSubtask(id, activityIndex, taskIndex, releaseIndex, delIndex);
        e.parent().remove();
    };
    var nextBottomCallBack = function (e) {
        var subtask = {
            title: '',
        };
        var activityIndex = e.parents('.board-activity').prevAll().length;
        var taskIndex = e.parents('.board-task').prevAll().length;
        var activityDom = boardActivities.children(':nth-child(' + (activityIndex + 1) + ')');
        var taskDom = activityDom.find('.board-tasks').children(':nth-child(' + (taskIndex + 1) + ')');
        var releaseDom = e.parents('.release-with-subtasks');
        var insertIndex = e.parent().prevAll().length;
        var newCard = newBoardSubtaskCard(subtask, activityDom, taskDom, releaseDom, insertIndex + 1);
        e.parent().after(newCard);
    };
    var element = newBoardCard($('<li></li>'), subtask, closeCallBack, nextBottomCallBack);
    element.addClass('board-subtask-card');
    element.addClass('board-card-color-yellow');
    element.attr('cardtype', 'activity');
    if (subtask && 'id' in subtask) {
        console.log('init subtask ' + subtask.id);
        element.attr(CART_ID_ATTR_NAME, subtask.id);
    } else if (activityDom != undefined && insertIndex != undefined && releaseDom != undefined && taskDom != undefined) {
        requestAddSubtask(subtask, activityDom, taskDom, releaseDom, insertIndex, element);
    } else {
        console.error('newBoardTaskCard error');
    }
    return element;
}

function newBoardCard(element, card, closeCallback, nextBottomCallback, nextRightCallback) {
    if (card && 'title' in card && card.title !== '') {
        element.append(newCardTitleText(card.title));
        element.append(newCardEmptyTitle().hide());
    } else {
        element.append(newCardTitleText().hide());
        element.append(newCardEmptyTitle().show());
    }
    element.append(newCardTitleEditor().hide());
    if (closeCallback) {
        element.append(newBoardCardBlockClose());
        element.append(newBoardCardClose());
        element.children('.board-card-close').click(function () {
            closeCallback($(this));
        });
    }
    if (nextBottomCallback) {
        element.append(newBoardCardBlockBottom());
        element.append(newBoardCardNextBottom());
        element.children('.board-card-next-bottom').click(function () {
            nextBottomCallback($(this));
        });
    }
    if (nextRightCallback) {
        element.append(newBoardCardBlockRight());
        element.append(newBoardCardNextRight());
        element.children('.board-card-next-right').click(function () {
            nextRightCallback($(this));
        });
    }
    element.mouseleave(function () {
        $(this).children('.board-card-next-bottom').slideUp(50);
        $(this).children('.board-card-next-right').fadeOut(50);
        $(this).children('.board-card-close').fadeOut(50);
        // console.log("leave card")
    });
    return element;
}

function newBoardCardNextBottom() {
    var element = $('<span class="board-card-next-bottom" style="display: none"><img src="' + CONTEXT_PATH + 'img/story-map/icon_arrow_down.png"></span>');
    element.mouseleave(function () {
        $(this).slideUp(50);
        // console.log("leave bottom")
    });
    return element;
}

function newBoardCardBlockBottom() {
    var element = $('<span class="board-card-block-bottom"></span>');
    element.mouseenter(function () {
        $(this).siblings('.board-card-next-bottom').slideDown(50);
        // console.log("enter")
    });
    return element;
}

function newBoardCardNextRight() {

    var element = $('<span class="board-card-next-right" style="display: none"><img src="' + CONTEXT_PATH + 'img/story-map/icon_arrow_right.png"></span>');
    element.mouseleave(function () {
        $(this).fadeOut(50);
        // console.log("leave right")
    });
    return element;
}

function newBoardCardBlockRight() {
    var element = $('<span class="board-card-block-right"></span>');
    element.mouseenter(function () {
        $(this).siblings('.board-card-next-right').fadeIn(50);
        // console.log("enter")
    });
    return element;
}

function newBoardCardBlockClose() {
    var element = $('<span class="board-card-block-close" ></span>');
    element.mouseenter(function () {
        $(this).siblings('.board-card-close').fadeIn(50);
        // console.log("enter")
    });
    return element;
}

function newBoardCardClose() {
    var element = $('<span class="board-card-close" style="display: none"><img src="' + CONTEXT_PATH + 'img/story-map/icon_close.png"></span>');
    element.mouseleave(function () {
        $(this).fadeOut(50);
        // console.log("leave close")
    });
    return element;
}

function newCardTitleText(title) {
    var element = $('<span class="board-card-title-text">' + (title ? title : '') + '</span>');
    element.click(function () {
        var textarea = $(this).siblings("textarea");
        textarea.val($(this).text());
        $(this).hide();
        textarea.show();
        textarea.focus();
    });
    element.mouseenter(function () {
        $(this).siblings('.board-card-next-bottom').slideUp(50);
        // console.log("enter")
    });
    return element;
}

function newCardEmptyTitle() {
    var element = $('<span class="board-card-empty-title">Empty card</span>');
    element.click(function () {
        var textarea = $(this).siblings("textarea");
        $(this).hide();
        textarea.show();

        textarea.focus();
    });
    element.mouseenter(function () {
        $(this).siblings('.board-card-next-bottom').slideUp(50);
        // console.log("enter")
    });
    return element;
}

function newCardTitleEditor() {
    var element = $('<textarea type="text" class="board-card-title-editor" data-editmode="false"></textarea>');
    element.mouseenter(function () {
        $(this).siblings('.board-card-next-bottom').slideUp(50);
        // console.log("enter")
    });
    element.blur(function () {
        var textBoard = $(this).siblings('.board-card-title-text');
        textBoard.show();
        var value = $(this).val();
        var text = textBoard.text();
        $(this).hide();
        if (value == text) {
            return;
        }
        textBoard.text(value);
        if ($(this).parent().hasClass('board-subtask-card')) {
            var dom = $(this).parent();
            requestChangeTitleSubtask(dom, value, dom.prevAll().length);
        } else if ($(this).parent().hasClass('board-task-card')) {
            var dom = $(this).parent();
            requestChangeTitleTask(dom, value, dom.prevAll().length);
        } else if ($(this).parent().hasClass('board-activity-card')) {
            var dom = $(this).parents('.board-activity');
            requestChangeTitleActivity(dom, value, dom.prevAll().length);
        } else {
            console.error('文本编辑出错！！！');
        }
    });
    return element;
}

function addMessage(msg, type, level) {
    var max = parseInt($('#operate').width() / 18);
    var rs;
    if (msg < max) {
        rs = [];
        rs[0] = msg;
        alert(rs);
    } else {
        // var reg=/.{5}/g;
        var reg = eval("/.{" + max + "}/g");
        rs = msg.match(reg);//注意如果s的长度小于4,那么rs=null
        rs.push(msg.substring(rs.join('').length));
        console.log(rs);
    }
    var div = $('<div></div>');
    rs.forEach(function (value) {
        div.append(newMessage(value, type, level));
    });
    var br = $('<br/>')
    messagesDom.prepend(br);
    messagesDom.prepend(div);
    setTimeout(function () {
        div.fadeOut(800, function () {
            div.remove();
            br.remove();
        });
    }, 3000);
}

var MSG_TYPE_DEFAULT = 'default';
var MSG_TYPE_PRIMARY = 'primary';
var MSG_TYPE_SUCCESS = 'success';
var MSG_TYPE_INFO = 'info';
var MSG_TYPE_WARNING = 'warning';
var MSG_TYPE_DANGER = 'danger';

/**
 *
 * @param msg
 * @param type default primary success info warning danger
 * @param level 1-6
 */
function newMessage(msg, type, level) {
    var h = $('<h' + level + '></h' + level + '>');
    h.append($('<span class="label label-' + type + '">' + msg + '</span>'));
    return h;
}

function requestBaseDel(api, e, dataCallback) {
    var func = function (callback) {
        var postdata = dataCallback();
        if(postdata == null)
            return;
        postdata.storyMapId = STORY_MAP_ID;
        $.ajax({
                url: apiBase + api,
                data: postdata,
                type: "DELETE",
                headers: headers,
                success: function (data, status) {
                    console.log('data: ' + data + ' status: ' + status);
                    if (status == "success") {
                        console.log(data);
                    }
                },
                dataType: "json"
            }
        ).fail(function (e) {
            console.log(e);
        }).always(callback);
    };
    funcQueue.push(func);
}

function requestDelActivity(id, delIndex) {
    storyMap.activities.splice(delIndex, 0);
    for (var i = 0; i < storyMap.releases.length; i++) {
        storyMap.releases[i].activities.splice(delIndex, 1);
    }
    console.log('del activity card id: ' + id + ' index:' + delIndex);
    requestBaseAdd('/activity', e, function () {
        var postdata = {
            id: id
        };
        return postdata;
    });
}

function requestDelTask(id, activityIndex, delIndex) {
    storyMap.activities[activityIndex].tasks.splice(delIndex, 0);
    for (var i = 0; i < storyMap.releases.length; i++) {
        storyMap.releases[i].activities[activityIndex].tasks.splice(delIndex, 0);
    }
    console.log('del task card id: ' + id + ' index:' + delIndex + ' aIndex:' + activityIndex);
    requestBaseAdd('/activity', e, function () {
        var postdata = {
            id: id
        };
        return postdata;
    });
}

function requestDelSubtask(id, activityIndex, taskIndex, releaseIndex, delIndex) {
    console.log('del subtask card id: ' + id + ' index:' + delIndex + ' aIndex:' + activityIndex + ' tIndex:' + taskIndex + ' rIndex:' + releaseIndex);
    requestBaseAdd('/activity', e, function () {
        var postdata = {
            id: id
        };
        return postdata;
    });
}

function requestDelRelease(id, delIndex) {
    storyMap.releases.splice(delIndex, 0);
    console.log('del subtask card id: ' + id + ' index:' + delIndex);
    requestBaseAdd('/activity', e, function () {
        var postdata = {
            id: id
        };
        return postdata;
    });
}

function requestBaseAdd(api, e, dataCallback) {
    var func = function (callback) {
        var postdata = dataCallback();
        if(postdata == null)
            return;
        postdata.storyMapId = STORY_MAP_ID;
        $.ajax({
                url: apiBase + api,
                data: postdata,
                type: "POST",
                headers: headers,
                success: function (data, status) {
                    console.log('data: ' + data + ' status: ' + status);
                    if (status == "success") {
                        if (data.status == 0) {
                            result = data.result;
                            e.attr(CART_ID_ATTR_NAME, result.id);
                        }
                    }
                },
                dataType: "json"
            }
        ).fail(function (e) {
            console.log(e);
        }).always(callback);
    };
    funcQueue.push(func);
}

function requestAddActivity(item, insertIndex, e) {
    storyMap.activities.splice(insertIndex, 0, item);
    for (var i = 0; i < storyMap.releases.length; i++) {
        storyMap.releases[i].activities.splice(insertIndex, 0, item);
    }
    console.log('insert activity card index:' + insertIndex);

    requestBaseAdd('/activity', e, function () {
        var postdata = {
            title: item.title,
            order: insertIndex,
        };
        return postdata;
    });
}

function requestAddTask(item, activityDom, insertIndex, e) {
    var activityIndex = activityDom.prevAll().length;
    storyMap.activities[activityIndex].tasks.splice(insertIndex, 0, item);
    for (var i = 0; i < storyMap.releases.length; i++) {
        storyMap.releases[i].activities[activityIndex].tasks.splice(insertIndex, 0, item);
    }
    console.log('insert task card index:' + insertIndex + ' aID:' + activityDom.attr(CART_ID_ATTR_NAME));
    requestBaseAdd('/task', e, function () {
        var postdata = {
            title: item.title,
            order: insertIndex,
            activityId: activityDom.attr(CART_ID_ATTR_NAME),
        };
        if(postdata.activityId==undefined){
            console.error('insert task card index:' + insertIndex + ' aID:' + activityDom.attr(CART_ID_ATTR_NAME));
        }
        return postdata;
    });
}

function requestAddSubtask(item, activityDom, taskDom, releaseDom, insertIndex, e) {
    console.log('insert subtask card index:' + insertIndex + ' aID:' + activityDom.attr(CART_ID_ATTR_NAME) + ' tID:' + taskDom.attr(CART_ID_ATTR_NAME) + ' rID:' + releaseDom.attr(CART_ID_ATTR_NAME));
    var postdata = {
        title: item.title,
        order: insertIndex,
    };
    requestBaseAdd('/subtask', e, function () {
        var postdata = {
            title: item.title,
            order: insertIndex,
            activityId: activityDom.attr(CART_ID_ATTR_NAME),
            taskId: taskDom.attr(CART_ID_ATTR_NAME),
            releaseId: releaseDom.attr(CART_ID_ATTR_NAME),
        };
        if(postdata.activityId==undefined || postdata.taskId==undefined || postdata.releaseId==undefined){
            console.error('insert subtask card index:' + insertIndex + ' aID:' + activityDom.attr(CART_ID_ATTR_NAME) + ' tID:' + taskDom.attr(CART_ID_ATTR_NAME) + ' rID:' + releaseDom.attr(CART_ID_ATTR_NAME));
            return null;
        }
        return postdata;
    });
}

function requestAddRelease(item, insertIndex, e) {
    var postdata = {
        title: item.title,
        order: insertIndex,
    };
    requestBaseAdd('release', e, function () {
        var postdata = {
            title: item.title,
            order: insertIndex,
        };
        return postdata;
    });
}

function requestBaseChangeTitle(api, e, dataCallback) {
    var func = function (callback) {
        var postdata = dataCallback();
        if(postdata == null)
            return;
        postdata.storyMapId = STORY_MAP_ID;
        $.ajax({
                url: apiBase + api,
                data: postdata,
                type: "PUT",
                headers: headers,
                success: function (data, status) {
                    console.log('data: ' + data + ' status: ' + status);
                    if (status == "success") {
                        if (data.status == 0) {
                            result = data.result;
                            console.log(result);
                        }
                    }
                },
                dataType: "json"
            }
        ).fail(function (e) {
            console.log(e);
        }).always(callback);
    };
    funcQueue.push(func);
}

function requestChangeTitleActivity(dom, value, index) {
    console.log('change title activity card index:' + index + ' ID:' + dom.attr(CART_ID_ATTR_NAME));
    console.log('change value ' + value);
    requestBaseChangeTitle('/activity', dom, function () {
        var postdata = {
            id: dom.attr(CART_ID_ATTR_NAME),
            title: value,
        };
        return postdata;
    });
}

function requestChangeTitleTask(dom, value, index) {
    console.log('change title task card index:' + index + ' ID:' + dom.attr(CART_ID_ATTR_NAME));
    console.log('change value ' + value);
    requestBaseChangeTitle('/task', dom, function () {
        var postdata = {
            id: dom.attr(CART_ID_ATTR_NAME),
            title: value,
        };
        return postdata;
    });
}

function requestChangeTitleSubtask(dom, value, index) {
    console.log('change title subtask card index:' + index + ' ID:' + dom.attr(CART_ID_ATTR_NAME));
    console.log('change value ' + value);
    requestBaseChangeTitle('/subtask', dom, function () {
        var postdata = {
            id: dom.attr(CART_ID_ATTR_NAME),
            title: value,
        };
        return postdata;
    });
}

function requestChangeTitleRelease(dom, value, index) {
    console.log('change title release card index:' + index + ' ID:' + dom.attr(CART_ID_ATTR_NAME));
    requestBaseChangeTitle('/release', dom, function () {
        var postdata = {
            id: dom.attr(CART_ID_ATTR_NAME),
            title: value,
        };
        return postdata;
    });
}

var minInterval = 600;
var interval = 0;
var mapStatus = '';
var preStatus = mapStatus;

function changeStatus(status) {
    mapStatus = status;
}

var funcQueue = [];
var isRequest = false;

self.setInterval(function () {
    if (!isRequest) {
        if (funcQueue.length > 0) {
            isRequest = true;
            changeStatus('保存中。。。')
            var func = funcQueue.shift();
            var callback = function () {
                isRequest = false;
            };
            try {
                func(callback);
            } catch (e) {
                isRequest = false;
                console.error(e);
            }
        } else {
            if (!isRequest) {
                if (mapStatus != '')
                    changeStatus('保存成功');
            }
        }
    }

    if (interval <= 0) {
        if (mapStatus != preStatus) {
            preStatus = mapStatus;

            if (mapStatus == '') {
                console.log('fadeout');
                $('#status').fadeOut(400);
            } else {
                $('#status').show();
                $('#status').text(mapStatus);
            }
            if (mapStatus != '保存中。。。')
                mapStatus = '';
            interval = minInterval;
        } else {
            mapStatus = '';
        }
    }
    interval -= 200;
}, 200);

var MESSAGE_WAIT_DELETE_COMPLETE = '请等待上一操作保存完成。。。。。';
var MESSAGE_WAIT_DELETE_COMPLETE_TYPE = MSG_TYPE_DANGER;
var MESSAGE_WAIT_DELETE_COMPLETE_LEVEL = 6;


function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]);
    return null; //返回参数值
}