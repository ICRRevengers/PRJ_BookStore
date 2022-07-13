/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package file;

import DTO.Book;
import DTO.Author;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
//import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class FileManage {
     public static void writeFile(String fileName, Book[] list, int quantity) {
        PrintWriter write = null;
        try {
            write = new PrintWriter(fileName);
            for (int i = 0; i < quantity; i++) {
                write.println(list[i]);
            }
        } catch (Exception event) {
            event.printStackTrace();
        } finally {
            try {
                if (write != null) {
                    write.close();
                }
            } catch (Exception event) {
                event.printStackTrace();
            }
        }
    }
   
    public static Book[] loadBook(String filename) {
        Book[] book = null;
        FileReader f = null;
        BufferedReader r = null;
        try {
            ArrayList<Book> result = new ArrayList<>();
            f = new FileReader(filename);
            r = new BufferedReader(f);
            while (r.ready()) {
                String tempoary = r.readLine();
                if (tempoary != null && !tempoary.isEmpty()) {
                    String[] array = tempoary.split(",");
                    if (array.length == 5) {
                        Book x = new Book(array[0], array[1], Integer.parseInt(array[2]), array[3], array[4]);
                        result.add(x);
                    }
                }
            }
            book = new Book[result.size()];
            book = result.toArray(book);
        } catch (Exception event) {
            event.printStackTrace();
        } finally {
            try {
                if (f != null) {
                    f.close();
                }
                if (r != null) {
                    r.close();
                }
            } catch (Exception event) {
                event.printStackTrace();
            }
        }
        return book;
    }
    
     public static Author[] loadAuthor(String filename2) {
        Author[] author = null;
        FileReader f = null;
        BufferedReader r = null;
        try {
            ArrayList<Author> result = new ArrayList<>();
            f = new FileReader(filename2);
            r = new BufferedReader(f);
            while (r.ready()) {
                String tempoary = r.readLine();
                if (tempoary != null && !tempoary.isEmpty()) {
                    String[] array = tempoary.split(",");
                    if (array.length == 2) {
                        Author x = new Author(array[0], array[1]);
                        result.add(x);
                    }
                }
            }
            author = new Author[result.size()];
            author = result.toArray(author);
        } catch (Exception event) {
            event.printStackTrace();
        } finally {
            try {
                if (f != null) {
                    f.close();
                }
                if (r != null) {
                    r.close();
                }
            } catch (Exception event) {
                event.printStackTrace();
            }
        }
        return author;
    }
    
}
