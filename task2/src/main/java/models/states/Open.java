package models.states;

import models.State;
import models.Task;

public class Open extends State {

    public Open(Task task) {
        super(task);
    }

    @Override
    public void up(String... args) {
        getContext().setDeveloperId(Integer.parseInt(args[0]));
        getContext().setState(Assigned.class);
    }

    @Override
    public void down(String... args) {
        getContext().setState(Backlog.class);
    }
}
