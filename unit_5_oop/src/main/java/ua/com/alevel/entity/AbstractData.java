package ua.com.alevel.entity;

import java.util.Date;

public class AbstractData {
    private int id;
    private Date created;

    public AbstractData() {
        this.created = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
