package ua.com.alevel.dao.imp;


import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import ua.com.alevel.dao.AuthorDAO;
import ua.com.alevel.db.DBLibrary;
import ua.com.alevel.entity.Author;

import java.util.List;

public class AuthorImp implements AuthorDAO {
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
    public void create(Author data) {
        logger.info("Entre method create(Author)...");
        DBLibrary.getInstance().creatAuthor(data);
        logger.info("Exit from method create(Author)...");
    }

    @Override
    public Author readId(int id) {
        logger.info("Method readId(Author) is execute...");
        return DBLibrary.getInstance().findAuthor(id);
    }

    @Override
    public List<Author> read() {
        logger.info("Method read(Author) is execute...");
        return DBLibrary.getInstance().findAllAuthors();
    }

    @Override
    public void update(Author data) {
        logger.info("Entre method update(Author)...");
        DBLibrary.getInstance().updateAuthor(data);
        logger.info("Exit from method update(Author)...");
    }

    @Override
    public void delete(int id) {
        logger.info("Entre method delete(Author)...");
        DBLibrary.getInstance().deleteAuthor(id);
        logger.info("Exit from method delete(Author)...");
    }
}
