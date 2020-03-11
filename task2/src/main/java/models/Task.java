package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import models.states.Draft;

import java.lang.reflect.InvocationTargetException;

@Data
@AllArgsConstructor
public class Task implements ITask {
    State state;
    Integer id;
    Integer developerId;
    Integer testerId;
    String text;
    String error;

    public Task() {
        this.state = new Draft(this);
    }

    @Override
    public String toString() {
        return "Task{" +
                "state=" + state +
                ", id=" + id +
                ", developerId=" + developerId +
                ", testerId=" + testerId +
                ", text='" + text + '\'' +
                ", error='" + error + '\'' +
                '}';
    }

    public void setState(Class<? extends State> aClass) {
        try {
            state = aClass.getConstructor(Task.class).newInstance(this);
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void up(String... args) {
        state.up(args);
    }

    public void down(String... args) {
        state.down(args);
    }

    public static Builder builder() {
        return (new Task()).new Builder();
    }

    public Task copy() {
        return new Task(state.copy(this), null, developerId, testerId, text, error);
    }


    //Builder
    public class Builder {

        private Builder() {
        }

        public Builder id(Integer id) {
            Task.this.id = id;
            return this;
        }

        public Builder state(State state) {
            Task.this.state = state;
            return this;
        }

        public Builder devId(Integer id) {
            Task.this.developerId = id;
            return this;
        }

        public Builder testerId(Integer id) {
            Task.this.testerId = id;
            return this;
        }

        public Builder text(String text) {
            Task.this.text = text;
            return this;
        }

        public Builder error(String error) {
            Task.this.error = error;
            return this;
        }

        public Task build() {
            Task.this.state = new Draft(Task.this);
            return Task.this;
        }
    }
}
