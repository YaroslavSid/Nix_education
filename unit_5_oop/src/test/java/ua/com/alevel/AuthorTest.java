package ua.com.alevel;

import org.apache.commons.collections.CollectionUtils;
import org.junit.jupiter.api.*;
import ua.com.alevel.dao.AuthorDAO;
import ua.com.alevel.dao.imp.AuthorImp;
import ua.com.alevel.entity.Author;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AuthorTest {

    private static final AuthorDAO authorImp = new AuthorImp();
    private static final String name = "Name";
    private static final String lastName = "LastName";
    private static final String bookName = "BookName";


    @BeforeAll
    public static void init() {
        for (int i = 0; i < 10; i++) {
            Set<String> set = new HashSet<>();
            set.add(bookName + i);
            Author author = new Author();
            author.setNameAuthor(name + i);
            author.setLastNameAuthor(lastName + i);
            author.setListBooks(set);
            authorImp.create(author);
        }
        Assertions.assertTrue(CollectionUtils.isNotEmpty(authorImp.read()));
    }

    @Test
    @Order(1)
    public void createAuthor() {
        List<Author> authorList = authorImp.read();
        int size = authorList.size();
        Author author = new Author();
        author.setNameAuthor(name);
        author.setLastNameAuthor(lastName);
        author.setListBooks(Collections.singleton("My book"));
        authorImp.create(author);
        author = authorImp.readId(1);
        Assertions.assertNotNull(author);
        Assertions.assertTrue(size != authorImp.read().size());
    }

    @Test
    @Order(2)
    public void update() {
        Author author = authorImp.readId(1);
        author.setNameAuthor("Yaroslav");
        authorImp.update(author);
        author = authorImp.readId(1);
        Assertions.assertEquals("Yaroslav", author.getNameAuthor());
    }

    @Test
    @Order(3)
    public void read() {
        Assertions.assertNotNull(authorImp.read());
    }

    @Test
    @Order(4)
    public void readId() {
        Assertions.assertEquals("Yaroslav", authorImp.readId(1).getNameAuthor());
    }

    @Test
    @Order(5)
    public void delete() {
        List<Author> authorList = authorImp.read();
        int size = authorList.size();
        authorImp.delete(1);
        Assertions.assertTrue(size != authorImp.read().size());
    }
}

