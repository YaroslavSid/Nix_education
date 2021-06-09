package ua.com.alevel;

import org.hibernate.Session;
import ua.com.alevel.entity.*;
import ua.com.alevel.entity.number.entity.Course;
import ua.com.alevel.entity.number.entity.Mark;
import ua.com.alevel.util.HibernateFactory;

import java.time.Instant;


public class CreateEntity {

    public static void main(String[] args) {
        try (Session session = HibernateFactory.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();

            Course course1 = new Course(1);
            Course course2 = new Course(2);

            session.save(course1);
            session.save(course2);

            // ----------------------------------------------

            Lecturer lecturer1 = new Lecturer("Egor");
            Lecturer lecturer2 = new Lecturer("Dima");
            Lecturer lecturer3 = new Lecturer("Alex");
            Lecturer lecturer4 = new Lecturer("Petr");

            session.save(lecturer1);
            session.save(lecturer2);
            session.save(lecturer3);
            session.save(lecturer4);

            // ----------------------------------------------

            Topic topic1 = new Topic("ZTL");
            Topic topic2 = new Topic("FH");
            Topic topic3 = new Topic("Clinic");
            Topic topic4 = new Topic("BH");

            topic1.setLecturer(lecturer1);
            topic2.setLecturer(lecturer2);
            topic3.setLecturer(lecturer3);
            topic4.setLecturer(lecturer4);

            session.save(topic1);
            session.save(topic2);
            session.save(topic3);
            session.save(topic4);

            // ----------------------------------------------

            Group group1 = new Group("A");
            Group group2 = new Group("B");
            Group group3 = new Group("C");

            group1.setLecturerSet(lecturer1);
            group2.setLecturerSet(lecturer1);
            group3.setLecturerSet(lecturer2);

            session.save(group1);
            session.save(group2);
            session.save(group3);

            // ----------------------------------------------

            Lesson lesson1 = new Lesson("Pharmacy - unit 1");
            lesson1.setTime(Instant.parse("2021-09-01T00:00:00Z"));
            Lesson lesson2 = new Lesson("Pharmacy - unit 2");
            lesson2.setTime(Instant.parse("2021-09-02T00:00:00Z"));
            Lesson lesson3 = new Lesson("Pharmacy - unit 3");
            lesson3.setTime(Instant.parse("2021-09-03T00:00:00Z"));
            Lesson lesson4 = new Lesson("Pharmacy - unit 4");
            lesson4.setTime(Instant.parse("2020-09-11T00:00:00Z"));

            lesson1.setTopic(topic1);
            lesson2.setTopic(topic2);
            lesson3.setTopic(topic3);
            lesson4.setTopic(topic4);
            lesson1.setGroup(group1);
            lesson2.setGroup(group1);
            lesson3.setGroup(group2);
            lesson4.setGroup(group2);
            lesson1.setCourse(course1);
            lesson2.setCourse(course1);
            lesson3.setCourse(course2);
            lesson4.setCourse(course2);

            session.save(lesson1);
            session.save(lesson2);
            session.save(lesson3);
            session.save(lesson4);

            // ----------------------------------------------

            Mark mark1 = new Mark();
            Mark mark2 = new Mark();
            Mark mark3 = new Mark();
            Mark mark4 = new Mark();

            session.save(mark1);
            session.save(mark2);
            session.save(mark3);
            session.save(mark4);

            // ----------------------------------------------

            Student student1 = new Student("YariC");
            Student student2 = new Student("Ed");
            Student student3 = new Student("Atnot");
            Student student4 = new Student("Misha");

            student1.setGroupStudent(group1);
            student2.setGroupStudent(group1);
            student3.setGroupStudent(group2);
            student4.setGroupStudent(group2);

            student1.setMark(mark1);
            student2.setMark(mark2);
            student3.setMark(mark3);
            student4.setMark(mark4);

            session.save(student1);
            session.save(student2);
            session.save(student3);
            session.save(student4);

            // ----------------------------------------------

            mark1 = student1.getMark();
            mark1.setMark(2);
            mark2 = student2.getMark();
            mark2.setMark(5);
            mark3 = student3.getMark();
            mark3.setMark(3);
            mark4 = student4.getMark();
            mark4.setMark(5);

            session.save(mark1);
            session.save(mark2);
            session.save(mark3);
            session.save(mark4);

            // ----------------------------------------------

            session.getTransaction().commit();
        }

    }
}
