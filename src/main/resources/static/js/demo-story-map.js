$(function () {
    //init(storyMap);
    initSortable();
    initEditable();
});
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