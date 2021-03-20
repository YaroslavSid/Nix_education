package ua.com.alevel.factory;

import org.reflections.Reflections;
import ua.com.alevel.CalcLibService;

import java.util.Set;

public class CalcFactory {


    private static CalcFactory instance;
    public Reflections reflections;
    private Set<Class<? extends CalcLibService>> calcLibServices;

    private CalcFactory() {
        reflections = new Reflections("ua.com.alevel");
        calcLibServices = reflections.getSubTypesOf(CalcLibService.class);
        calcLibServices.forEach(System.out::println);
    }

    public static CalcFactory getInstance() {
        if (instance == null) {
            instance = new CalcFactory();
        }
        return instance;
    }

    public CalcLibService getCalcLibService() {

        for (Class<? extends CalcLibService> calc : calcLibServices) {
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
