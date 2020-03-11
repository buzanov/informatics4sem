package models.page;

import java.util.HashMap;

public abstract class Page {
    protected String link;
    protected String name;
    protected HashMap<String, Page> links;
    protected Integer currentPosition = 0;

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

    public abstract void goToPage(String name);

    public abstract void goToLink(String link);
}
