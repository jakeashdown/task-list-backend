package com.jakeashdown.restbackenddemo.boundary;

import com.jakeashdown.restbackenddemo.model.Task;
import com.jakeashdown.restbackenddemo.model.TaskWithoutId;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface TaskBoundary {
    public List<Task> getAllTasks();
    public Optional<Task> getTaskForId(BigInteger taskId);
    public BigInteger createTaskReturningId(String title, String description);
}
