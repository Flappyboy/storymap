if (!CONTEXT_PATH) {
    var CONTEXT_PATH = "\/";
}

var boardActivities;
var boardSortableReleases;

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
            title: 'release1',
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
            title: 'release2',
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
                    tasks: [
                    ]
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

var zoom = 28; // 1 - 40
var zoomList = '';

for(var i=0; i<40; i++){
    zoomList = zoomList + 'board-zoom-' + (i+1) + ' ';
}


$(function () {
    init(storyMap);
});

function init(storyMap) {
    // 1-40
    $( "#board" ).draggable(
        {
            //containment: "parent",
            scroll: false,
            start: function() {

            },
            drag: function() {
            },
            stop: function() {
                if(lightDom) {
                    lightDom.removeClass('board-light-yellow board-light-blue board-light-green');
                }
            }
        }
    );
    $("#right-content").bind("mousewheel",
        function(event, delta, deltaX, deltaY) {
            console.log(delta, deltaX, deltaY);
            if(delta<0) {
                if(zoom>1){
                    zoom--;
                    $("#right-content").removeClass(zoomList);
                    $("#right-content").addClass("board-zoom-"+zoom);
                }
            }else{
                if(zoom<40){
                    zoom++;
                    $("#right-content").removeClass(zoomList);
                    $("#right-content").addClass("board-zoom-"+zoom);
                }
            }

            console.log(zoomList)
            return false;
        });

    boardActivities = $("#board-activities");
    boardSortableReleases = $("#board-sortable-releases");
    var activities = storyMap.activities;
    activities.forEach(function (activity, i) {
        boardActivities.append(newBoardActivity(activity));
    });
    var releases = storyMap.releases;
    releases.forEach(function (release, i) {
        boardSortableReleases.append(newReleaseWithSubstasks(release));
    });
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

function newBoardActivity(activity) {
    var element = $('<li class="board-activity"></li>');
    element.append(newPersons());
    element.append(newBoardActivityCard(activity));
    element.append(newBoardTasksOuter(activity.tasks));
    if(activity && 'id' in activity){
        console.log('init activity '+activity.id);
        element.attr(CART_ID_ATTR_NAME,activity.id);
    }else{
        requestAddActivity(activity, element);
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
        var newElement = newBoardActivity(activity);
        e.parents('.board-activity').after(newElement);
        console.log(activityIndex);
        addActivityToReleases(activityIndex + 1, activity);
    };
    var closeCallback = function (e) {

        var activityIndex = e.parents('.board-activity').prevAll().length;
        console.log(activityIndex);
        delActivityFromReleases(activityIndex);
        e.parents('.board-activity').remove();
        if (boardActivities.children().length === 0) {
            var activity = {
                title: '',
                tasks: [],
                firstCard: true,
            };
            boardActivities.append(newBoardActivity(activity));
            addActivityToReleases(0, activity);
        }
    };
    var element = newBoardCard($('<li></li>'), activity, closeCallback, null, nextRightCallback);
    element.addClass('board-activity-card');
    element.addClass('board-card-color-blue');
    element.attr('cardtype','activity');
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
        connectWith: ".ui-sortable"
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
            if($(this).children().length === 0) {
                $(this).addClass('board-light-green');
                lightDom = $(this);
            }
            return;
        }
        var task = {
            title: '',
            subtasks: [],
        }
        $(this).append(newBoardTaskCard(task));
        $(this).removeClass('board-light-green');

        var activityIndex = $(this).parents('.board-activity').prevAll().length;
        console.log('activityIndex ' + activityIndex);
        addTaskToReleases(activityIndex, 0, task);
    });
    return element;
}

function newBoardTaskCard(task) {
    var nextBottomCallback = function (e) {
        // TODO
    };
    var nextRightCallback = function (e) {
        var task = {
            title: '',
            subtasks: []
        };
        var newCard = newBoardTaskCard(task);
        e.parent().after(newCard);
        var taskIndex = e.parent().prevAll().length;
        var activityIndex = e.parents('.board-activity').prevAll().length;
        console.log('taskIndex ' + taskIndex);
        console.log('activityIndex ' + activityIndex);
        addTaskToReleases(activityIndex, taskIndex + 1, task);
    };
    var closeCallback = function (e) {
        var taskIndex = e.parent().prevAll().length;
        var activityIndex = e.parents('.board-activity').prevAll().length;
        console.log('taskIndex ' + taskIndex);
        console.log('activityIndex ' + activityIndex);
        delTaskToReleases(activityIndex, taskIndex);
        e.parent().remove();
    };
    var element = newBoardCard($('<li></li>'), task, closeCallback, null, nextRightCallback);
    element.addClass('board-task');
    element.addClass('board-task-card');
    element.addClass('board-card-color-green');
    element.attr('cardtype','task');
    return element;
}

function newReleaseWithSubstasks(release) {
    var element = $('<div class="release-with-subtasks"></div>');
    element.append(newBoardRelease(release));
    element.append($('<div></div>').append(newBoardSubtaskActivities(release.activities)));
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
        connectWith: ".ui-sortable"
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
        if (!$(this).hasClass('board-light-yellow') || $(this).children().length > 0){
            if($(this).children().length === 0) {
                $(this).addClass('board-light-yellow');
                lightDom = $(this);
            }
            return;
        }
        var subtask = {
            title: '',
        };
        $(this).append(newBoardSubtaskCard(subtask));
        $(this).removeClass('board-light-yellow');
    });
    return element;
}

function newBoardSubtaskCard(subtask) {
    var closeCallBack = function (e) {
        e.parent().remove();
    };
    var nextBottomCallBack = function (e) {
        var newCard = newBoardSubtaskCard();
        e.parent().after(newCard);
    };
    var element = newBoardCard($('<li></li>'), subtask, closeCallBack, nextBottomCallBack);
    element.addClass('board-subtask-card');
    element.addClass('board-card-color-yellow');
    element.attr('cardtype','activity');
    return element;
}

function newBoardCard(element, card, closeCallback, nextBottomCallback, nextRightCallback) {
    if (card && 'title' in card && card.title!=='') {
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
        textarea.blur(function () {
            var textBoard = $(this).siblings('.board-card-title-text');
            textBoard.show();
            textBoard.text($(this).val());
            $(this).hide();
        });
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
        textarea.blur(function () {
            var textBoard = $(this).siblings('.board-card-title-text');
            textBoard.show();
            textBoard.text($(this).val());
            $(this).hide();
        });
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
    return element;
}

function requestDelActivity(id) {

}

function requestDelTask(id) {

}

function requestDelSubtask(id) {

}

function requestDelRelease(id) {

}

function requestAddActivity(item, e) {
    if('firstCard' in item){

    }else{
        item.preDom;
    }
    // e.attr(CART_ID_ATTR_NAME,);
}

function requestAddTask(item, e) {

}

function requestAddSubtask(item, e) {

}

function requestAddRelease(item, e) {

}

function requestPutActivity(item) {

}

function requestPutTask(item) {

}

function requestPutSubtask(item) {

}

function requestPutRelease(item) {

}



