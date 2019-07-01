package com.jakeashdown.restbackenddemo.boundary;

import com.jakeashdown.restbackenddemo.model.TaskWithId;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface TaskBoundary {
    public List<TaskWithId> getAllTasks();
    public Optional<TaskWithId> getTaskForId(BigInteger taskId);
    public BigInteger createTaskReturningId(String title, String description);
}
