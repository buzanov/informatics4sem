package context;

import models.page.Page;
import utils.PageReaderUtil;

import java.util.Map;

public class Context {

    static Map<String, Page> pages;

    public static void connection() {
        PageReaderUtil pageReader = new PageReaderUtil("pages.txt");
        pages = pageReader.readFromFile();
    }

    public static Map<String, Page> getPages() {
        return pages;
    }

    public static Page getPage(String url) {
        Page page;
        if ((page = pages.get(url)) != null) {
            return page;
        } else {
            System.out.println("Error 404");
            return null;
        }


    }
}
