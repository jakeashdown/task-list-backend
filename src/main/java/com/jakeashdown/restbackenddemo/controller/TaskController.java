package com.jakeashdown.restbackenddemo.controller;

import com.jakeashdown.restbackenddemo.boundary.TaskBoundary;
import com.jakeashdown.restbackenddemo.model.Task;
import com.jakeashdown.restbackenddemo.model.TaskWithId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public List<TaskWithId> getAllTasks() {
        System.out.println("Getting all tasks...");
        return taskBoundary.getAllTasks();
    }

    @GetMapping("/task/{id}")
    public Optional<TaskWithId> getTaskForId(@PathVariable BigInteger id) {
        System.out.println("Getting task for ID [" + id + "]...");
        return taskBoundary.getTaskForId(id);
    }

    @PostMapping("/task")
    public ResponseEntity<BigInteger> createTask(@RequestBody Task task) {
        System.out.println("Creating task with title [" + task.getTitle() + "]...");
        BigInteger taskId = taskBoundary.createTaskReturningId(task.getTitle(), task.getDescription());
        System.out.println("Returning task ID [" + taskId + "]...");
        return new ResponseEntity(taskId, HttpStatus.CREATED);
    }
}
