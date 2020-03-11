package models.page;

public class TextAdPage extends AdPage {
    @Override
    protected Page goToAd() {
        return null;
    }

    @Override
    public boolean goToPosition(int position) {
        return false;
    }
}
