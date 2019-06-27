package com.jakeashdown.restbackenddemo.boundary;

import com.jakeashdown.restbackenddemo.model.Task;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TaskBoundaryImplInMemory implements TaskBoundary {
    @Override
    public List<Task> getAllTasks() {
        // TODO: replace with actual data
        Task task = new Task(
                1,
                "Get a job",
                new ArrayList<>()
        );
        List tasks = new ArrayList();
        tasks.add(task);
        return tasks;
    }
}
