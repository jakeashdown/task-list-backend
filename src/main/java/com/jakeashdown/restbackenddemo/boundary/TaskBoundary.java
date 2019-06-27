package com.jakeashdown.restbackenddemo.boundary;

import com.jakeashdown.restbackenddemo.model.Task;

import java.util.List;

public interface TaskBoundary {
    public List<Task> getAllTasks();
}
