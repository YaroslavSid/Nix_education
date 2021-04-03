package ua.com.alevel.dao;

import ua.com.alevel.entity.AbstractData;

import java.util.List;

public interface AbstractDAO <T extends AbstractData> {

    void create(T data);
    T readId(int id);
    List<T> read();
    void update(T data);
    void delete(int id);
}
