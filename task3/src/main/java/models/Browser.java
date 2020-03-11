package models;

import context.Context;
import models.page.Page;

import java.util.Deque;

public class Browser {
    private Deque<Snapshot> history;
    private Deque<Snapshot> forward;
    private Page current;

    private void save() {
        history.add(new Snapshot(current, current.getName()));
    }

    private void restore(Momentum momentum) {
        current = momentum.getState();
    }


    public void back() {
        if (!history.isEmpty()) {
            restore(history.pop());
        }
    }

    public void forward() {
        if (!forward.isEmpty()) {
            save();
        }
    }

    public void goToPage(String name) {
        history.add(new Snapshot(current, current.getName()));
        current = current.getLinks().get(name);
    }
}
