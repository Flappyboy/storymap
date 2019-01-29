package cn.edu.nju.software.storymapping.map.controller;

import cn.edu.nju.software.storymapping.map.controller.mockdto.*;
import cn.edu.nju.software.storymapping.map.entity.Release;
import cn.edu.nju.software.storymapping.map.entity.StoryMap;
import cn.edu.nju.software.storymapping.system.dto.Response;
import cn.edu.nju.software.storymapping.system.entity.User;
import cn.edu.nju.software.storymapping.system.service.UserService;
import cn.edu.nju.software.storymapping.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping(value = "/mock")
public class MockController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/user")
    public User getUser() {
        User user = userService.currentUser();
        return user;
    }

    @GetMapping(value = "/storymap/{id}")
    public Response getStoryMap(@PathVariable Long id) {
        System.out.println(id);

        List<SubtaskDto> subtaskList = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            subtaskList.add(new SubtaskDto((long) i, "subtask " + i, (long) (Math.random() * 2), new Long(1)));
        }
        Iterator<SubtaskDto> iteratorSubtask = subtaskList.iterator();
        List<TaskDto> taskList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            int num = (int) (Math.random() * 6);
            List<SubtaskDto> list = new ArrayList<>();
            for (int j = 0; j < num; j++) {
                list.add(iteratorSubtask.next());
            }
            taskList.add(new TaskDto((long) i, "task " + i, list, 1));
        }

        Iterator<TaskDto> iteratorTask = taskList.iterator();
        List<ActivityDto> activityList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            int num = (int) (Math.random() * 4);
            List<TaskDto> list = new ArrayList<>();
            for (int j = 0; j < num; j++) {
                list.add(iteratorTask.next());
            }
//            activityList.add(new ActivityDto((long) i, "activity " + i, list, 1));
        }

        List<ReleaseDto> releases = new ArrayList<>();
        ReleaseDto releaseDto0 = new ReleaseDto(0l, "Release1", activityList, 1);
        ReleaseDto releaseDto1 = new ReleaseDto(1l, "Release2", activityList, 1);
        releases.add(releaseDto0);
        releases.add(releaseDto1);

//        StoryMapDto storyMapDto = new StoryMapDto(1l, "story map", activityList, releases, 1);

        Response response = new Response();
//        response.success(storyMapDto);

        return response;
    }


    @PostMapping(value = "/activity")
    public Response addActivity(@ModelAttribute ActivityDto dto) {
        return mockAddResponse(dto);
    }

    @PostMapping(value = "/task")
    public Response addTask(TaskDto dto) {
        return mockAddResponse(dto);
    }

    @PostMapping(value = "/subtask")
    public Response addSubtask(SubtaskDto dto) {
        return mockAddResponse(dto);
    }

    @PostMapping(value = "/release")
    public Response addRelease(ReleaseDto dto) {
        return mockAddResponse(dto);
    }

    private Response mockAddResponse(ItemDto dto) {
        Response response = new Response().success(null);
        dto.setId((long) (Math.random() * 100000000));
        response.setResult(dto);
        System.out.println(dto);
        return response;
    }
}
