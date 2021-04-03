package ua.com.alevel.entity;

import java.util.HashSet;
import java.util.Set;

public class Author extends AbstractData {

    String nameAuthor;
    String lastNameAuthor;
    Set<String> listBooks = new HashSet<>();


    @Override
    public String toString() {
        return "Author{" + ", id=" + super.getId() +
                ", nameAuthor=" + nameAuthor +
                ", lastNameAuthor=" + lastNameAuthor +
                ", listBooks=" + listBooks +
                '}';
    }


    public Author() {
        super();
    }


    public String getNameAuthor() {
        return nameAuthor;
    }

    public void setNameAuthor(String nameAuthor) {
        this.nameAuthor = nameAuthor;
    }

    public String getLastNameAuthor() {
        return lastNameAuthor;
    }

    public void setLastNameAuthor(String lastNameAuthor) {
        this.lastNameAuthor = lastNameAuthor;
    }

    public Set<String> getListBooks() {
        return listBooks;
    }

    public void setListBooks(Set<String> listBooks) {
        this.listBooks = listBooks;
    }
}
