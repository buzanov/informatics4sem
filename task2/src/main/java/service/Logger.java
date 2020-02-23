package service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Logger {
    private static Logger myLogger;
    FileWriter fw;

    public static Logger getInstance() {
        return myLogger == null ? myLogger = new Logger() : myLogger;
    }

    private Logger() {
        try {
            File file = new File("log.txt");

            if (file.createNewFile()) {
                System.out.println("File was created.");
            }
            fw = new FileWriter(file, true);

        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void log(String string) {
        try {
            fw.append(string).append(" \n");
            fw.flush();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
