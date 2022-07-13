/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Book_manage;

import DTO.Author;
import file.FileManage;

/**
 *
 * @author ADMIN
 */
public class ManagerAuthor {

    private Author[] list;
    private int quantity;

    public ManagerAuthor() {
        list = new Author[100];
        quantity = 0;
    }

    public ManagerAuthor(Author[] list, int quantity) {
        this.list = list;
        this.quantity = quantity;
    }

    public Author[] getList() {
        return list;
    }

    public int getQuantity() {
        return quantity;
    }

     public boolean checkAuthorID(String authorId) {
        for (int i = 0; i < quantity; i++) {
            if (list[i].getAuthorID().equals(authorId)) {
                return true;
            }
        }
        return false;
    }
    
    public void readAuthorList(String fileName) { //doc file
        int i;
        Author[] result2 = FileManage.loadAuthor(fileName);
        for (i = 0; i < result2.length; i++) {
            list[i] = result2[i];
        }
        quantity = i;
    }
    
    //authorId to ADD TO BOOK
    public String outputAuthor(int order)
    {
        return list[order].getAuthorID() ;  
        
    }
    
    public String outputAuthorName(int x)
    {
        return list[x].getAuthor_name();
    }
    //xuat thong tin author(name, id)
    public void outputAuthorFile()
    {
        for(int i = 0;i < quantity; i++)
        {
            list[i].outputAuthorFile(i);
        }
    }
    //tra ve so phan tu co trong authorList
    public int authorSize()
    {
        return quantity;
    }
    
//    public boolean removeAuthor(String AuthorName) {
//        {
//            for (int i = 0; i < quantity; i++) {
//                if (list[i].getAuthor_name().equals(AuthorName)) {
//                    list[i] = list[i + 1];
//                    quantity--;
//                    return true;
//                }
//            }
//            return false;
//        }
//    }
    
}
