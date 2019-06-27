package com.jakeashdown.restbackenddemo.model;

import java.util.List;

public class Task {
    private long id;
    private String title;
    private List<Step> steps;
    // TODO: add status

    public Task(long id, String title, List<Step> steps) {
        this.id = id;
        this.title = title;
        this.steps = steps;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }
}
