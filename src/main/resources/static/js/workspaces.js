if (!CONTEXT_PATH) {
    var CONTEXT_PATH = "\/";
}

var STORY_MAP_ID = getUrlParam('id');
// if (STORY_MAP_ID == null) {
//     window.location.href = CONTEXT_PATH + "";
// }

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
            }
        }
    });
});
$(document).ready(function (){
function getStorymap(api, e, dataCallback) {
    var func = function (callback) {
        var getdata = dataCallback();
        if(getdata == null)
            return;
        getdata.storyMapId = STORY_MAP_ID;
        $.ajax({
                url: apiBase + api,
                data: getdata,
                type: "GET",
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
            console.error(e);
        }).always(callback);
    };
    funcQueue.push(func);
}
});

function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]);
    return null; //返回参数值
}