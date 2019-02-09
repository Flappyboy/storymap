var StoryMapModel = function (args) {
    
    this.storymap = preStorymap(cloneObj(args.storymap));
    
}

function cloneObj(obj) {
    return JSON.parse(JSON.stringify(obj));
}

function preStorymap(storymap){
    storymap.releases.forEach(function (release) {
        delete release.activities;
    });
    return storymap;
}

StoryMapModel.prototype.getStoryMapId = function () {
    return this.storymap.id;
}

/**
 * 对story map model的一些基本操作
 *
 */

StoryMapModel.prototype.getActivity = function (activity_i) {
    return this.storymap.activities[activity_i];
}
StoryMapModel.prototype.getTask = function (activity_i, task_i) {
    return this.getActivity(activity_i).tasks[task_i];
}
StoryMapModel.prototype.getRelease = function (release_i) {
    return this.storymap.releases[release_i];
}

/**
 * 该函数获取的结果是一个新建的list
 * @param activity_i
 * @param task_i
 * @param release_i
 * @returns {Array}
 */
StoryMapModel.prototype.getSubtasks = function (activity_i, task_i, release_i) {
    var subtasks = this.getTask(activity_i,task_i).subtasks;
    var result = [];
    var releaseId = this.getRelease(release_i).id;
    subtasks.forEach(function (subtask) {
        if(subtask.releaseId == releaseId) {
            result.push(subtask);
        }
    });
    return result;
}
StoryMapModel.prototype.getSubtaskIndex = function (activity_i, task_i, release_i, subtask_i) {
    var subtaskList = this.getTask(activity_i, task_i).subtasks;
    var releaseId = this.getRelease(release_i).id;
    var s = -1;
    for(var i = 0; i < subtaskList.length; i++) {
        if(subtaskList[i].releaseId == releaseId) {
            s++;
            if(s == subtask_i){
                return i;
            }
        }
    }
    return -1;
}
StoryMapModel.prototype.getSubtask = function (activity_i, task_i, release_i, subtask_i) {
    var subtasks = this.getSubtasks(activity_i, task_i, release_i);
    return subtasks[subtask_i];
}
StoryMapModel.prototype.addActivity = function (activity_i, model) {
    model.tasks = [];
    this.storymap.activities.splice(activity_i, 0, model);
}
StoryMapModel.prototype.addTask = function (activity_i, task_i, model) {
    model.subtasks= [];
    this.getActivity(activity_i).tasks.splice(task_i, 0, model);
}
StoryMapModel.prototype.addRelease = function (release_i, model) {
    this.storymap.releases.splice(release_i, 0, model);
}
StoryMapModel.prototype.addSubtask = function (activity_i, task_i, release_i, subtask_i, model) {
    var subtaskIndex = this.getSubtaskIndex(activity_i, task_i, release_i, subtask_i);
    if (subtaskIndex < 0){
        this.getTask(activity_i, task_i).subtasks.push(model);
    }else {
        this.getTask(activity_i, task_i).subtasks.splice(subtaskIndex, 0, model);
    }
}
StoryMapModel.prototype.delActivity = function (activity_i) {
    this.storymap.activities.splice(activity_i, 1);
}
StoryMapModel.prototype.delTask = function (activity_i, task_i) {
    this.storymap.activities[activity_i].tasks.splice(task_i, 1);
}
StoryMapModel.prototype.delRelease = function (release_i) {
    this.storymap.releases.splice(release_i, 1);
}
StoryMapModel.prototype.delSubtask = function (activity_i, task_i, release_i, subtask_i) {
    var subtaskIndex = this.getSubtaskIndex(activity_i, task_i, release_i, subtask_i);
    this.getTask(activity_i,task_i).subtasks.splice(subtaskIndex, 1);
}
StoryMapModel.prototype.modifyActivity = function (activity_i, attr, value) {
    this.getActivity(activity_i)[attr] = value;
}
StoryMapModel.prototype.modifyTask = function (activity_i, task_i, attr, value) {
    this.getTask(activity_i, task_i)[attr] = value;
}
StoryMapModel.prototype.modifyRelease = function (release_i, attr, value) {
    this.getRelease(release_i)[attr] = value;
}
StoryMapModel.prototype.modifySubtask = function (activity_i, task_i, release_i, subtask_i, attr, value) {
    this.getSubtask(activity_i, task_i, release_i, subtask_i)[attr] = value;
}