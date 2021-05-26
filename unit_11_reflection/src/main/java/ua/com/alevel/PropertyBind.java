package ua.com.alevel;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;

public class PropertyBind {
    private Properties findProperties(){
        Properties properties = new Properties();
        try(InputStream inputStream =
                    getClass().getClassLoader().getResourceAsStream("app.properties")) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    public Object  initializeProperties(Object ob) {
        PropertyBind propertyBind = new PropertyBind();
        Properties properties = propertyBind.findProperties();

        Class<?> classOfInstance = ob.getClass();

        for(Field field : classOfInstance.getFields()){
            field.setAccessible(true);
            if(field.isAnnotationPresent(Keys.class)){
                Keys property = field.getAnnotation(Keys.class);
                if (property == null) continue;
                String value = properties.getProperty(property.value());
                if (value == null) continue;

                String type = field.getType().getName();

                try {
                    if (int.class.getName().equals(type)) {
                        field.setInt(ob, Integer.parseInt(value));
                    } else if (long.class.getName().equals(type)) {
                        field.setLong(ob, Long.parseLong(value));
                    } else if (double.class.getName().equals(type)) {
                        field.setDouble(ob, Double.parseDouble(value));
                    } else if (boolean.class.getName().equals(type)) {
                        field.setBoolean(ob, Boolean.parseBoolean(value));
                    } else if (String.class.getName().equals(type)) {
                        field.set(ob, value);
                    }
                    else {
                        System.out.println("This type is not provided!");
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return ob;
    }
}
