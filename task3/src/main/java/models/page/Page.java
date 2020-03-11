package models.page;

import models.Snapshot;

import java.util.HashMap;

public abstract class Page {
    protected String link;
    protected String name;
    protected HashMap<String, Page> links;
    protected Integer currentPosition = 0;
    protected boolean isWatched;

    public Integer getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(Integer currentPosition) {
        this.currentPosition = currentPosition;
    }

    public boolean isWatched() {
        return isWatched;
    }

    public void setWatched(boolean watched) {
        isWatched = watched;
    }

    public HashMap<String, Page> getLinks() {
        return links;
    }

    public Page() {
        this.links = new HashMap<>();
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract boolean goToPosition(int position);

    public Snapshot snapshot() {
        return new Snapshot(this, this.getName());
    }
}
