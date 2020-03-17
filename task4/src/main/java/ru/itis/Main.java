package ru.itis;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.itis.config.ApplicationContextConfig;
import ru.itis.service.Interpreter;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationContextConfig.class);
        Interpreter interpreter = (Interpreter) context.getBean("interpreter");
        Scanner sc = new Scanner(System.in);
        while (true) {
            interpreter.handle_input(sc.nextLine());
        }
    }
}
