package ua.com.alevel.db;



import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;

import java.util.ArrayList;
import java.util.List;

public class DBLibrary {
    private static DBLibrary dbLibrary;

    private final List<Book> books;
    private final List<Author> authors;

    private DBLibrary(){
        this.books = new  ArrayList<>();
        this.authors = new ArrayList<>();
    }

    public static DBLibrary getInstance(){
        if(dbLibrary == null){
            dbLibrary = new DBLibrary();
        }
        return dbLibrary;
    }

    public void creatBook(Book book){
        int size = books.size();
        int id = size + 1;
        book.setId(id);
        books.add(book);
    }

    public List<Book> findAllBook() {
        return books;
    }

    public Book findBook(int id) {
        return books.stream().filter(b -> b.getId() == id).findFirst().get();
    }

    public  void deleteBook(int id){
        books.removeIf(book -> book.getId() == id);
    }

    public void updateBook(Book book){
        Book current = findBook(book.getId());
        current.setNameBook(book.getNameBook());
        current.setListAuthors(book.getListAuthors());
    }

    //----------------------------------------------------------------------------------


    public void creatAuthor(Author author){
        int size = authors.size();
        int id = size + 1;
        author.setId(id);
        authors.add(author);
    }

    public List<Author> findAllAuthors() {
        return authors;
    }

    public Author findAuthor(int id) {
        return authors.stream().filter(a -> a.getId() == id).findFirst().get();
    }

    public  void deleteAuthor(int id){
        authors.removeIf(author -> author.getId() == id);

    }

    public void updateAuthor( Author author){
        Author current = findAuthor(author.getId());
        current.setNameAuthor(author.getNameAuthor());
        current.setLastNameAuthor(author.getLastNameAuthor());
        current.setListBooks(author.getListBooks());
    }









}
