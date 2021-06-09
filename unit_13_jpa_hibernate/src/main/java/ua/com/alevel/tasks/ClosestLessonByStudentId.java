package ua.com.alevel.tasks;

import org.hibernate.Session;
import ua.com.alevel.entity.Lesson;
import ua.com.alevel.entity.Student;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Comparator;
import java.util.Date;
import java.util.Set;

public class ClosestLessonByStudentId {

    private final Session session;

    public ClosestLessonByStudentId(Session session) {
        this.session = session;
    }

    public void findClosetLessonForStudentByStudentId(Integer studentId) {

        Student student = session.load(Student.class, studentId);

        Set<Lesson> lessonSet = student
                .getGroupStudent()
                .getLessonSet();

       Lesson closetLesson = lessonSet
                .stream()
                .filter(l -> l.getTime().getEpochSecond() > Instant.now().getEpochSecond())
                .min(Comparator.comparing(Lesson::getTime))
                .get();



        Date myDate = Date.from(closetLesson.getTime());


        SimpleDateFormat formatterForTime = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat formatterForDate = new SimpleDateFormat("dd.MM.yyyy");
        String formattedDate = formatterForDate.format(myDate);
        String formattedTime = formatterForTime.format(myDate);

        System.out.println("Closet lesson for student with id " + studentId + ":");
        System.out.println("Date - " + formattedDate);
        System.out.println("Time - " + formattedTime);
        System.out.println("Teacher - " + closetLesson.getTopic().getLecturer().getName());
        System.out.println("Topic - " + closetLesson.getTopic().getName());
    }
}
