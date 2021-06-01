package ua.com.alevel.mapper;

import lombok.var;
import ua.com.alevel.Keys;
import ua.com.alevel.table.Table;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;


public class PropertyBind {

    public <T> List<T> initializeProperties(Class<T> tClass, Table table) {
        List<T> tList = new ArrayList<>();
        T object;
        for (int i = 0; i < table.sizeOfRows() - 1; i++) {
            try {
                Constructor<T> constructor = tClass.getConstructor();
                object = constructor.newInstance();
                for (Field field : tClass.getDeclaredFields()) {
                    if (field.isAnnotationPresent(Keys.class)) {
                        Keys property = field.getAnnotation(Keys.class);
                        if (property == null) continue;
                        var value = table.getCell(i, property.value());
                        if (value == null) continue;
                        field.setAccessible(true);
                        var type = field.getType().getName();
                        if (int.class.getName().equals(type)) {
                            field.setInt(object, Integer.parseInt(value));
                        } else if (long.class.getName().equals(type)) {
                            field.setLong(object, Long.parseLong(value));
                        } else if (double.class.getName().equals(type)) {
                            field.setDouble(object, Double.parseDouble(value));
                        } else if (boolean.class.getName().equals(type)) {
                            field.setBoolean(object, Boolean.parseBoolean(value));
                        } else if (String.class.getName().equals(type)) {
                            field.set(object, value);
                        } else {
                            System.out.println("This type is not provided!");
                        }
                    }
                }
                tList.add(object);
            } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return tList;
    }
}