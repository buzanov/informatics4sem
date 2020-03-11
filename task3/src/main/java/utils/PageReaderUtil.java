package utils;

import models.page.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PageReaderUtil {
    FileWriter fileWriter;
    Scanner sc;

    public PageReaderUtil(String path) {
        try {
            fileWriter = new FileWriter(path, true);
            sc = new Scanner(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String, Page> readFromFile() {
        Map<String, Page> map = new HashMap<>();
        int a = sc.nextInt();
        for (int i = 0; i < a; i++) {
            String[] line = sc.nextLine().split(";");
            switch (line[0]) {
                case ("Common"): {
                    CommonPage page = new CommonPage();
                    page.setName(line[1]);
                    page.setLink(line[2]);
                    map.put(page.getName(), page);
                    break;
                }
                case ("TextAdPage"): {
                    TextAdPage page = new TextAdPage();
                    page.setName(line[1]);
                    page.setLink(line[2]);
                    map.put(page.getName(), page);
                    break;
                }
                case ("AdVideoPage"): {
                    VideoAdPage page = new VideoAdPage();
                    page.setName(line[1]);
                    page.setLink(line[2]);
                    map.put(page.getName(), page);
                    break;
                }
            }
        }
        for (int i = 0; i < a; i++) {
            String line = sc.nextLine();
            String[] arr = line.split(":");
            for (String s : arr[1].split(" ")) {
                map.get(arr[0]).getLinks().put(s, map.get(s));
            }
        }
        sc = new Scanner("advertisement.txt");
        while (sc.hasNextLine()) {
            String[] in = sc.nextLine().split(":");
            for (String s : in[2].split(" ")) {
                String[] m = s.split("=");
                ((AdPage) map.get(in[1])).getAds().put(Integer.parseInt(m[1]), map.get(m[0]));
            }
        }
        return map;
    }
}
