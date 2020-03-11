package models.page;

import context.Context;
import models.Browser;

public class CommonPage extends Page {

    public CommonPage() {
    }

    @Override
    public boolean goToPosition(int position) {
        currentPosition = position;
        return true;
    }
}
