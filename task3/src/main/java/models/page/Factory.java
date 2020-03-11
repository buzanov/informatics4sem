package models.page;

import context.Context;

import java.lang.reflect.InvocationTargetException;

public class Factory {
    public static Page createCommonPage(String name, Class<? extends Page> type, String... links) {
        try {
            Page page = type.getConstructor().newInstance();
            page.setName(name);
            for (String link : links) {
                page.getLinks().put(link, Context.getPage(link));
            }
            return page;
        } catch (InstantiationException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
