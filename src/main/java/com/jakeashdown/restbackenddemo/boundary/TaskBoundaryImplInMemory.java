package com.jakeashdown.restbackenddemo.boundary;

import com.jakeashdown.restbackenddemo.model.Task;
import com.jakeashdown.restbackenddemo.model.TaskWithId;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class TaskBoundaryImplInMemory implements TaskBoundary {

    private final AtomicLong counter = new AtomicLong();

    private Map<BigInteger, TaskWithId> tasks = new HashMap();

    public TaskBoundaryImplInMemory() {
        final TaskWithId task1 = new TaskWithId(
                BigInteger.valueOf(counter.incrementAndGet()),
                "Get a job",
                "Hopefully something you actually like"
        );
        final TaskWithId task2 = new TaskWithId(
                BigInteger.valueOf(counter.incrementAndGet()),
                "Climb 7a",
                "Maybe 'Shakira'"
        );

        tasks.put(task1.getId(), task1);
        tasks.put(task2.getId(), task2);
    }

    @Override
    public List<TaskWithId> getAllTasks() {
        return new ArrayList(tasks.values());
    }

    @Override
    public Optional<TaskWithId> getTaskForId(BigInteger taskId) {
        return Optional.ofNullable(tasks.get(taskId));
    }

    @Override
    public BigInteger createTaskReturningId(String title, String description) {
        final TaskWithId task = new TaskWithId(
            BigInteger.valueOf(counter.incrementAndGet()),
            title,
            description
            );
        tasks.put(task.getId(), task);
        return task.getId();
    }
}
