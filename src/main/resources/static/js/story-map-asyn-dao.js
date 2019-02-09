var StoryMapAsynDao = function (args) {
    
    this._storymapModel = new StoryMapModel({
        storymap: args.storymap,
    });
    this._valid = args.valid;
    this._asyn = new AsynExec({
        start: function () {
            args.start();
        },
        end: function () {
            args.end();
        },
        delayEnd: function () {
            args.delayEnd();
        },
    });
    
}

StoryMapAsynDao.prototype._baseAdd = function (api, dataAcqFunc, resultCallback) {
    if (!this._valid) return;

    dataAcqFunc = dataAcqFunc.bind(this);
    resultCallback = resultCallback.bind(this);
    var func = function (callback) {
        var data = dataAcqFunc();
        data.storyMapId = this._storymapModel.getStoryMapId();
        $.ajax({
                url: apiBase + api,
                data: data,
                type: "POST",
                headers: headers,
                success: function (data, status) {
                    console.log('data: ' + data + ' status: ' + status);
                    if (status == "success") {
                        if (data.status == 0) {
                            var result = data.result;
                            resultCallback(result);
                        }
                    }
                },
                dataType: "json"
            }
        ).fail(function (e) {
            console.error(e);
        }).always(callback);
    };
    this._asyn.pushFunc(func.bind(this));
}
StoryMapAsynDao.prototype.addActivity = function (activity_i, model) {
    var data;
    this._baseAdd('/activity', function () {
        data = {
            title: model.title,
            order: activity_i
        };
        return data;
    },function (result) {
        data.id = result.id;
        this._storymapModel.addActivity(activity_i, data);
        // this._storymapModel.modifyActivity(activity_i,'id',result.id);
    })
}
StoryMapAsynDao.prototype.addTask = function (activity_i, task_i, model) {
    var data;
    this._baseAdd('/task', function () {
        data = {
            title: model.title,
        };
        data.order = task_i;
        data.activityId = this._storymapModel.getActivity(activity_i).id;
        return data;
    },function (result) {
        data.id = result.id;
        this._storymapModel.addTask(activity_i, task_i, data);
        // this._storymapModel.modifyTask(activity_i, task_i,'id',result.id);
    })
}
StoryMapAsynDao.prototype.addRelease = function (release_i, model) {
    var data;
    this._baseAdd('/release', function () {
        data = {
            title: model.title,
        };
        data.order = release_i;
        return data;
    },function (result) {
        data.id = result.id;
        this._storymapModel.addRelease(release_i, data);
        // this._storymapModel.modifyRelease(release_i,'id',result.id);
    })
}
StoryMapAsynDao.prototype.addSubtask = function (activity_i, task_i, release_i, subtask_i, model) {
    var data;
    this._baseAdd('/subtask', function () {
        data = {
            title: model.title,
        };
        data.order = subtask_i;
        data.activityId = this._storymapModel.getActivity(activity_i).id;
        data.taskId = this._storymapModel.getTask(activity_i, task_i).id;
        data.releaseId = this._storymapModel.getRelease(release_i).id;
        return data;
    },function (result) {
        data.id = result.id;
        this._storymapModel.addSubtask(activity_i, task_i, release_i, subtask_i, data);
        // this._storymapModel.modifySubtask(activity_i, task_i, release_i, subtask_i,'id',result.id);
    })
}

StoryMapAsynDao.prototype._baseModifyTitle = function(api, dataAcqFunc, resultCallback) {
    if (!this._valid) return;

    dataAcqFunc = dataAcqFunc.bind(this);
    resultCallback = resultCallback.bind(this);
    var func = function (callback) {
        var data = dataAcqFunc();
        data.storyMapId = this._storymapModel.getStoryMapId();
        $.ajax({
                url: apiBase + api,
                data: data,
                type: "PUT",
                headers: headers,
                success: function (data, status) {
                    console.log('data: ' + data + ' status: ' + status);
                    if (status == "success") {
                        if (data.status == 0) {
                            result = data.result;
                            console.log(result);
                            resultCallback();
                        }
                    }
                },
                dataType: "json"
            }
        ).fail(function (e) {
            console.error(e);
        }).always(callback);
    };
    this._asyn.pushFunc(func.bind(this));
}
StoryMapAsynDao.prototype.modifyActivityTitle = function (activity_i, value) {

    this._baseModifyTitle('/activity', function () {
        var data = {
            id: this._storymapModel.getActivity(activity_i).id,
            title: value,
        };
        return data;
    }, function () {
        this._storymapModel.modifyActivity(activity_i, 'title', value);
    });
}
StoryMapAsynDao.prototype.modifyTaskTitle = function (activity_i, task_i, value) {

    this._baseModifyTitle('/task', function () {
        var data = {
            id: this._storymapModel.getTask(activity_i, task_i).id,
            title: value,
        };
        return data;
    }, function () {
        this._storymapModel.modifyTask(activity_i, task_i, 'title', value);
    });
}
StoryMapAsynDao.prototype.modifyReleaseTitle = function (release_i, value) {

    this._baseModifyTitle('/release', function () {
        var data = {
            id: this._storymapModel.getRelease(release_i).id,
            title: value,
        };
        return data;
    }, function () {
        this._storymapModel.modifyRelease(release_i, 'title', value);
    });
}
StoryMapAsynDao.prototype.modifySubtaskTitle = function (activity_i, task_i, release_i, subtask_i, value) {

    this._baseModifyTitle('/subtask', function () {
        var data = {
            id: this._storymapModel.getSubtask(activity_i, task_i, release_i, subtask_i).id,
            title: value,
        };
        return data;
    }, function () {
        this._storymapModel.modifySubtask(activity_i, task_i, release_i, subtask_i, 'title', value);
    });
}

StoryMapAsynDao.prototype._baseDel= function(api, dataAcqFunc,  resultCallback) {
    if (!this._valid) return;

    dataAcqFunc = dataAcqFunc.bind(this);
    resultCallback = resultCallback.bind(this);
    var func = function (callback) {
        var data = dataAcqFunc();
        data.storyMapId = this._storymapModel.getStoryMapId();
        $.ajax({
                url: apiBase + api + '/' + data.id,
                data: data,
                type: "DELETE",
                headers: headers,
                success: function (data, status) {
                    console.log('data: ' + data + ' status: ' + status);
                    if (status == "success") {
                        console.log(data);
                        resultCallback();
                    }
                },
                dataType: "json"
            }
        ).fail(function (e) {
            console.error(e);
        }).always(callback);
    };
    this._asyn.pushFunc(func.bind(this));
}
StoryMapAsynDao.prototype.delActivity = function (activity_i) {

    this._baseDel('/activity', function () {
        var data = {
            id: this._storymapModel.getActivity(activity_i).id,
        };
        return data;
    }, function () {
        this._storymapModel.delActivity(activity_i);
    });
}
StoryMapAsynDao.prototype.delTask = function (activity_i, task_i) {

    this._baseDel('/task', function () {
        var data = {
            id: this._storymapModel.getTask(activity_i, task_i).id,
        };
        return data;
    }, function () {
        this._storymapModel.delTask(activity_i, task_i);
    });
}
StoryMapAsynDao.prototype.delRelease = function (release_i) {

    this._baseDel('/release', function () {
        var data = {
            id: this._storymapModel.getRelease(release_i).id,
        };
        return data;
    }, function () {
        this._storymapModel.delRelease(release_i);
    });
}
StoryMapAsynDao.prototype.delSubtask = function (activity_i, task_i, release_i, subtask_i) {

    this._baseDel('/subtask', function () {
        var data = {
            id: this._storymapModel.getSubtask(activity_i, task_i, release_i, subtask_i).id,
        };
        return data;
    }, function () {
        this._storymapModel.delSubtask(activity_i, task_i, release_i, subtask_i);
    });
}
