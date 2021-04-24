package ua.com.alevel.controller;

import ua.com.alevel.dao.imp.BookImp;
import ua.com.alevel.entity.Book;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BookController {
    BookImp bookImp = new BookImp();

    public void run() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Hello!");
        System.out.println("I help you create Books! Choose act:\n1) create\n2) update\n" +
                "3) delete \n4) read all \n5) read by id\n0) to exit");
        String positionBook;
        while ((positionBook = reader.readLine()) != null) {
            switch (positionBook) {

                case "1":
                    create(reader);
                    break;
                case "4":
                    read();
                    break;
                case "2":
                    update(reader);
                    break;
                case "3":
                    delete(reader);
                    break;
                case "5":
                    readId(reader);
                    break;
                case "0":
                    System.exit(0);
                default:
                    System.out.println("Try again");
            }
            System.out.println("If you want exit, please input 0, else , repeat logic");
        }
        reader.close();

    }

    private void create(BufferedReader reader) throws IOException {
        Book book = new Book();
        System.out.println("Please enter name book...");
        String name = reader.readLine();
        System.out.println("Please enter list authors...\n(Example: Charlotte Bronte, J.R.R. Tolkien, etc.)");

        String []names = reader.readLine().split(",");
        Set<String> authors = new HashSet<>(Arrays.asList(names));
        book.setListAuthors(authors);
        book.setNameBook(name);
        bookImp.create(book);
    }

    private void update(BufferedReader reader) throws IOException {
        System.out.println("Look for book by id:");
        String idS = reader.readLine();
        int id = Integer.parseInt(idS);
        Book book = bookImp.readId(id);
        System.out.println("Please enter name book...");
        String name = reader.readLine();
        System.out.println("Please enter list authors...");

        String []names = reader.readLine().split(",");
        Set<String> authors = new HashSet<>(Arrays.asList(names));
        book.setListAuthors(authors);
        book.setNameBook(name);
        bookImp.update(book);
    }


    private void delete(BufferedReader reader) throws IOException {
        System.out.println("Select the id you want to remove:");
        String idS = reader.readLine();
        int id = Integer.parseInt(idS);
        bookImp.delete(id);
    }

    private void readId(BufferedReader reader) throws IOException {
        System.out.println("Select the id you want to find:");
        String idS = reader.readLine();
        int id = Integer.parseInt(idS);
        System.out.println("Books =" + bookImp.readId(id));
    }

    private void read() {
        System.out.println("Books = " + bookImp.read());
        System.out.println();
    }
}
