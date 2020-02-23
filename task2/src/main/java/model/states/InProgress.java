package model.states;

import model.State;
import model.Task;

public class InProgress extends State {

    public InProgress(Task task) {
        super(task);
    }

    @Override
    public void up(String... args) {
        task.setState(Resolved.class);
    }

    @Override
    public void down(String... args) {
        task.setState(Assigned.class);
    }
}
