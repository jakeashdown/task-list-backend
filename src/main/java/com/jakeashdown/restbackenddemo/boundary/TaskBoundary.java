package com.jakeashdown.restbackenddemo.boundary;

import com.jakeashdown.restbackenddemo.model.TaskWithId;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface TaskBoundary {
    public List<TaskWithId> selectAllTasks();
    public Optional<TaskWithId> selectTaskForId(BigInteger taskId);
    public BigInteger insertTaskReturningId(String title, String description);
    public void createOrUpdateTask(TaskWithId task);
}
