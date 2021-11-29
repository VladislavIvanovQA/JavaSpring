package ru.gb;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.gb.config.ApplicationConfig;
import ru.gb.service.ConsoleService;

public class Application {
    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(ApplicationConfig.class);

        ConsoleService cartConsole = context.getBean(ConsoleService.class);
        cartConsole.run();
    }
}
