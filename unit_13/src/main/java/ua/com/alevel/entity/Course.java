package ua.com.alevel.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;


@Setter
@Getter
@Entity
@Table
public class Course extends FieldEntity{
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private Set<Lesson> lesson;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "mark_id")
//    private Mark mark;

    public Course(){
    }
    public Course(String name){
        super(name);
    }
}
