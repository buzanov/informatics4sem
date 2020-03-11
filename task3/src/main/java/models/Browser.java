package models;

import context.Context;
import models.page.AdPage;
import models.page.Page;
import models.page.TextAdPage;

import java.util.ArrayDeque;
import java.util.Deque;

public class Browser {
    private Deque<Snapshot> history;
    private Deque<Snapshot> forward;
    private Page current;

    public Browser() {
        history = new ArrayDeque<>();
        forward = new ArrayDeque<>();
    }

    private void save() {
        history.add(current.snapshot());
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


    public void goToAd() {
        if (current instanceof AdPage) {
            goToPage(((AdPage) current).getAds().get(current.getCurrentPosition()));
        } else {
            System.out.println("Sorry, there is no advertisement");
        }
    }

    public void goToPage(Page page) {
        history.addLast(current.snapshot());
        current = page;
        forward.clear();
        page.setWatched(true);
    }

    public void goToPage(String name) {
        Page page = current.getLinks().get(name);
        if (page == null) {
            System.out.println("Page not found");
        } else {
            history.addLast(current.snapshot());
            current = page;
            forward.clear();
            page.setWatched(true);
        }
    }

    public void goToLink(String name) {
        if (current != null) {
            history.addLast(current.snapshot());
        }
        current = Context.getPage(name);
        current.setWatched(true);
        forward.clear();
    }

    public void changePosition(int position) {
        if (current instanceof AdPage) {
            if (current instanceof TextAdPage) {
                current.goToPosition(position);
            } else {
                Page page = ((AdPage) current).getAds().get(position - 1);
                if (page != null && !page.isWatched()) {
                    goToPage(page.getName());
                } else {
                    current.goToPosition(position);
                }
            }
        } else {
            current.goToPosition(position);
        }
    }
}
