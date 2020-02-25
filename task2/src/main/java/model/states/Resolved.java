package model.states;

import model.State;
import model.Task;

public class Resolved extends State {

    public Resolved(Task task) {
        super(task);
    }

    @Override
    public void up(String... args) {
        getContext().setTesterId(Integer.parseInt(args[0]));
        getContext().setState(Testing.class);
    }

    @Override
    public void down(String... args) {
        getContext().setState(InProgress.class);
    }
}
