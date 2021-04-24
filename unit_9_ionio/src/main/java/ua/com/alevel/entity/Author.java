package ua.com.alevel.entity;

import java.util.Arrays;
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

    public String[] toArray(){
        String[]arr = new String[4];

        arr[0] = String.valueOf(getId());
        arr[1] = getNameAuthor();
        arr[2] = getLastNameAuthor();
        arr[3] = String.valueOf(getListBooks());

        return arr;
    }

    public void createAuthor(String[] strings ){
        setId(Integer.parseInt(strings[0]));
        setNameAuthor(strings[1]);
        setLastNameAuthor(strings[2]);
        String books = strings[3];
        String[]names =  books.split(",");
        Set<String> book = new HashSet<>(Arrays.asList(names));
        setListBooks(book);
    }

}
