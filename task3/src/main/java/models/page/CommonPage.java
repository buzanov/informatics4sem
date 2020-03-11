package models.page;

import context.Context;
import models.Browser;

public class CommonPage extends Page {
    @Override
    public void goToPage(String name) {
        Context.getPage(name);
    }

    @Override
    public void goToLink(String link) {

    }
}
