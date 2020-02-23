package model.states;

import model.State;
import model.Task;

public class Open extends State {

    public Open(Task task) {
        super(task);
    }

    @Override
    public void up(String... args) {
        task.setDeveloperId(Integer.parseInt(args[0]));
        task.setState(Assigned.class);
    }

    @Override
    public void down(String... args) {
        task.setState(Backlog.class);
    }
}
