package com.jakeashdown.restbackenddemo.boundary;

import com.jakeashdown.restbackenddemo.model.Task;
import com.jakeashdown.restbackenddemo.model.TaskWithId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Component
public class TaskBoundaryImplInMemory implements TaskBoundary {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final AtomicLong idCounter = new AtomicLong();

    private BigInteger getNextId() { return BigInteger.valueOf(idCounter.incrementAndGet()); }

    private Map<BigInteger, Task> tasks = new HashMap();

    public TaskBoundaryImplInMemory() {
        // TODO: remove after testing
        final Task task1 = new Task(
                "Get a job",
                "Hopefully something you actually like"
        );
        final Task task2 = new Task(
                "Climb 7a",
                "Maybe 'Shakira'"
        );

        tasks.put(getNextId(), task1);
        tasks.put(getNextId(), task2);
    }

    @Override
    public List<TaskWithId> selectAllTasks() {
        logger.info("selecting all tasks");
        return tasks.keySet().stream()
                .map(key -> new TaskWithId(key, tasks.get(key)))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<TaskWithId> selectTaskForId(BigInteger taskId) {
        logger.info("selecting task for ID [" + taskId + "]");
        if (tasks.containsKey(taskId)) {
            return Optional.of(new TaskWithId(taskId, tasks.get(taskId)));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public BigInteger insertTaskReturningId(String title, String description) {
        logger.info("inserting task with title [" + title + "]");
        final Task task = new Task(
            title,
            description
            );
        final BigInteger id = getNextId();
        tasks.put(id, task);
        return id;
    }

    @Override
    public void createOrUpdateTask(BigInteger id, Task task) {
        logger.info("creating or updating task with ID [" + id + "]");
        tasks.put(id, new TaskWithId(id, task));
    }

    @Override
    public void deleteTask(BigInteger id) {
        logger.info("deleting task with ID [" + id + "]");
        tasks.remove(id);
    }
}
