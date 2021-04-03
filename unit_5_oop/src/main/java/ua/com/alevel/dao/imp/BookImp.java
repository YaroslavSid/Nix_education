package ua.com.alevel.dao.imp;

import ua.com.alevel.dao.BookDAO;
import ua.com.alevel.db.DBLibrary;
import ua.com.alevel.entity.Book;

import java.io.IOException;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class BookImp implements BookDAO {
    private static final Logger logger = Logger.getLogger("MyLog");
    private static FileHandler fh;

    static {
        try {
            if (fh == null) {
                fh = new FileHandler("C:unit_5_oop/src/main/java/logs/log4j.log", true);
                logger.addHandler(fh);
                SimpleFormatter formatter = new SimpleFormatter();
                fh.setFormatter(formatter);
            }
        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void create(Book data) {
        logger.info("Entre method create(Book)...");
        DBLibrary.getInstance().creatBook(data);
        logger.info("Exit from method create(Book)...");
    }

    @Override
    public Book readId(int id) {
        logger.info("Method readId(Book) is execute...");
        return DBLibrary.getInstance().findBook(id);
    }

    @Override
    public List<Book> read() {
        logger.info("Method read(Book) is execute...");
        return DBLibrary.getInstance().findAllBook();
    }

    @Override
    public void update(Book data) {
        logger.info("Entre method update(Book)...");
        DBLibrary.getInstance().updateBook(data);
        logger.info("Exit from method update(Book)...");
    }

    @Override
    public void delete(int id) {
        logger.info("Entre method delete(Book)...");
        DBLibrary.getInstance().deleteBook(id);
        logger.info("Exit from method delete(Book)...");
    }
}
