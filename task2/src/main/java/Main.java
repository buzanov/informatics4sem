import models.ITask;
import models.LoggedTask;
import models.Task;

import java.util.*;

public class Main {
    static Map<Integer, ITask> tasks;
    static Integer taskNumber;


    public static void main(String[] args) {
        start();
    }

    public static void start() {
        taskNumber = 1;
        tasks = new HashMap<>();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String line = sc.nextLine();
            handleInput(line);

        }
    }

    private static void handleInput(String line) {
        if (line.startsWith("new task")) {
            String[] query = line.split(" ");
            String text = Arrays.asList(query).subList(2, query.length).stream().reduce((o, o1) -> o.concat(" " + o1)).orElse("");
            tasks.put(taskNumber++,
                    new LoggedTask(Task.builder()
                            .text(text)
                            .id(taskNumber)
                            .build()));
        } else if (line.startsWith("up")) {
            String[] query = line.split(" ");
            tasks.get(Integer.parseInt(query[1])).up(Arrays.copyOfRange(query, 2, query.length));
        } else if (line.startsWith("down")) {
            String[] query = line.split(" ");
            tasks.get(Integer.parseInt(query[1])).down(Arrays.copyOfRange(query, 2, query.length));
        } else if (line.startsWith("copy")) {
            String[] query = line.split(" ");
            ITask copy = tasks.get(Integer.parseInt(query[1])).copy();
            copy.setId(taskNumber++);
            tasks.put(taskNumber, copy);
        }
    }
}
