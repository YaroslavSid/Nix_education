package ua.com.alevel.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table
public class Mark extends  FieldEntity{

    @Column
    private Integer mark;

    @OneToOne(mappedBy = "mark", cascade = CascadeType.ALL)
    private Student student;

    public Mark(){
    }
//    public Mark(Integer mark){
//        super();
//        this.mark=mark;
//    }


}
