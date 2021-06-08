package ua.com.alevel;

import org.hibernate.Session;
import sun.security.pkcs11.wrapper.Functions;
import ua.com.alevel.entity.Group;
import ua.com.alevel.entity.Lecturer;
import ua.com.alevel.entity.Lesson;
import ua.com.alevel.entity.Student;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BestLecturerGroup {

    private final Session session;

    public BestLecturerGroup(Session session) {
        this.session = session;
    }

    public void findBestGroup(Integer lecturerId) {

        Lecturer lecturer = session.load(Lecturer.class, lecturerId);
        Set<Group> lecturerGroups = lecturer.getGroupLecturer();

        Map<Group, List<Student>> groupToStudents = lecturerGroups.stream()
                .collect(Collectors.toMap(Function.identity(), Group::getStudent));

        Map<Group, Double> groupToAverageMark = groupToStudents.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        entry -> entry.getValue()
                                .stream()
                                .mapToInt(student -> student.getMark().getMark())
                                .average()
                                .getAsDouble())
                );

        Optional<Map.Entry<Group, Double>> bestGroup = groupToAverageMark.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .skip(groupToAverageMark.size() - 1)
                .findFirst();

        if (bestGroup.isPresent()) {
            Map.Entry<Group, Double> best = bestGroup.get();

            String course = best.getKey()
                    .getLessonSet()
                    .stream()
                    .filter(lesson -> lesson.getCourse()  != null)
                    .findFirst()
                    .get()
                    .getCourse()
                    .getName();

            System.out.println("Best group name: " + best.getKey().getName() + " on " + course +
                    " course with average mark " + best.getValue());
        }
    }
}
