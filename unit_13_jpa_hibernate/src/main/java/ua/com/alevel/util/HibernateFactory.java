package ua.com.alevel.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ua.com.alevel.entity.*;
import ua.com.alevel.entity.number.entity.Course;
import ua.com.alevel.entity.number.entity.Mark;

import java.io.InputStream;

public class HibernateFactory {

    private static HibernateFactory hibernateFactory;
    private  static SessionFactory sessionFactory;

    private HibernateFactory(){}

    public static SessionFactory getSessionFactory(){
        if (sessionFactory == null){
            try {
                sessionFactory = new Configuration()
                        .configure()
                        .addAnnotatedClass(Topic.class)
                        .addAnnotatedClass(Course.class)
                        .addAnnotatedClass(Lecturer.class)
                        .addAnnotatedClass(Mark.class)
                        .addAnnotatedClass(Student.class)
                        .addAnnotatedClass(Lesson.class)
                        .addAnnotatedClass(Group.class)
                        .buildSessionFactory();
            }catch (Exception e){
                System.out.println("Problem with configuration " + e);
            }
        }
        return sessionFactory;
    }
}
