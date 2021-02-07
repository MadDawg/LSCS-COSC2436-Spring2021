// Written by Cornell Washington

import java.util.*;
import java.lang.*;
import java.io.*;

class Week3{
    /**
    * The isPalindrome method determines whether a string
    * is a palindrome.
    * @param str The string to test.
    * @return This method returns true if str is a
    * palindrome. It returns false otherwise.
    */
    public static boolean isPalendrome(String str){
        if (str.length() <= 1){ return true; }
        if (str.charAt(0) != str.charAt(str.length()-1)){ return false; }
        return isPalendrome(str.substring(1,str.length()-1));
    }

    public static void main (String[] args) throws java.lang.Exception{
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter palindomes (press CTRL+Z followed by the Enter key to finish): ");
        do{
            try{
                String str = sc.nextLine();
                if (isPalendrome(str)){
                    System.out.printf("\"%s\" is a palendrome.\n", str);
                }
                else{
                    System.out.printf("\"%s\" is not a palendrome.\n", str);
                }
            }
            // user inputs ^Z or ^D without inputting a string
            catch(java.util.NoSuchElementException e){
                break;
            }
        }
        while(sc.hasNextLine());
        sc.close();

        System.out.println("Done.");
    }
}
