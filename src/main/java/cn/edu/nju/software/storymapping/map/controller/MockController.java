package cn.edu.nju.software.storymapping.map.controller;

import cn.edu.nju.software.storymapping.map.controller.mockdto.*;
import cn.edu.nju.software.storymapping.map.entity.StoryMap;
import cn.edu.nju.software.storymapping.system.dto.Response;
import cn.edu.nju.software.storymapping.system.entity.User;
import cn.edu.nju.software.storymapping.utils.UserUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping(value = "/mock")
public class MockController {

    @GetMapping(value = "/user")
    public User getUser(){
        User user = UserUtil.currentUser();
        return user;
    }

    @GetMapping(value = "/storymap")
    public Response getStoryMap(){

        List<SubtaskDto> subtaskList = new ArrayList<>();
        for(int i=0; i<1000; i++){
            subtaskList.add(new SubtaskDto((long) i, "subtask "+i, (long)(Math.random()*2)));
        }
        Iterator<SubtaskDto> iteratorSubtask= subtaskList.iterator();
        List<TaskDto> taskList = new ArrayList<>();
        for(int i=0; i<20; i++){
            int num = (int) (Math.random()*6);
            List<SubtaskDto> list = new ArrayList<>();
            for(int j=0; j<num; j++){
                list.add(iteratorSubtask.next());
            }
            taskList.add(new TaskDto((long) i, "task "+i, list));
        }

        Iterator<TaskDto> iteratorTask= taskList.iterator();
        List<ActivityDto> activityList = new ArrayList<>();
        for(int i=0; i<3; i++){
            int num = (int) (Math.random()*4);
            List<TaskDto> list = new ArrayList<>();
            for(int j=0; j<num; j++){
                list.add(iteratorTask.next());
            }
            activityList.add(new ActivityDto((long) i, "activity "+i, list));
        }

        List<ReleaseDto> releases = new ArrayList<>();
        ReleaseDto releaseDto0 = new ReleaseDto(0l, "Release1", activityList);
        ReleaseDto releaseDto1 = new ReleaseDto(1l, "Release2", activityList);
        releases.add(releaseDto0);
        releases.add(releaseDto1);

        StoryMapDto storyMapDto = new StoryMapDto(1l, "story map", activityList, releases);

        Response response = new Response();
        response.success(storyMapDto);

        return response;
    }
}
