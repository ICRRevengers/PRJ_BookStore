/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import Book_manage.ManagerAuthor;
import Book_manage.ManagerBook;
import Valid.CheckValid;
import java.util.Scanner;
import sun.security.util.Length;

/**
 *
 * @author ADMIN
 */
public class Book {

    private String Isbn, Title, AuthorID, AuthorName;
    private int price;

    public Book(String Isbn, String Title, int price, String AuthorID ,String AuthorName) {
        this.Isbn = Isbn;
        this.Title = Title;
        this.price = price;
        this.AuthorID = AuthorID;
        this.AuthorName = AuthorName;
    }

    public Book() {
        Isbn = "";
        Title = "";
        price = 0;
        AuthorID = "";
        AuthorName = "";
    }

    public String getIsbn() {
        return Isbn;
    }

    public void setIsbn(String Isbn) {
        this.Isbn = Isbn;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getAuthorID() {
        return AuthorID;
    }

    public void setAuthorID(String AuthorID) {
        this.AuthorID = AuthorID;
    }

    public String getAuthorName() {
        return AuthorName;
    }

    public void setAuthorName(String AuthorName) {
        this.AuthorName = AuthorName;
    }

    public void input() {
        Scanner sc = new Scanner(System.in);
        Boolean tieptuc = false;
        ManagerBook manage = new ManagerBook();
        //INPUT BookISBN
        do {
            try {
                do {
                    System.out.println("input new BookIsbn(No spoecial letter, must be between 4 and 7 letter) : ");
                    Isbn = sc.nextLine();
                    CheckValid.checkString(Isbn, "^[a-zA-Z]+(?!\\s).+");
                    if (manage.checkDup(Isbn) == true) {
                        System.out.println("BookId is duplicated");
                    }
                    if (Isbn.length() > 7 || Isbn.length() < 4) {
                        System.out.println("out of range, please input again(must be between 4 and 7 letter)");
                    }
                } while (Isbn.length() > 7 || Isbn.length() < 4);
                tieptuc = false;
            } catch (Exception e) {
                if (e.getMessage() != null) {
                    System.out.println(e.getMessage());
                }
                tieptuc = true;
            }
        } while (tieptuc);
        //INPUT TITLE
        do {
            try {
                do {
                    System.out.println("input new Book title(do not exceed 30 letter) : ");
                    Title = sc.nextLine();
                    CheckValid.checkString(Title, "^[a-zA-Z]+(?!\\s).+");
                    tieptuc = false;
                    if (Title.length() > 30) {
                        System.out.println("the length of the book is too long, please input again(<30 letter)");
                    }
                } while (Title.length() > 30);
            } catch (Exception e) {
                if (e.getMessage() != null) {
                    System.out.println(e.getMessage());
                }
                tieptuc = true;
            }
        } while (tieptuc);
        //INPUT PRICE
        do {
            try {
                do {
                    sc = new Scanner(System.in);
                    System.out.print("input price[$](number only and > 0): ");
                    price = sc.nextInt();
                    if (price < 1) {
                        System.out.println("price must > 0, please input again");
                    }
                    if (price > 10000) {
                        System.out.println("price must be <10000, please input again");
                    }
                    tieptuc = false;
                } while (price < 1 || price > 10000);
            } catch (Exception event) {
                System.out.println("error, please input again(only number elements)");
                tieptuc = true;
            }
        } while (tieptuc);
    }

    public void outputBook() {
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.printf("%-15s %-35s %-13s %-12s %-12s \n",
                "║ " + Isbn, Title, price + "$", AuthorID, AuthorName);
    }

    public void outputBook2() {
        System.out.printf("%-15s %-36s %-12s %-12s\n",
                " BookIsbn", " title", "price", " AuthorID");
        System.out.println("---------------------------------------------------------------------------||");
        System.out.printf("%-15s %-35s %-13s %-12s \n",
                "║ " + Isbn, Title, price + "$", AuthorID);
    }
    
    @Override
    public String toString() {
        return Isbn + "," + Title + "," + price + "," + AuthorID + "," + AuthorName;
    }
}
