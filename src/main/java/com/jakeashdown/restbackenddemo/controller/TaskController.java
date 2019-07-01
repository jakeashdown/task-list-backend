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
    public List<TaskWithId> queryAllTasks() {
        System.out.println("TaskController: querying all tasks");
        return taskBoundary.selectAllTasks();
    }

    @GetMapping("/task/{id}")
    public Optional<TaskWithId> queryTaskForId(@PathVariable BigInteger id) {
        System.out.println("TaskController: querying task for ID [" + id + "]");
        return taskBoundary.selectTaskForId(id);
    }

    @PostMapping("/task")
    public ResponseEntity<BigInteger> mutateTaskReturningId(@RequestBody Task task) {
        System.out.println("TaskController: mutating task with title [" + task.getTitle() + "]");
        BigInteger taskId = taskBoundary.insertTaskReturningId(task.getTitle(), task.getDescription());
        System.out.println("TaskController: returning task ID [" + taskId + "]");
        return new ResponseEntity(taskId, HttpStatus.CREATED);
    }
}
