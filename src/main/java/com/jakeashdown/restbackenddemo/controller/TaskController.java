package com.jakeashdown.restbackenddemo.controller;

import com.jakeashdown.restbackenddemo.boundary.TaskBoundary;
import com.jakeashdown.restbackenddemo.model.Task;
import com.jakeashdown.restbackenddemo.model.TaskWithId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/task")
    public List<TaskWithId> queryAllTasks() {
        logger.info("GET '/task'");
        return taskBoundary.selectAllTasks();
    }

    @GetMapping("/task/{id}")
    public Optional<TaskWithId> queryTaskForId(@PathVariable BigInteger id) {
        logger.info("GET 'task/" + id + "'");
        return taskBoundary.selectTaskForId(id);
    }

    @PostMapping("/task")
    public ResponseEntity<BigInteger> createTaskReturningId(@RequestBody Task task) {
        logger.info("POST '/task' [" + task + "]");
        BigInteger taskId = taskBoundary.insertTaskReturningId(task.getTitle(), task.getDescription());
        return new ResponseEntity(taskId, HttpStatus.CREATED);
    }

    @PutMapping("/task/{id}")
    public void createOrUpdateTask(@PathVariable BigInteger id, @RequestBody Task task) {
        logger.info("PUT '/task/" + id + "' [" + task + "]");
        taskBoundary.createOrUpdateTask(id, task);
    }
}
