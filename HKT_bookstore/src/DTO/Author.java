/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author ADMIN
 */
public class Author {
    private String AuthorID;
    private String Author_name;

    public Author(String AuthorID, String Author_name) {
        this.AuthorID = AuthorID;
        this.Author_name = Author_name;
    }

    public String getAuthorID() {
        return AuthorID;
    }

    public String getAuthor_name() {
        return Author_name;
    }

    @Override
    public String toString() {
        return  AuthorID + "," + Author_name;
    }
    
     public void outputAuthorFile(int i) {
        System.out.printf("%d. %-10s|| %-20s || \n", i + 1, AuthorID, Author_name);
    }
}
