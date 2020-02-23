package model.states;

import model.State;
import model.Task;

public class Closed extends State {

    public Closed(Task task) {
        super(task);
    }

    @Override
    public void up(String... args) {
        //nothing
    }

    @Override
    public void down(String... args) {
        //nothing
    }
}
