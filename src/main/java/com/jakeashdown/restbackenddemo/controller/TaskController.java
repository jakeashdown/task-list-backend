package com.jakeashdown.restbackenddemo.controller;

import com.jakeashdown.restbackenddemo.boundary.TaskBoundary;
import com.jakeashdown.restbackenddemo.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@CrossOrigin // TODO: make global
@RestController
public class TaskController {
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    TaskBoundary taskBoundary;

    @GetMapping("/task")
    public List<Task> getAllTasks() {
        System.out.println("Getting all tasks...");
        return taskBoundary.getAllTasks();
    }

    @GetMapping("/task/{id}")
    public Optional<Task> getTaskForId(@PathVariable BigInteger id) {
        System.out.println("Getting task for ID " + id + "...");
        return taskBoundary.getTaskForId(id);
    }
}
