package com.jakeashdown.restbackenddemo.model;

import java.math.BigInteger;

public class Task {
    private BigInteger id;
    private String title;
    private String description; // TODO: make optional
    // TODO: add steps and status

    public Task(BigInteger id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
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
