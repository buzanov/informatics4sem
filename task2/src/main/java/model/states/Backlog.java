package model.states;

import model.State;
import model.Task;

public class Backlog extends State {

    public Backlog(Task task) {
        super(task);
    }

    @Override
    public void up(String... args) {
        task.setState(Open.class);
    }

    @Override
    public void down(String... args) {
        //nothing
    }
}
