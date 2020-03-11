package models;

import models.page.Page;

public class Snapshot implements Momentum {
    Page state;
    String name;

    public Snapshot(Page state, String name) {
        this.state = state;
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Page getState() {
        return state;
    }
}
