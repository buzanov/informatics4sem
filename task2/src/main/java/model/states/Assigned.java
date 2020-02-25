package model.states;

import model.State;
import model.Task;

public class Assigned extends State {

    public Assigned(Task task) {
        super(task);
    }

    @Override
    public void up(String... args) {
        getContext().setState(InProgress.class);
    }

    @Override
    public void down(String... args) {
        getContext().setDeveloperId(null);
        getContext().setState(Open.class);
    }
}
