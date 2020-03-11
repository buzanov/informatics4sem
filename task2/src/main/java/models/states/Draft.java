package models.states;

import models.State;
import models.Task;

public class Draft extends State {

    public Draft(Task task) {
        super(task);
    }

    @Override
    public void up(String... args) {
        getContext().setState(Open.class);
    }

    @Override
    public void down(String... args) {
        //nothing
    }
}
