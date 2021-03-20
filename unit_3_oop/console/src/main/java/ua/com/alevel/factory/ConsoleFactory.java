package ua.com.alevel.factory;

import org.reflections.Reflections;
import ua.com.alevel.ConsoleService;


import java.util.Set;

public class ConsoleFactory {


    private static ConsoleFactory instance;
    public Reflections reflections;
    private Set<Class<? extends ConsoleService>> calcLibServices;

    private ConsoleFactory() {
        reflections = new Reflections("ua.com.alevel");
        calcLibServices = reflections.getSubTypesOf(ConsoleService.class);
        calcLibServices.forEach(System.out::println);
    }

    public static ConsoleFactory getInstance() {
        if (instance == null) {
            instance = new ConsoleFactory();
        }
        return instance;
    }

    public ConsoleService getCalcLibService() {

        for (Class<? extends ConsoleService> calc : calcLibServices) {
            if (!calc.isAnnotationPresent(Deprecated.class)) {
                try {
                    return calc.getDeclaredConstructor().newInstance();
                } catch (Exception e) {
                    throw new RuntimeException();
                }
            }
        }
        throw new RuntimeException();
    }
}
