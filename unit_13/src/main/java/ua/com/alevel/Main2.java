package ua.com.alevel;

import org.hibernate.Session;
import ua.com.alevel.util.HibernateFactory;


public class Main2 {
    public static void main(String[] args) {
        ClosestLessonByStudentId closestL = new ClosestLessonByStudentId
                (HibernateFactory.getSessionFactory().getCurrentSession());

        BestLecturerGroup bestLecturerGroup = new BestLecturerGroup(
                HibernateFactory.getSessionFactory().getCurrentSession());

        try(Session session  = HibernateFactory.getSessionFactory().getCurrentSession()) {
            session.getTransaction().begin();

            closestL.findClosetLessonForStudentByStudentId(1);
            System.out.println("\n");
            bestLecturerGroup.findBestGroup(1);

            session.getTransaction().commit();
        }

    }
}
