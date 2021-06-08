package ua.com.alevel.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "topic")
public class Topic extends FieldEntity {

    @OneToOne(mappedBy = "topic")
    private Lesson lesson;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "lecturer_id")
    private Lecturer lecturer;

    public Topic(){}

    public Topic( String name) {
        super(name);

    }

}
