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

    public Object  initializeProperties(Object ob) throws IllegalAccessException {
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
                field.set(ob, value);
            }
        }
        return ob;
    }
}
