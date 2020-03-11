package models.page;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AdPage extends Page {
    protected Map<Integer, Page> ads = new HashMap<>();

    public Map<Integer, Page> getAds() {
        return ads;
    }

    protected abstract Page goToAd();
}
