package ua.com.alevel.dao.imp;

import ua.com.alevel.dao.BookDAO;
import ua.com.alevel.db.DBLibrary;
import ua.com.alevel.entity.Book;

import java.util.List;

public class BookImp implements BookDAO {
    @Override
    public void create(Book data) {
        DBLibrary.getInstance().creatBook(data);
    }

    @Override
    public Book readId(int id) {
        return DBLibrary.getInstance().findBook(id);
    }

    @Override
    public List<Book> read() {
        return DBLibrary.getInstance().findAllBook();
    }

    @Override
    public void update(Book data) {
        DBLibrary.getInstance().updateBook(data);
    }

    @Override
    public void delete(int id) {
        DBLibrary.getInstance().deleteBook(id);
    }
}
