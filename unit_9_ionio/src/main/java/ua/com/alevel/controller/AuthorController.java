package ua.com.alevel.controller;

import ua.com.alevel.dao.imp.AuthorImp;
import ua.com.alevel.entity.Author;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;



public class AuthorController {
    AuthorImp authorImp = new AuthorImp();

    public void run() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Hello!");
        System.out.println("I help you create Authors! Choose act:\n1) create\n2) update\n" +
                "3) delete \n4) read all \n5) read by id\n0) to exit");
        String positionAuthor;
        while ((positionAuthor = reader.readLine()) != null) {
            switch (positionAuthor) {
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
        Author author = new Author();
        System.out.println("Please enter name author...");
        String name = reader.readLine();
        System.out.println("Please enter lastname author...");
        String lastName = reader.readLine();
        System.out.println("Please enter books...\n(Example: The Hobbit, Animal Farm, etc.)");

        String []names = reader.readLine().split(",");
        Set<String> books = new HashSet<>(Arrays.asList(names));
        author.setListBooks(books);
        author.setNameAuthor(name);
        author.setLastNameAuthor(lastName);
        authorImp.create(author);
    }

    private void update(BufferedReader reader) throws IOException {
        System.out.println("Look for author by id:");
        String idS = reader.readLine();
        int id = Integer.parseInt(idS);
        Author author = authorImp.readId(id);
        System.out.println("Please enter name author...");
        String name = reader.readLine();
        System.out.println("Please enter your lastname...");
        String lastName = reader.readLine();
        System.out.println("Please enter books...");

        String []names = reader.readLine().split(",");
        Set<String> books = new HashSet<>(Arrays.asList(names));
        author.setListBooks(books);
        author.setNameAuthor(name);
        author.setLastNameAuthor(lastName);
        authorImp.update(author);

    }


    private void delete(BufferedReader reader) throws IOException {
        System.out.println("Select the id you want to remove:");
        String idS = reader.readLine();
        int id = Integer.parseInt(idS);
        authorImp.delete(id);
    }

    private void readId(BufferedReader reader) throws IOException {
        System.out.println("Select the id you want to find:");
        String idS = reader.readLine();
        int id = Integer.parseInt(idS);
        System.out.println("author =" + authorImp.readId(id));
    }

    private void read() {
        System.out.println("Authors = " + authorImp.read());
        System.out.println();
    }
}






