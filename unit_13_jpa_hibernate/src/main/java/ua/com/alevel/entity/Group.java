package ua.com.alevel.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Setter
@Getter
@Table(name = "group_s")
public class Group extends FieldEntity{
    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private Set<Lesson> lessonSet;

    @OneToMany(mappedBy = "groupStudent", cascade = CascadeType.ALL)
    private List<Student> student;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Lecturer lecturerSet;

    public Group(String name) {
        super(name);
    }

    public Group() {
    }

}

