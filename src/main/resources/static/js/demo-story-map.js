$(function () {
    //init(storyMap);
    initSortable();
    initEditable();
    initNextBottom();
    initNextRight();
    initClose();
});
function initClose() {
    $(".board-card-block-close").mouseenter(function () {
        $(this).siblings('.board-card-close').fadeIn(50);
        console.log("enter")
    });
    $(".board-card-close").mouseleave(function () {
        $(this).fadeOut(50);
        console.log("leave close")
    });

    $(".board-card").mouseleave(function () {
        $(this).children('.board-card-next-bottom').slideUp(50);
        $(this).children('.board-card-next-right').fadeOut(50);
        $(this).children('.board-card-close').fadeOut(50);
        console.log("leave card")
    });
}
function initNextRight() {
    $(".board-card-block-right").mouseenter(function () {
        $(this).siblings('.board-card-next-right').fadeIn(50);
        console.log("enter")
    });
    $(".board-card-next-right").mouseleave(function () {
        $(this).fadeOut(50);
        console.log("leave right")
    });

    // $(".board-card").mouseleave(function () {
    //     $(this).children('.board-card-next-bottom').slideUp(50);
    //     $(this).children('.board-card-next-right').fadeOut(50);
    //     console.log("leave card")
    // });
}
function initNextBottom() {
    $(".board-card-block-bottom").mouseenter(function () {
        $(this).siblings('.board-card-next-bottom').slideDown(50);
        console.log("enter")
    });
    $(".board-card-next-bottom").mouseleave(function () {
        $(this).slideUp(50);
        console.log("leave bottom")
    });

    // $(".board-card").mouseleave(function () {
    //     $(this).children('.board-card-next-bottom').slideUp(50);
    //     console.log("leave card")
    // });
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