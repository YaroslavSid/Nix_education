package ua.com.alevel.entity.number.entity;

import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.entity.Lesson;

import javax.persistence.*;
import java.util.Set;


@Setter
@Getter
@Entity
@Table
public class Course extends FieldNumberEntity {

    @Column(name = "course", nullable = false)
    private Integer numberCourse;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private Set<Lesson> lesson;

    public Course() {
    }

    public Course(Integer course) {
        super();
        this.numberCourse = course;
    }
}
