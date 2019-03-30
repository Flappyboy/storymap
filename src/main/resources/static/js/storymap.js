if (!CONTEXT_PATH) {
    var CONTEXT_PATH = "\/";
}
var WORKSPACE_ID = getUrlParam('workspaceId');
if(WORKSPACE_ID==null){
    //window
}

// 请求的基本url
var apiBase = CONTEXT_PATH + 'api';


$(function (){
        $("#pro").click(function () {
            $("#userInfo").load('/template/user-Info.html', function () {
                $('#myModal').on('hide.bs.modal', function () {//模态框关闭时触发,刷新父页面
                    window.location.reload()
                })
            });
        })
        $.ajax({
                url: apiBase + '/storymap/workspace/'+WORKSPACE_ID,
                type: "GET",
                headers: headers,
                success: function (data, status) {
                    console.log(data);
                    console.log('data: ' + data + ' status: ' + status);
                    if (status == "success") {
                        if (data.status == 0) {
                            var maps = data.result.storyMaps;
                            if(maps.length==1){
                                $("#arrow").show();
                                $("#mess").show();
                            }else{
                                $("#arrow").hide();
                                $("#mess").hide();
                            }
                            for (var i = 0; i < maps.length; i++) {
                                console.log(maps[i]);
                                var mapDom = $('<span class="list-group-item active" style="display: inline-block;"></span>');
                                mapDom.append('<h4  class="list-group-item-heading" >'+maps[i].title+'</h4>');
                                mapDom.append('<p  class="list-group-item-text" >'+maps[i].desc+'</p>');
                                mapDom.append('<a class="storymap-skip" href="'+CONTEXT_PATH+'template/story-map/story-map.html?id='+maps[i].id+'"></a>');
                                mapDom.append('<i id="'+maps[i].id+'" class="fa fa-times storymap-close" aria-hidden="true" onclick="delStorymap(this)"></i>');
                                $('#last-flag').before(mapDom);
                            }
                        }
                    }
                },
                dataType: "json"
            }
        ).fail(function (e) {
            console.error(e);
        }).always(function () {
            $('#last-storymap').show();
            $('#last-storymap').attr('display','block');
            $('#loading-storymap').hide();
        });
});

function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]);
    return null; //返回参数值
}

function newStorymap(){
    if(!$('#new-name').val())
        return;
    if(!$('#new-desc').val())
        return;
    $('#new-btn-text').hide();
    $('#new-loading').show();
    var postdata = {
        workSpaceId: WORKSPACE_ID,
        title: $('#new-name').val(),
        desc: $('#new-desc').val(),
    };
    $("#arrow").hide();
    $("#mess").hide();
    $.ajax({
            url: CONTEXT_PATH+'api/storymap',
            data: postdata,
            type: "POST",
            headers: headers,
            success: function (data, status) {
                console.log('data: ' + data + ' status: ' + status);
                if (status == "success") {
                    if (data.status == 0) {
                        var newSpace = data.result;
                        console.log(newSpace);

                        var mapDom = $('<span class="list-group-item active" style="display: inline-block;"></span>');
                        mapDom.append('<h4  class="list-group-item-heading" >'+newSpace.title+'</h4>');
                        mapDom.append('<p  class="list-group-item-text" >'+newSpace.desc+'</p>');
                        mapDom.append('<a class="storymap-skip" href="'+CONTEXT_PATH+'template/story-map/story-map.html?id='+newSpace.id+'"></a>');
                        mapDom.append('<i id="'+newSpace.id+'" class="fa fa-times storymap-close" aria-hidden="true" onclick="delStorymap(this)"></i>');
                        $('#last-flag').before(mapDom);
                    }
                }
            },
            dataType: "json"
        }
    ).fail(function (e) {
        console.error(e);
    }).always(function () {
        $('#new-btn-text').show();
        $('#new-loading').hide();
        $('#myModal').modal('hide');
        $('#new-name').val('');
        $('#new-desc').val('');
    })
}

function delStorymap(e) {
    var id = $(e).attr('id');
    $(e).removeClass('fa-times');
    $(e).addClass('fa-spinner');
    $(e).addClass('fa-spin');
    $.ajax({
            url: CONTEXT_PATH+'api/storymap' + '/' + id,
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