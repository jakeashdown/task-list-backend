package com.jakeashdown.restbackenddemo.model;

import java.math.BigInteger;

public class TaskWithId extends Task {
    private BigInteger id;

    public TaskWithId(BigInteger id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public TaskWithId(BigInteger id, Task task) {
        this.id = id;
        this.title = task.getTitle();
        this.description = task.getDescription();
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }
}
