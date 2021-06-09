package ua.com.alevel.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public abstract class FieldEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    private Date createTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date updateTime;

    @PreUpdate
    public void onPreUpdate() {
        this.updateTime = new Date();
    }

    public  FieldEntity(String name) {
        this.createTime = new Date();
        this.updateTime = new Date();
        this.name = name;
    }

    public  FieldEntity() {
        this.createTime = new Date();
        this.updateTime = new Date();
    }
}
