package model.states;

import model.State;
import model.Task;

public class Resolved extends State {

    public Resolved(Task task) {
        super(task);
    }

    @Override
    public void up(String... args) {
        task.setTesterId(Integer.parseInt(args[0]));
        task.setState(Testing.class);
    }

    @Override
    public void down(String... args) {
        task.setState(InProgress.class);
    }
}
