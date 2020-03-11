import models.Tree;
import parser.JsonParser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Saver {
    private static Saver saver;
    private static FileWriter writer;
    private static Scanner sc;
    private static File file;


    public static Saver getInstance() {
        return saver == null ? saver = new Saver() : saver;
    }

    private Saver() {
        try {
            file = new File("storage.txt");
            writer = new FileWriter("storage.txt", true);
            sc = new Scanner(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void save(Tree tree) {
        try {
            writer.write(JsonParser.getInstance().write(tree) + "\n");
            writer.flush();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public Tree read(int index) {
        int i = 1;
        String line;
        while (sc.hasNextLine()) {
            line = sc.nextLine();
            if (i == index) {
                return JsonParser.getInstance().read(line);
            }
            i++;
        }
        return null;
    }
}