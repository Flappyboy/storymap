
function addActivity(){
    addHead();
    addContent();
}

$(function() {
    $( ".board-subtasks" ).sortable({
        connectWith: ".ui-sortable"
    });
    $( ".board-tasks" ).sortable({
        connectWith: ".ui-sortable"
    });
    $(".board-card-title-text").click(function(){
        var textarea = $(this).siblings("textarea");
        var textBoard = $(this);
        textarea.val(textBoard.text());
        textBoard.hide();
        textarea.show();
        textarea.blur(function(){
            textBoard.show();
            textBoard.text($(this).val());
            $(this).hide();
        });
        textarea.focus();
    });
});
