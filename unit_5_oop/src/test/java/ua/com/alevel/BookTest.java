package ua.com.alevel;

import org.apache.commons.collections.CollectionUtils;
import org.junit.jupiter.api.*;
import ua.com.alevel.dao.BookDAO;
import ua.com.alevel.dao.imp.BookImp;
import ua.com.alevel.entity.Book;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookTest {
    private static final BookDAO bookImp = new BookImp();
    private static final String nameBook = "Name Book";
    private static final String nameAuthor = "Name Author";

    @BeforeAll
    public static void init() {
        for (int i = 0; i < 10; i++) {
            Set<String> set = new HashSet<>();
            set.add(nameAuthor + i);
            Book book = new Book();
            book.setNameBook(nameBook);
            book.setListAuthors(set);
            bookImp.create(book);
        }
        Assertions.assertTrue(CollectionUtils.isNotEmpty(bookImp.read()));
    }

    @Test
    @Order(1)
    public void createBook() {
        List<Book> bookList = bookImp.read();
        int size = bookList.size();
        Book book = new Book();
        book.setNameBook(nameBook);
        book.setListAuthors(Collections.singleton("New Author"));
        bookImp.create(book);
        book = bookImp.readId(1);
        Assertions.assertNotNull(book);
        Assertions.assertTrue(size != bookImp.read().size());
    }

    @Test
    @Order(2)
    public void update() {
        Book book = bookImp.readId(9);
        book.setNameBook("New Book");
        bookImp.update(book);
        book = bookImp.readId(9);
        Assertions.assertEquals("New Book", book.getNameBook());
    }

    @Test
    @Order(3)
    public void read() {
        Assertions.assertNotNull(bookImp.read());
    }

    @Test
    @Order(4)
    public void readId() {
        Assertions.assertEquals("New Book", bookImp.readId(9).getNameBook());
    }

    @Test
    @Order(5)
    public void delete() {
        List<Book> booksList = bookImp.read();
        int size = booksList.size();
        bookImp.delete(1);
        Assertions.assertTrue(size != bookImp.read().size());
    }
}
