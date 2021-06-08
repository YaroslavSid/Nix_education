package ua.com.alevel.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ua.com.alevel.entity.*;

public class HibernateFactory {

    private static HibernateFactory hibernateFactory;
    private  static SessionFactory sessionFactory;

    private HibernateFactory(){}

    public static SessionFactory getSessionFactory(){
        if (sessionFactory == null){
            try {
                sessionFactory = new Configuration()
                        .configure("hibernate.cfg.xml")
                        .addAnnotatedClass(Topic.class)
                        .addAnnotatedClass(Course.class)
                        .addAnnotatedClass(Lecturer.class)
                        .addAnnotatedClass(Mark.class)
                        .addAnnotatedClass(Student.class)
                        .addAnnotatedClass(Lesson.class)
                        .addAnnotatedClass(Group.class)
                        .buildSessionFactory();
            }catch (Exception e){
                System.out.println("ggggg"+e);
            }
        }
        return sessionFactory;
    }
}
