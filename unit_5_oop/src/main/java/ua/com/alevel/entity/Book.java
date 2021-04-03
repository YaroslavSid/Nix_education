package ua.com.alevel.entity;

import java.util.HashSet;
import java.util.Set;

public class Book extends AbstractData {

    private String nameBook;
    private Set<String> listAuthors = new HashSet<>();

    @Override
    public String toString() {
        return "Book{" +"id="+ super.getId() +
                ", nameBook=" + nameBook  +
                ", listAuthors=" + listAuthors +
                '}';
    }

    public Book() {
        super();
    }

    public Set<String> getListAuthors() {
        return listAuthors;
    }

    public void setListAuthors(Set<String> listAuthors) {
        this.listAuthors = listAuthors;
    }

    public void setNameBook(String nameBook) {
        this.nameBook = nameBook;
    }


    public String getNameBook() {
        return nameBook;
    }


}
