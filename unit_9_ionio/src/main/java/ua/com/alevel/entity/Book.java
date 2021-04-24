package ua.com.alevel.entity;

import java.util.Arrays;
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

    public String [] toArray() {
        String[] arr = new String[3];

        arr[0] = String.valueOf(getId());
        arr[1] = getNameBook();
        arr[2] = String.valueOf(getListAuthors());

        return arr;
    }

    public void  createBook(String[] strings){
       setId(Integer.parseInt(strings[0]));
       setNameBook(strings[1]);
        String author = strings[2];
        String []names = author.split(",");
        Set<String> authors = new HashSet<>(Arrays.asList(names));
        this.setListAuthors(authors);
    }

}
