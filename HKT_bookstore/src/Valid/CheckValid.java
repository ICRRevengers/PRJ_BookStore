/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Valid;

/**
 *
 * @author ADMIN
 */
public class CheckValid {
     public static boolean checkString(String input, String pattern) throws Exception {
        if (input == null) {
            throw new Exception("input is empty");
        }
        if (pattern == null) {
            throw new Exception("pattern is null");
        }
        if (!input.matches(pattern)) {
            throw new Exception("data not correct, please try again(no special letter Ex:!@#,...)");
        }
        return true;
    }
     
    public static boolean checkSubOption(int option) throws Exception {
        if (option < 1 || option > 4) {
            throw new Exception("options doesn't exist");
        }
        return true;
    }
    
    public static boolean checkSubOption2(int option) throws Exception {
        if (option < 1 || option > 2) {
            throw new Exception("options doesn't exist");
        }
        return true;
    }
}
