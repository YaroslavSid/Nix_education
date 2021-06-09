package ua.com.alevel;

import org.hibernate.Session;
import ua.com.alevel.tasks.BestLecturerGroup;
import ua.com.alevel.tasks.ClosestLessonByStudentId;
import ua.com.alevel.util.HibernateFactory;


public class CompletingTasks {
    private  final  static Integer idStudent = 1;
    private  final  static Integer idLecturer = 1;

    public static void main(String[] args) {
        ClosestLessonByStudentId closestL = new ClosestLessonByStudentId(
                HibernateFactory.getSessionFactory().getCurrentSession());

        BestLecturerGroup bestLecturerGroup = new BestLecturerGroup(
                HibernateFactory.getSessionFactory().getCurrentSession());

        // ------------------------------------------------------------------------------

        try(Session session  = HibernateFactory.getSessionFactory().getCurrentSession()) {
            session.getTransaction().begin();

            closestL.findClosetLessonForStudentByStudentId(idStudent);
            System.out.println("\n");
            bestLecturerGroup.findBestGroup(idLecturer);

            session.getTransaction().commit();
        }

    }
}
