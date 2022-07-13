/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Book_manage.ManagerBook;
import Book_manage.ManagerAuthor;
import DTO.Book;
import Valid.CheckValid;
import java.util.Scanner;

/**
 *
 * @author ADMIN
 */
public class main {

    public static void main(String[] args) {
        Book book = null;
        String confirm;
        ManagerBook manage = new ManagerBook();
        ManagerAuthor manageAuthor = new ManagerAuthor();
        int choice = 0;
        Scanner sc = new Scanner(System.in);
        boolean tieptuc = false;
        manage.readBookList("book2.dat");
        manageAuthor.readAuthorList("author.dat");
        do {
            System.out.println("Welcome to HKT store-@2021 by <SE150882-Truong Hoang Long>");
            System.out.println("[1] Add new book");
            System.out.println("[2] Search book");
            System.out.println("[3] Remove book");
            System.out.println("[4] Show book");
            System.out.println("[5] Update Book");
            System.out.println("[6] Store data to file");
            System.out.println("[7] Empty");
            System.out.println("[8] Exit");
            boolean tieptuc2 = true;
            do {
                try {
                    sc = new Scanner(System.in);
                    System.out.print(" Input Options: ");
                    choice = sc.nextInt();
                    if (choice < 1 || choice > 8) {
                        System.out.println("\tPlease Input from 1-8!\n");
                    }
                    tieptuc2 = false;
                } catch (Exception e) {
                    System.out.println("\tInvalid choice, please input again(Number only)\n");
                    tieptuc2 = true;
                }
            } while (tieptuc2);
            System.out.print("\n");
            switch (choice) {
                case 1:
                    do {
                        sc = new Scanner(System.in);
                        book = new Book();
                        book.input();
                        if (manage.checkDup(book.getIsbn()) == true) {
                            System.out.println("book is already exit");
                        } else {
                            System.out.printf("%-12s %-36s \n",
                                    "Author ID    ||", "      Author Name    ||");
                            System.out.println("-------------||----------------------||");
                            manageAuthor.outputAuthorFile();
                            boolean flag = true;
                            int subchoice = 0;
                            do {
                                try {
                                    do {
                                        sc = new Scanner(System.in);
                                        System.out.print("Your Author choice is: ");
                                        subchoice = sc.nextInt();
                                        if (subchoice < 1 || subchoice > manageAuthor.authorSize()) {
                                            System.out.println("\tMust be from 1-" + manageAuthor.authorSize() + "!\n");
                                        }
                                        flag = false;
                                    } while (subchoice < 1 || subchoice > manageAuthor.authorSize());
                                    book.setAuthorID(manageAuthor.outputAuthor(subchoice - 1));
                                    book.setAuthorName(manageAuthor.outputAuthorName(choice));
                                } catch (Exception e) {
                                    System.out.println("Invalid, number only!");
                                    flag = true;
                                }
                            } while (flag);
                            System.out.println("Book successfully added");
                            manage.addBook(book);
                            manage.writeBook("book2.dat");
                        }
                        sc = new Scanner(System.in);
                        do {
                            System.out.println("do you want to continue? (yes/no)");
                            confirm = sc.nextLine();
                            if (!confirm.equals("yes") && !confirm.equals("no")) {
                                System.out.println("you must choose yes or no to continue");
                            }
                        } while (!confirm.equals("yes") && !confirm.equals("no"));
                    } while (!confirm.equals("no"));

                    tieptuc = true;
                    break;
                case 2:
                    sc = new Scanner(System.in);
                    int choice3 = 0;
                    boolean tieptuc5 = true;
                    do {
                        System.out.println("1. Search using book Title  ");
                        System.out.println("2. Search using Author Name ");
                        System.out.println("3. Exit to menu");
                        boolean tieptuc6 = false;
                        do {
                            try {
                                sc = new Scanner(System.in);
                                System.out.print("input options : ");
                                choice3 = sc.nextInt();
                                CheckValid.checkSubOption(choice3);
                                tieptuc6 = false;
                            } catch (Exception e) {
                                if (e.getMessage() != null) {
                                    System.out.println(e.getMessage());
                                } else {
                                    System.out.println("options doesn't exist");
                                }
                                tieptuc6 = true;
                            }
                        } while (tieptuc6);
                        switch (choice3) {
                            case 1:
                                do {
                                    sc = new Scanner(System.in);
                                    System.out.println("Search using these item");
                                    manage.displayTitle();
                                    System.out.println("\nInput the Title you want to search : ");
                                    String Isbn = sc.nextLine();
                                    if (manage.searchTitle(Isbn) == false) {
                                        System.out.println("No book were found contain those letter");
                                    }
                                    do {
                                        System.out.println("do you want to continue search? pick(yes/no)");
                                        confirm = sc.nextLine();
                                        if (!confirm.equals("yes") && !confirm.equals("no")) {
                                            System.out.println("you must choose yes or no to continue");
                                        }
                                    } while (!confirm.equals("yes") && !confirm.equals("no"));
                                } while (!confirm.equals("no"));
                                tieptuc5 = true;
                                break;
                            case 2:
                                do {
                                    sc = new Scanner(System.in);
                                    System.out.println("Search using these item");
                                    manage.displayAuthor_name();
                                    System.out.println("\nInput AuthorName you want to search : ");
                                    String Isbn = sc.nextLine();
                                    if (manage.searchAuthor_name(Isbn) == false) {
                                        System.out.println("No book were found contain those letter");
                                    }
                                    do {
                                        System.out.println("do you want to continue search? pick(yes/no)");
                                        confirm = sc.nextLine();
                                        if (!confirm.equals("yes") && !confirm.equals("no")) {
                                            System.out.println("you must choose yes or no to continue");
                                        }
                                    } while (!confirm.equals("yes") && !confirm.equals("no"));
                                } while (!confirm.equals("no"));
                                tieptuc5 = true;
                                break;
                            case 3:
                                tieptuc5 = false;
                                break;
                        }
                    } while (tieptuc5);
                    tieptuc = true;
                    break;
                case 3:
                    do {
                        sc = new Scanner(System.in);
                        System.out.println("Remove using these item");
                        manage.displayIsbn();
                        System.out.println("\nInput Book(ID) you want to delete :");
                        String removedIsbn = sc.nextLine();
                        if (manage.removeBook(removedIsbn)) {
                            System.out.println("remove succesful");
                        } else {
                            System.out.println("book doesn't exist");
                        }
                        manage.writeBook("book2.dat");
                        do {
                            System.out.println("do you want to continue delete? (yes/no)");
                            confirm = sc.nextLine();
                            if (!confirm.equals("yes") && !confirm.equals("no")) {
                                System.out.println("you must choose yes or no to continue");
                            }
                        } while (!confirm.equals("yes") && !confirm.equals("no"));
                    } while (!confirm.equals("no"));
                    tieptuc = true;
                    break;
                case 4:
                    if (manage.checkEmpty() == true) {
                        System.out.println("the list is empty");
                        tieptuc = true;
                        break;
                    } else {
                        System.out.printf("%-15s %-36s %-12s %-12s %-12s\n",
                                " BookIsbn", " title", "price", " AuthorID", "AuthorName");
                        manage.displayAll();
                        tieptuc = true;
                    }
                    break;
                case 5: //update
                    sc = new Scanner(System.in);
                    int choice2 = 0;
                    boolean tieptuc3 = true;
                    do {
                        System.out.println("Choose Update : ");
                        System.out.println("1. Title");
                        System.out.println("2. Price");
                        System.out.println("3. Update Title & Price");
                        System.out.println("4. Back to Main Menu");
                        boolean tieptuc4 = false;
                        do {
                            try {
                                sc = new Scanner(System.in);
                                System.out.print("input options : ");
                                choice2 = sc.nextInt();
                                CheckValid.checkSubOption(choice2);
                                tieptuc4 = false;
                            } catch (Exception e) {
                                if (e.getMessage() != null) {
                                    System.out.println(e.getMessage());
                                } else {
                                    System.out.println("options doesn't exist");
                                }
                                tieptuc4 = true;
                            }
                        } while (tieptuc4);
                        switch (choice2) {
                            case 1:
                                do {
                                    sc = new Scanner(System.in);
                                    manage.displayIsbn();
                                    System.out.println("\ninput BookId to update : ");
                                    String updateTitle = sc.nextLine();
                                    if (manage.updateTitle(updateTitle) == true) {
                                        System.out.println("update successful");
                                    } else {
                                        System.out.println("Book doesn't exist");
                                    }
                                    manage.writeBook("book2.dat");
                                    do {
                                        System.out.println("do you want to continue update? (yes/no)");
                                        confirm = sc.nextLine();
                                        if (!confirm.equals("yes") && !confirm.equals("no")) {
                                            System.out.println("you must choose yes or no to continue");
                                        }
                                    } while (!confirm.equals("yes") && !confirm.equals("no"));
                                } while (!confirm.equals("no"));
                                tieptuc3 = true;
                                break;
                            case 2:
                                do {
                                    sc = new Scanner(System.in);
                                    manage.displayIsbn();
                                    System.out.println("\ninput BookId to update : ");
                                    String updateprice = sc.nextLine();
                                    if (manage.updatePrice(updateprice) == true) {
                                        System.out.println("update successful");
                                    } else {
                                        System.out.println("Book doesn't exist");
                                    }
                                    manage.writeBook("book2.dat");
                                    do {
                                        System.out.println("do you want to continue update? (yes/no)");
                                        confirm = sc.nextLine();
                                        if (!confirm.equals("yes") && !confirm.equals("no")) {
                                            System.out.println("you must choose yes or no to continue");
                                        }
                                    } while (!confirm.equals("yes") && !confirm.equals("no"));
                                } while (!confirm.equals("no"));
                                tieptuc3 = true;
                                break;
                            case 3:
                                do {
                                    sc = new Scanner(System.in);
                                    manage.displayIsbn();
                                    System.out.println("\ninput BookId to update : ");
                                    String updateAll = sc.nextLine();
                                    if (manage.updateAll(updateAll) == true) {
                                        System.out.println("update successful");
                                    } else {
                                        System.out.println("Book doesn't exist");
                                    }
                                    manage.writeBook("book2.dat");
                                    do {
                                        System.out.println("do you want to continue update? (yes/no)");
                                        confirm = sc.nextLine();
                                        if (!confirm.equals("yes") && !confirm.equals("no")) {
                                            System.out.println("you must choose yes or no to continue");
                                        }
                                    } while (!confirm.equals("yes") && !confirm.equals("no"));
                                } while (!confirm.equals("no"));
                                tieptuc3 = true;
                                break;
                            case 4:
                                tieptuc3 = false;
                                break;
                        }
                    } while (tieptuc3);
                    tieptuc = true;
                    break;
                case 6:
                    manage.writeBook("book2.dat");
                    System.out.println("print sucessful");
                    tieptuc = true;
                    break;
                case 7:

                case 8:
                    tieptuc = false;
                    break;
            }
        } while (tieptuc);
    }
}
