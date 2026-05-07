package app;

import data.DataManager;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Scanner;

public class LoginTools {
    public static String hash(String input) {
        byte[] hashBytes = {};
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            hashBytes = md.digest(input.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            System.out.println(e.getMessage());
        }
        return  Base64.getEncoder().encodeToString(hashBytes);
    }

    public static boolean checkUserName(String input, DataManager data) {
        if(data.getUserNameToID().containsKey(input)) {
            System.out.println();
            System.out.println("This username was taken before.");
            return false;
        }
        if(input.length() < 3) {
            System.out.println();
            System.out.println("The username must contain at least 3 digits");
            return false;
        }
        for(int i=0; i<input.length(); i++) {
            char check = input.charAt(i);
            if((check >= 'a' && check <= 'z') || (check >= '0' && check <= '9')) {
                continue;
            }
            else {
                System.out.println();
                System.out.println("The username must contain only -small letters- and -numbers-");
                return false;
            }
        }
        return true;
    }
    public static boolean checkPassword(Scanner sc, String input) {
        if(input.length() < 6) {
            System.out.println();
            System.out.println("The password must contain at least 6 digits");
            return false;
        }
        for(int i=0; i<input.length(); i++) {
            if(input.charAt(i) < 33) {
                System.out.println();
                System.out.println("Unacceptable Password");
                return false;
            }
        }
        System.out.println("Re-Enter your password");
        System.out.print(">> ");

        if(!(sc.nextLine().equals(input))) {
            System.out.println();
            System.out.println("Not matched with the entered password");
            return false;
        }
        return true;
    }
}
