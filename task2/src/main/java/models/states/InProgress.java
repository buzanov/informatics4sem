package models.states;

import models.State;
import models.Task;

public class InProgress extends State {

    public InProgress(Task task) {
        super(task);
    }

    @Override
    public void up(String... args) {
        getContext().setState(Resolved.class);
    }

    @Override
    public void down(String... args) {
        getContext().setState(Assigned.class);
    }
}
