package com.jakeashdown.restbackenddemo.model;

// TODO: use inheritance
public class TaskWithoutId {
    private String title;
    private String description;

    public TaskWithoutId() {}

    public TaskWithoutId(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}