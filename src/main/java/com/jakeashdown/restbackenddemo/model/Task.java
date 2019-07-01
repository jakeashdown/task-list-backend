package com.jakeashdown.restbackenddemo.model;

import java.math.BigInteger;

public class Task {
    protected String title;
    protected String description; // TODO: make optional
    // TODO: add steps and status

    public Task() {}

    public Task(String title, String description) {
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

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Task) {
            Task otherTask = (Task) obj;
            return title.equals(otherTask.title )
                    && description.equals(otherTask.description);
        } else {
            return false;
        }
    }
}
