package ua.com.alevel.entity.number.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public abstract class FieldNumberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Integer id;

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

    public  FieldNumberEntity() {
        this.createTime = new Date();
        this.updateTime = new Date();
    }
}
