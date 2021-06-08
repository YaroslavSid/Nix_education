package ua.com.alevel.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;


@Setter
@Getter
@Entity
@Table
public class Lesson extends FieldEntity{

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "topic_id")
    private Topic topic;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(nullable = false)
    private Instant time;

    public  Lesson(){
    }
    public  Lesson(String name){
    super(name);
    }

}
