package com.jakeashdown.restbackenddemo.boundary;

import com.jakeashdown.restbackenddemo.model.Task;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.*;

@Component
public class TaskBoundaryImplInMemory implements TaskBoundary {

    private Map<BigInteger, Task> tasks = new HashMap();

    public TaskBoundaryImplInMemory() {
        // TODO: replace with actual data
        Task task = new Task(
                BigInteger.ONE,
                "Get a job",
                new ArrayList<>()
        );
        tasks.put(task.getId(), task);
    }

    @Override
    public List<Task> getAllTasks() {
        return new ArrayList(tasks.values());
    }

    @Override
    public Optional<Task> getTaskForId(BigInteger taskId) {
        return Optional.ofNullable(tasks.get(taskId));
    }
}
