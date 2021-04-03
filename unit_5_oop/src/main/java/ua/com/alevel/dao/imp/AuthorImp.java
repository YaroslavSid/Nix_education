package ua.com.alevel.dao.imp;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.dao.AuthorDAO;
import ua.com.alevel.db.DBLibrary;
import ua.com.alevel.entity.Author;

import java.util.List;

public class AuthorImp implements AuthorDAO {

    private static final Logger logger  = LoggerFactory.getLogger(AuthorImp.class);

    @Override
    public void create(Author data) {
        logger.info("Entre create ...");
        DBLibrary.getInstance().creatAuthor(data);
        logger.info("Exit create ...");
    }

    @Override
    public Author readId(int id) {
        return DBLibrary.getInstance().findAuthor(id);
    }

    @Override
    public List<Author> read() {
        return DBLibrary.getInstance().findAllAuthors();
    }

    @Override
    public void update(Author data) {
        DBLibrary.getInstance().updateAuthor(data);
    }

    @Override
    public void delete(int id) {
        DBLibrary.getInstance().deleteAuthor(id);
    }
}
