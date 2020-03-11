package models;

import service.Logger;

import java.util.Arrays;

public class LoggedTask implements ITask {
    Task task;

    public LoggedTask(Task task) {
        this.task = task;
    }

    @Override
    public void setState(Class<? extends State> aClass) {
        this.task.setState(aClass);
    }

    @Override
    public void up(String... args) {
        Logger.getInstance().log(task.toString() + " up(" + Arrays.toString(args) + ")");
        task.up(args);
        Logger.getInstance().log("and became to " + task.toString());
    }

    @Override
    public void down(String... args) {
        Logger.getInstance().log(task.toString() + " down(" + Arrays.toString(args) + ")");
        task.down(args);
        Logger.getInstance().log("and became to " + task.toString());
    }

    @Override
    public ITask copy() {
        return new LoggedTask(task.copy());
    }

    @Override
    public void setId(Integer id) {
        task.setId(id);
    }
}
