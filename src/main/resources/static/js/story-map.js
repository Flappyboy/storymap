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
            tasks: [
            ],
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
                    tasks:[
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
                  tasks:[]
                },
                {
                    tasks:[
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
                    tasks:[
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
                    tasks:[]
                },
                {
                    tasks:[
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
    initSortable();
    initEditable();
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

function initEditable() {
    $(".board-card-title-text").click(function () {
        var textarea = $(this).siblings("textarea");
        var textBoard = $(this);
        textarea.val(textBoard.text());
        textBoard.hide();
        textarea.show();
        textarea.blur(function () {
            textBoard.show();
            textBoard.text($(this).val());
            $(this).hide();
        });
        textarea.focus();
    });
}

function initSortable() {
    $(".board-subtasks").sortable({
        connectWith: ".ui-sortable"
    });
    $(".board-tasks-outer .board-tasks").sortable({
        connectWith: ".ui-sortable"
    });
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
    var element = newBoardCard_div(activity);
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
    var element = $('<ul class="board-tasks"></ul>');
    if (tasks) {
        tasks.forEach(function (task, i) {
            element.append(newBoardTaskCard(task));
        })
    }
    return element;
}

function newBoardTaskCard(task) {
    var element = newBoardCard_li(task);
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
    var element = $('<div class="board-release">'+release.title+'</div>');
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
    var element = $('<ul class="board-subtasks"></ul>');
    if (subtasks) {
        subtasks.forEach(function (subtask, i) {
            element.append(newBoardSubtaskCard(subtask));
        })
    }
    return element;
}

function newBoardSubtaskCard(subtask) {
    var element = newBoardCard_li(subtask);
    element.addClass('board-subtask-card');
    element.addClass('board-card-color-yellow');
    return element;
}

function newBoardCard_div(card) {
    var element = $('<div></div>');
    element = newBoardCard(element, card);
    return element;
}

function newBoardCard_li(card) {
    var element = $('<li></li>');
    element = newBoardCard(element, card);
    return element;
}

function newBoardCard(element, card) {
    element.append(newCardTitleText(card.title));
    if (card.title)
        element.append(newCardEmptyTitle().hide());
    else
        element.append(newCardEmptyTitle().show());
    element.append(newCardTitleEditor().hide());
    return element;
}

function newCardTitleText(title) {
    var element = $('<span class="board-card-title-text">' + title + '</span>');
    return element;
}

function newCardEmptyTitle() {
    var element = $('<span class="board-card-empty-title">Empty card</span>');
    return element;
}

function newCardTitleEditor() {
    var element = $('<textarea type="text" class="board-card-title-editor" data-editmode="false"></textarea>');
    return element;
}


function addActivity() {
    addHead();
    addContent();
}



