package ua.com.alevel.entity;

import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.entity.number.entity.Mark;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "student")
public class Student extends FieldEntity {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "mark_id")
    private Mark mark;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group groupStudent;

    public  Student (){
    }

    public  Student (String name){
    super(name);
    }
}
