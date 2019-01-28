if (!CONTEXT_PATH) {
    var CONTEXT_PATH = "\/";
}
$(document).ready(function () {
    $("#pro").click(function () {
        $("#userInfo").load('/template/user-Info.html', function () {
            $('#myModal').on('hide.bs.modal', function () {//模态框关闭时触发,刷新父页面
                window.location.reload()
            })
        });
    })
});

function newWorkspace(){
    if(!$('#workspace-name').val())
        return;
    $('#new-workspace-btn-text').hide();
    $('#new-workspace-loading').show();
    var postdata = {
        title: $('#workspace-name').val(),
        desc: $('#workspace-desc').val()?$('#workspace-desc').val():' ',
    };
    $.ajax({
            url: CONTEXT_PATH+'api/workspace',
            data: postdata,
            type: "POST",
            headers: headers,
            success: function (data, status) {
                console.log('data: ' + data + ' status: ' + status);
                if (status == "success") {
                    if (data.status == 0) {
                        var newSpace = data.result;
                        console.log(newSpace);

                        var newSpaceDom = $('<span class="list-group-item active" style="display: inline-block;"></span>');
                        newSpaceDom.append('<h4 class="list-group-item-heading" >'+newSpace.title+'</h4>');
                        newSpaceDom.append('<p class="list-group-item-text" >'+newSpace.desc+'</p>');
                        newSpaceDom.append('<a class="storymap-skip" href="'+CONTEXT_PATH+'template/storymap.html?workspaceId='+newSpace.id+'"></a>');
                        newSpaceDom.append('<i id="'+newSpace.id+'" class="fa fa-times storymap-close" aria-hidden="true" onclick="delWorkspace(this)"></i>');
                        $('#last-workspcase').before(newSpaceDom);
                    }
                }
            },
            dataType: "json"
        }
    ).fail(function (e) {
        console.error(e);
    }).always(function () {
        $('#new-workspace-btn-text').show();
        $('#new-workspace-loading').hide();
    })
}

function delWorkspace(e) {
    var id = $(e).attr('id');
    $(e).removeClass('fa-times');
    $(e).addClass('fa-spinner');
    $(e).addClass('fa-spin');
    $.ajax({
            url: CONTEXT_PATH+'api/workspace' + '/' + id,
            type: "DELETE",
            headers: headers,
            success: function (data, status) {
                console.log('data: ' + data + ' status: ' + status);
                if (status == "success") {
                    console.log(data);
                    $(e).parent().remove();
                }
            },
            dataType: "json"
        }
    ).fail(function (e) {
        console.error(e);
    });
}