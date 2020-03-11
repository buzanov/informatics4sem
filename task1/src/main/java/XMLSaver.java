import models.Tree;
import parser.XMLParser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class XMLSaver {
    private static XMLSaver saver;
    private static FileWriter writer;
    private static Scanner sc;
    private static File file;


    public static XMLSaver getInstance() {
        return saver == null ? saver = new XMLSaver() : saver;
    }

    private XMLSaver() {
        try {
            file = new File("tree.xml");
            writer = new FileWriter("tree.xml", true);
            sc = new Scanner(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void save(Tree tree) {
        try {
            writer.write(XMLParser.getInstance().write(tree));
            writer.flush();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public Tree read() {
        StringBuilder s = new StringBuilder();
        while (sc.hasNextLine()) {
            s.append(sc.nextLine());
        }
        return XMLParser.getInstance().read(s.toString());
    }
}
