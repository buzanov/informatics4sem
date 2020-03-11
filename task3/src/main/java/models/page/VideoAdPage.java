package models.page;

import models.Browser;

public class VideoAdPage extends AdPage {
    @Override
    protected Page goToAd() {
        return null;
    }

    @Override
    public boolean goToPosition(int position) {
        currentPosition = position;
        return true;
    }
}
