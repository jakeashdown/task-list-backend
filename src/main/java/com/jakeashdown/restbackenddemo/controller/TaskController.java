package com.jakeashdown.restbackenddemo.controller;

import com.jakeashdown.restbackenddemo.model.Step;
import com.jakeashdown.restbackenddemo.model.Task;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class TaskController {
    private final AtomicLong counter = new AtomicLong();

    @CrossOrigin // TODO: make global
    @GetMapping("/")
    public List<Task> getAllTasks() {
        System.out.println("Getting all tasks...");

        // TODO: replace with actual data
        Task task = new Task(
                1,
                "Get a job",
                new ArrayList<>()
        );
        List tasks = new ArrayList();
        tasks.add(task);
        return tasks;
    }
}
