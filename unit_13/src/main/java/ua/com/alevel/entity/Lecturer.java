package ua.com.alevel.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Set;

@Getter
@Setter
@Entity
@Table
public class Lecturer extends FieldEntity {

    @OneToMany(mappedBy = "lecturerSet")
    private Set<Group> groupLecturer;

    @OneToOne(mappedBy = "lecturer")
    private Topic topic;


    public Lecturer() {
    }

    public Lecturer(String name) {
        super(name);

    }

}
