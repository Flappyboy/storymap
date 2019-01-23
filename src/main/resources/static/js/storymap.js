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
                            for (var i = 0; i < maps.length; i++) {
                                console.log(maps[i]);
                                /*
                                <a class="list-group-item active" href="#">
        <h4  class="list-group-item-heading" ></h4>
    	<p  class="list-group-item-text" ></p>
    </a>
                                * */
                                var mapDom = $('<a class="list-group-item active" href="'+CONTEXT_PATH+'template/story-map/story-map.html?id='+maps[i].id+'"></a>');
                                mapDom.append('<h4  class="list-group-item-heading" >'+maps[i].title+'</h4>');
                                mapDom.append('<p  class="list-group-item-text" ></p>');
                                $('.list-group').append(mapDom);
                            }
                        }
                    }
                },
                dataType: "json"
            }
        ).fail(function (e) {
            console.error(e);
        });
});

function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]);
    return null; //返回参数值
}