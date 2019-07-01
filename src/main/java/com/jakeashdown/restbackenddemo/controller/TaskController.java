package com.jakeashdown.restbackenddemo.controller;

import com.jakeashdown.restbackenddemo.boundary.TaskBoundary;
import com.jakeashdown.restbackenddemo.model.Task;
import com.jakeashdown.restbackenddemo.model.TaskWithoutId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@CrossOrigin // TODO: make global
@RestController
public class TaskController {
    @Autowired
    TaskBoundary taskBoundary;

    @GetMapping("/task")
    public List<Task> getAllTasks() {
        System.out.println("Getting all tasks...");
        return taskBoundary.getAllTasks();
    }

    @GetMapping("/task/{id}")
    public Optional<Task> getTaskForId(@PathVariable BigInteger id) {
        System.out.println("Getting task for ID [" + id + "]...");
        return taskBoundary.getTaskForId(id);
    }

    // TODO: make this return 201 Created
    @PostMapping("/task")
    public BigInteger createTask(@RequestBody TaskWithoutId taskWithoutId) {
        System.out.println("Creating task with title [" + taskWithoutId.getTitle() + "]...");
        BigInteger taskId = taskBoundary.createTaskReturningId(taskWithoutId.getTitle(), taskWithoutId.getDescription());
        System.out.println("Returning task ID [" + taskId + "]...");
        return taskId;
    }
}
