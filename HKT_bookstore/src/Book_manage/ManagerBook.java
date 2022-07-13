/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Book_manage;

import DTO.Author;
import DTO.Book;
import Valid.CheckValid;
import file.FileManage;
import java.util.Scanner;

/**
 *
 * @author ADMIN
 */
public class ManagerBook {

    private Book[] list;
    private int quantity;

    public ManagerBook() {
        list = new Book[100];
        quantity = 0;
    }

    public ManagerBook(Book[] list, int n) {
        this.list = list;
        this.quantity = quantity;
    }

    public Book[] getList() {
        return list;
    }

    public void setList(Book[] list) {
        this.list = list;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int findbyIsbn(String Isbn) {
        for (int i = 0; i < quantity; i++) {
            if (list[i].getIsbn().equals(Isbn)) {
                return i;
            }
        }
        return -1;
    }
//-------------------METHOD(ADD,DELETE,...)--------------------

    public boolean addBook(Book book) {
        {
            if (book == null || quantity > 100) {
                System.out.println("error");
                return false;
            }
            list[quantity] = book;
            quantity++;
            return true;
        }
    }

//tim isbn trung

    public boolean searchTitle(String isbn) {
        int check = 0;
        if (isbn.isEmpty()) {
            return false;
        }
        System.out.printf("%-15s %-36s %-12s %-12s\n",
                " BookIsbn", " title", "price", " AuthorID");
        for (int i = 0; i < quantity; i++) {
            if (list[i].getTitle().contains(isbn)) {
                list[i].outputBook();
                check++;
            }
        }
        if (check == 0) {
            return false;
        }
        return true;
    }
    
    public boolean searchAuthor_name(String isbn) {
        int check = 0;
        if (isbn.isEmpty()) {
            return false;
        }
        System.out.printf("%-15s %-36s %-12s %-12s\n",
                " BookIsbn", " title", "price", " AuthorID");
        for (int i = 0; i < quantity; i++) {
            if (list[i].getAuthorName().contains(isbn) ) {
                list[i].outputBook();
                check++;
            }
        }
        if (check == 0) {
            return false;
        }
        return true;
    }

    public boolean removeBook(String Isbn) {
        
            for (int i = 0; i < quantity; i++) {
                if (list[i].getIsbn().equals(Isbn)) {
                    list[i] = list[i + 1];
                    quantity--;
                    return true;
                }
            }
            return false;      
    }
//---------------------CHECK METHOD-------------------

    public boolean checkDup(String Isbn) {
        for (int i = 0; i < quantity; i++) {
            if (list[i].getIsbn().equals(Isbn)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean checkEmpty() {
        if (quantity == 0) {
            return true;
        }
        return false;
    }
       
//---------------------FILE--------------------------

    public void writeBook(String fileName) {  //viet file vo txt       
        FileManage.writeFile(fileName, list, quantity);
    }

    public void readBookList(String fileName) { //doc file
        int i;
        Book[] result = FileManage.loadBook(fileName);
        for (i = 0; i < result.length; i++) {
            list[i] = result[i];
        }
        quantity = i;
    }

//---------------------UPDATE------------------------  
    public boolean updateTitle(String isbn) {
        Scanner sc = new Scanner(System.in);
        boolean tieptuc = false;
        String newTitle;
        //update cpu  
        if (findbyIsbn(isbn) == -1) {
            return false;
        } else {
            do {
                try {
                    do {
                        sc = new Scanner(System.in);
                        System.out.println("input new Title(no special letter, no exceed 30 letter) : ");
                        newTitle = sc.nextLine();
                        CheckValid.checkString(newTitle, "^[a-zA-Z]+(?!\\s).+");
                        list[findbyIsbn(isbn)].setTitle(newTitle);
                        if (newTitle.length() > 30) {
                            System.out.println("");
                        }
                    } while (newTitle.length() > 30);
                    tieptuc = false;
                } catch (Exception event) {
                    if (event.getMessage() != null) {
                        System.out.println(event.getMessage());
                    }
                    tieptuc = true;
                }
            } while (tieptuc);
        }
        return true;
    }
    //update price

    public boolean updatePrice(String isbn) {
        Scanner sc = new Scanner(System.in);
        boolean tieptuc = false;
        int newPrice;
        if (findbyIsbn(isbn) == -1) {
            return false;
        } else {
            do {
                try {
                    do {
                        sc = new Scanner(System.in);
                        System.out.println("input new price (price must be integer,>0): ");
                        newPrice = sc.nextInt();
                        if (newPrice < 1 || newPrice > 10000) {
                            System.out.println("price must > 0 and <10000, please input again");
                        } else {
                            list[findbyIsbn(isbn)].setPrice(newPrice);
                        }
                        tieptuc = false;
                    } while (newPrice < 1 || newPrice > 10000);
                } catch (Exception event) {
                    System.out.println("error, please input again(only number elements)");
                    tieptuc = true;
                }
            } while (tieptuc);
        }
        return true;
    }

    public boolean updateAll(String isbn) {
        Scanner sc = new Scanner(System.in);
        boolean tieptuc = false;
        String newTitle;
        int newPrice;
        if (findbyIsbn(isbn) == -1) {
            return false;
        } else {
            //update title
            do {
                try {
                    do {
                        sc = new Scanner(System.in);
                        System.out.println("input new Title(no special letter, no exceed 30 letter) : ");
                        newTitle = sc.nextLine();
                        CheckValid.checkString(newTitle, "^[a-zA-Z]+(?!\\s).+");
                        list[findbyIsbn(isbn)].setTitle(newTitle);
                        if (newTitle.length() > 30) {
                            System.out.println("");
                        }
                    } while (newTitle.length() > 30);
                    tieptuc = false;
                } catch (Exception event) {
                    if (event.getMessage() != null) {
                        System.out.println(event.getMessage());
                    }
                    tieptuc = true;
                }
            } while (tieptuc);
            //update price
            do {
                try {
                    do {
                        sc = new Scanner(System.in);
                        System.out.println("input new price (price must be integer,>0): ");
                        newPrice = sc.nextInt();
                        if (newPrice < 1 || newPrice > 10000) {
                            System.out.println("price must > 0 and <10000, please input again");
                        } else {
                            list[findbyIsbn(isbn)].setPrice(newPrice);
                        }
                        tieptuc = false;
                    } while (newPrice < 1 || newPrice > 10000);
                } catch (Exception event) {
                    System.out.println("error, please input again(only number elements)");
                    tieptuc = true;
                }
            } while (tieptuc);
        }
        return true;
    }

//-----------------------DISPLAY--------------------------
    public void displayAll() {
        for (int i = 0; i < quantity; i++) {
            list[i].outputBook();
        }
    }

    public void displayIsbn() {
        System.out.print("ISBN: ");
        for (int i = 0; i < quantity; i++) {
            System.out.print(list[i].getIsbn() + " | ");
        }
    }

    public void displayTitle() {
        System.out.print("\nTitle: ");
        for (int i = 0; i < quantity; i++) {
            System.out.print(list[i].getTitle() + " | ");
        }
    }
    
    public void displayAuthor_name()
    {
        System.out.println("\nAuthor Name : ");
        for(int i = 0; i<quantity; i++)
        {
            System.out.println(list[i].getAuthorName());
        }
    }

//Xuat ra thong tin sach
    public void outputInfo(String isbn) {
        for (int i = 0; i < quantity; i++) {
            if (isbn.equals(list[i].getIsbn())) {
                list[i].outputBook2();
            }
        }
    }

}
