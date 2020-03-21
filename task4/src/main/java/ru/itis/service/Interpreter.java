package ru.itis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.model.Note;

import java.util.Arrays;

@Component
public class Interpreter {
    @Autowired
    RepositoryHandler repositoryHandler;

    public void handle_input(String string) {
        String[] param = string.split(" ");
        switch (param[0]) {
            case "add":
                repositoryHandler.add(Note.builder().message(Arrays.stream(Arrays.copyOfRange(param, 1, param.length)).reduce((s, s2) -> s + " " + s2).get()).build());
                break;
            case "out":
                System.out.println(repositoryHandler.out());
                break;
            case "stop":
                System.exit(0);
                break;
        }
    }
}
