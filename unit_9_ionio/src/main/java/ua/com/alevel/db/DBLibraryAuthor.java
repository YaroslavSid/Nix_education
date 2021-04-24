package ua.com.alevel.db;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import ua.com.alevel.entity.Author;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DBLibraryAuthor {
    private static final DBLibraryAuthor dbAuthor = new DBLibraryAuthor();
    private DBLibraryAuthor(){

    }
    public static DBLibraryAuthor getInstance(){
        return dbAuthor;
    }

    final String nameFile = "author.csv";

    String [] authorFirstLine = {"id", "nameAuthor", "lastNameAuthor","listBooks"};



    public void creatAuthor(Author author) throws IOException {
        int id = getUniqueId();
        author.setId(id);
        String [] asArray = author.toArray();
        try (CSVWriter writer = new CSVWriter(new FileWriter(nameFile, true))) {
            writer.writeNext(asArray);
        }
    }



    private int getUniqueId() throws IOException {
        createAndInitFileIfNotExist();

        List<Author> allAuthors = findAllAuthors();
        if (!allAuthors.isEmpty()) {
            Author author = allAuthors.get(allAuthors.size() - 1);
            return author.getId() + 1;
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
            writer.writeAll(Collections.singleton(authorFirstLine));
        }
    }


    public List<Author> findAllAuthors() {
        List<Author> authors = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(nameFile))) {
            if (reader.readNext() != null) {
                List<String[]> listAuthors = reader.readAll();
                for (String[] strings : listAuthors) {
                    Author author = new Author();
                    author.createAuthor(strings);
                    authors.add(author);
                }
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        return authors;
    }

    public Author findAuthor(int id) {
        return findAllAuthors().stream().filter(a -> a.getId() == id).findFirst().get();
    }

    public void deleteAuthor(int id) {
        List<Author> authors = findAllAuthors();
        authors.removeIf(author -> author.getId() == id);
        flushAndSaveAll(authors);

    }
    private void flushAndSaveAll(List<Author> authors) {
        List<String[]> strings = new ArrayList<>();
        strings.add(authorFirstLine);
        for (Author author : authors) {
            strings.add(author.toArray());
        }

        try (CSVWriter writer = new CSVWriter(new FileWriter(nameFile))) {
            writer.writeAll(strings);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateAuthor(Author author) {
        int id = author.getId();
        List<Author> authors = findAllAuthors();
        Author current = authors.stream().filter(a -> a.getId() == id).findFirst().get();
        current.setNameAuthor(author.getNameAuthor());
        current.setLastNameAuthor(author.getLastNameAuthor());
        current.setListBooks(author.getListBooks());
        flushAndSaveAll(authors);
    }
}
