package model;

import java.lang.reflect.InvocationTargetException;

public abstract class State {

    protected Task task;

    public abstract void up(String... args);

    public abstract void down(String... args);

    public Task getContext() {
        return task;
    }

    public State(Task task) {
        this.task = task;
    }

    public State copy() {
        try {
            return this.getClass().getConstructor(Task.class).newInstance(task);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
