import models.Browser;
import models.page.Factory;
import models.page.Page;

import java.util.Arrays;
import java.util.Scanner;

public class Interpreter {
    static Browser browser;

    public static void main(String[] args) {
        browser = new Browser();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            handle_message(sc.nextLine());
        }
    }

    private static void handle_message(String line) {
        String[] strings = line.split(" ");
        if (line.startsWith("link")) {
            browser.goToLink(strings[1]);
        } else if (line.startsWith("goto")) {
            browser.goToPage(strings[1]);
        } else if (line.startsWith("back")) {
            browser.back();
        } else if (line.startsWith("forward")) {
            browser.forward();
        } else if (line.startsWith("gotoAd")) {
            browser.goToAd();
        } else if (line.startsWith("gotoPos")) {
            browser.changePosition(Integer.parseInt(strings[1]));
        } else if (line.startsWith("create")) {
            try {
                strings = Arrays.copyOfRange(strings, 2, strings.length);
                Factory.createCommonPage(strings[1], (Class<? extends Page>) Class.forName(strings[2]), strings);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
