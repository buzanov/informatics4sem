package model.states;

import model.State;
import model.Task;

import java.util.Arrays;

public class Testing extends State {

    public Testing(Task task) {
        super(task);
    }

    @Override
    public void up(String... args) {
        getContext().setState(Closed.class);
        getContext().setTesterId(null);
        getContext().setDeveloperId(null);
    }

    @Override
    public void down(String... args) {
        getContext().setState(Assigned.class);
        getContext().setError(Arrays.stream(args).reduce(String::concat).orElse(""));

    }
}
