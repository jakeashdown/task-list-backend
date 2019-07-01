package com.jakeashdown.restbackenddemo.boundary;

import com.jakeashdown.restbackenddemo.model.Task;
import com.jakeashdown.restbackenddemo.model.TaskWithoutId;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class TaskBoundaryImplInMemory implements TaskBoundary {

    private final AtomicLong counter = new AtomicLong();

    private Map<BigInteger, Task> tasks = new HashMap();

    public TaskBoundaryImplInMemory() {
        final Task task1 = new Task(
                BigInteger.valueOf(counter.incrementAndGet()),
                "Get a job",
                "Hopefully something you actually like"
        );
        final Task task2 = new Task(
                BigInteger.valueOf(counter.incrementAndGet()),
                "Climb 7a",
                "Maybe 'Shakira'"
        );

        tasks.put(task1.getId(), task1);
        tasks.put(task2.getId(), task2);
    }

    @Override
    public List<Task> getAllTasks() {
        return new ArrayList(tasks.values());
    }

    @Override
    public Optional<Task> getTaskForId(BigInteger taskId) {
        return Optional.ofNullable(tasks.get(taskId));
    }

    @Override
    public BigInteger createTaskReturningId(String title, String description) {
        final Task task = new Task(
            BigInteger.valueOf(counter.incrementAndGet()),
            title,
            description
            );
        tasks.put(task.getId(), task);
        return task.getId();
    }
}
