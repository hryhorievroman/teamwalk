package com.xcompany.teamwalk.model;

import java.util.concurrent.atomic.AtomicLong;

public class Team {
    private final String id;
    private final AtomicLong steps;

    public Team(String id) {
        this.id = id;
        this.steps = new AtomicLong(0L);
    }

    public String getId() {
        return id;
    }

    public long getSteps() {
        return steps.get();
    }

    public void addStep(long delta) {
        steps.addAndGet(delta);
    }
}
