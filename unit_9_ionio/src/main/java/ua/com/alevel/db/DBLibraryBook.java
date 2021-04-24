package ua.com.alevel.db;


import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import ua.com.alevel.entity.Book;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DBLibraryBook {
    private static final DBLibraryBook dbLibrary = new DBLibraryBook();

    private DBLibraryBook() {
    }

    public static DBLibraryBook getInstance() {
        return dbLibrary;
    }

    final String nameFile = "book.csv";


    String[] bookFirstLine = {"id", "nameBook", "nameAuthors"};

    public void creatBook(Book book) throws IOException, CsvException {
        int id = getUniqueId();

        book.setId(id);

        String[] asArray = book.toArray();

        try (CSVWriter writer = new CSVWriter(new FileWriter(nameFile, true))) {
            writer.writeNext(asArray);
        }
    }

    private int getUniqueId() throws IOException {
        createAndInitFileIfNotExist();

        List<Book> allBook = findAllBook();
        if (!allBook.isEmpty()) {
            Book book = allBook.get(allBook.size() - 1);
            return book.getId() + 1;
        }
        return 1;
    }

    private void createAndInitFileIfNotExist() throws IOException {
        Path path = Paths.get(nameFile);

        File file = new File(nameFile);
        if (!Files.exists(path)) {
            boolean created = file.createNewFile();

            if (created) {
                System.out.println("New file created: " + nameFile);

                initFile();
            } else {
                System.out.println("Failed to create file: " + nameFile);
            }

        }

    }

    private void initFile() throws IOException {
        try (CSVWriter writer = new CSVWriter(new FileWriter(nameFile))) {
            writer.writeAll(Collections.singleton(bookFirstLine));
        }
    }

    public List<Book> findAllBook() {
        List<Book> books = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(nameFile))) {
            if (reader.readNext() != null) {
                List<String[]> listBook = reader.readAll();
                for (String[] strings : listBook) {
                    Book book = new Book();
                    book.createBook(strings);
                    books.add(book);
                }
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        return books;
    }

    public Book findBook(int id) {
        return findAllBook().stream().filter(b -> b.getId() == id).findFirst().get();
    }

    public void deleteBook(int id) {
        List<Book> books = findAllBook();
        books.removeIf(book -> book.getId() == id);
        flushAndSaveAll(books);
    }

    private void flushAndSaveAll(List<Book> books) {
        List<String[]> strings = new ArrayList<>();
        strings.add(bookFirstLine);
        for (Book book : books) {
            strings.add(book.toArray());
        }

        try (CSVWriter writer = new CSVWriter(new FileWriter(nameFile))) {
            writer.writeAll(strings);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateBook(Book book) {
        int id = book.getId();
        List<Book> books = findAllBook();
        Book current =  books.stream().filter(b -> b.getId() == id).findFirst().get();
        current.setNameBook(book.getNameBook());
        current.setListAuthors(book.getListAuthors());
        flushAndSaveAll(books);
    }

}

