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
        task.setState(Closed.class);
        task.setTesterId(null);
        task.setDeveloperId(null);
    }

    @Override
    public void down(String... args) {
        task.setState(Assigned.class);
        task.setError(Arrays.stream(args).reduce(String::concat).orElse(""));

    }
}
