if (!CONTEXT_PATH) {
    var CONTEXT_PATH = "\/";
}

var boardActivities;
var boardSortableReleases;

var storyMap = {
    activities: [
        {
            title: 'a1',
            tasks: [
                {
                    title: 'a1-t1',
                },
                {
                    title: 'a1-t2',
                }
            ],
        },
        {
            title: 'a3',
            tasks: [],
        },
        {
            title: 'a2',
            tasks: [
                {
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
                                    title: 'a1-t1-s1-r1',
                                },
                                {
                                    title: 'a1-t1-s2-r1',
                                }
                            ],
                        },
                        {
                            subtasks: [
                                {
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
                                    title: 'a1-t1-s1-r2',
                                },
                                {
                                    title: 'a1-t1-s2-r2',
                                }
                            ],
                        },
                        {
                            subtasks: [
                                {
                                    title: 'a1-t1-s1-r2',
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

$(function () {
    init(storyMap);
});

function init(storyMap) {
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

function addTaskToReleases(activityIndex, taskIndex) {
    var releases = boardSortableReleases.children();
    for(var i=1; i<=releases.length; i++){
        var release = boardSortableReleases.children(':nth-child('+i+')');
        var activity = release.find('.board-subtasks-activities').children(':nth-child('+(activityIndex+1)+')');
        var task = activity.children().children(':nth-child('+(taskIndex+1)+')');
        task.after(newBoardTasks([]));
    }
}

function newBoardActivity(activity) {
    var element = $('<li class="board-activity"></li>');
    element.append(newPersons());
    element.append(newBoardActivityCard(activity));
    element.append(newBoardTasksOuter(activity.tasks));
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
        // TODO
    };
    var closeCallback = function (e) {
        e.parent().remove();
        // TODO
    };
    var element = newBoardCard($('<li></li>'), activity, closeCallback, null, nextRightCallback);

    element.addClass('board-activity-card');
    element.addClass('board-card-color-blue');
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
    return element;
}

function newBoardTaskCard(task) {
    var nextBottomCallback = function (e) {
        // TODO
    };
    var nextRightCallback = function (e) {
        var newCard = newBoardTaskCard();
        e.parent().after(newCard);
        var taskIndex = e.parent().prevAll().length;
        var activityIndex = e.parents('.board-activity').prevAll().length;
        console.log('taskIndex ' + taskIndex);
        console.log('activityIndex ' + activityIndex);
        addTaskToReleases(activityIndex, taskIndex);
    };
    var closeCallback = function (e) {
        e.parent().remove();
        // TODO
    };
    var element = newBoardCard($('<li></li>'),task, closeCallback, null, nextRightCallback);
    element.addClass('board-task');
    element.addClass('board-task-card');
    element.addClass('board-card-color-green');
    return element;
}

function newReleaseWithSubstasks(release) {
    var element = $('<div class="release-with-subtasks"></div>');
    element.append(newBoardRelease(release));
    element.append($('<div></div>').append(newBoardSubtaskActivities(release.activities)));
    return element;
}

function newBoardRelease(release) {
    var element = $('<div class="board-release">' + release.title + '</div>');
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
    var element = newBoardCard( $('<li></li>'), subtask, closeCallBack, nextBottomCallBack);
    element.addClass('board-subtask-card');
    element.addClass('board-card-color-yellow');
    return element;
}

function newBoardCard(element, card, closeCallback, nextBottomCallback, nextRightCallback) {
    if (card && card.title) {
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
    if (nextBottomCallback){
        element.append(newBoardCardBlockBottom());
        element.append(newBoardCardNextBottom());
        element.children('.board-card-next-bottom').click(function () {
            nextBottomCallback($(this));
        });
    }
    if (nextRightCallback){
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
        console.log("leave card")
    });
    return element;
}

function newBoardCardNextBottom() {
    var element = $('<span class="board-card-next-bottom" style="display: none"><img src="' + CONTEXT_PATH + 'img/story-map/icon_arrow_down.png"></span>');
    element.mouseleave(function () {
        $(this).slideUp(50);
        console.log("leave bottom")
    });
    return element;
}

function newBoardCardBlockBottom() {
    var element = $('<span class="board-card-block-bottom"></span>');
    element.mouseenter(function () {
        $(this).siblings('.board-card-next-bottom').slideDown(50);
        console.log("enter")
    });
    return element;
}

function newBoardCardNextRight() {

    var element = $('<span class="board-card-next-right" style="display: none"><img src="' + CONTEXT_PATH + 'img/story-map/icon_arrow_right.png"></span>');
    element.mouseleave(function () {
        $(this).fadeOut(50);
        console.log("leave right")
    });
    return element;
}

function newBoardCardBlockRight() {
    var element = $('<span class="board-card-block-right"></span>');
    element.mouseenter(function () {
        $(this).siblings('.board-card-next-right').fadeIn(50);
        console.log("enter")
    });
    return element;
}

function newBoardCardBlockClose() {
    var element = $('<span class="board-card-block-close" ></span>');
    element.mouseenter(function () {
        $(this).siblings('.board-card-close').fadeIn(50);
        console.log("enter")
    });
    return element;
}

function newBoardCardClose() {
    var element = $('<span class="board-card-close" style="display: none"><img src="' + CONTEXT_PATH + 'img/story-map/icon_close.png"></span>');
    element.mouseleave(function () {
        $(this).fadeOut(50);
        console.log("leave close")
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
        console.log("enter")
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
        console.log("enter")
    });
    return element;
}

function newCardTitleEditor() {
    var element = $('<textarea type="text" class="board-card-title-editor" data-editmode="false"></textarea>');
    element.mouseenter(function () {
        $(this).siblings('.board-card-next-bottom').slideUp(50);
        console.log("enter")
    });
    return element;
}



