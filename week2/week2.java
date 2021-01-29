// Written by Cornell Washington

import java.util.*;
import java.lang.*;
import java.io.*;

import payroll.*;

class Week2
{
    public static void printPayroll(Payroll payroll){
        System.out.printf("\nName: %s\nID: %s\nHours Worked: %d\nPayrate: %.2f\n",
            payroll.getName(), payroll.getID(), payroll.getHours(), payroll.getRate());
    }

    public static void main (String[] args) throws java.lang.Exception
	{
        // as far as I know (which is very little), there is no tuple-like class included Java
        // and I don't want to use a library if I don't have to
        String[] names = {"John Cena", "Mark Rober", "Bjarne Stroustrup", "", "Cornell Washington"};
        String[] ids = {"JC1977", "MRNASA", "CC1985", "AA0000", "CW1991"};
        int[] hours = {-5, 80, 40, 0, 10};
        double[] payrates = {0.0, 25.0, 25.0, 0.0, 111.0};

        // test constructors
        for (int i = 0; i < 5; i++){
            try{
                Payroll payroll = new Payroll(names[i], ids[i], hours[i], payrates[i]);
                System.out.printf("Iteration %d: Object created succussfully.\n", i+1);
            }
            catch(RuntimeException e){
                System.out.printf("Iteration %d: %s\n", i+1, e);
            }
        }

        // test setters
        Payroll payroll = new Payroll();
        System.out.println();

        // test getters
        printPayroll(payroll);
        System.out.println();

        try{
            payroll.setName("");
        }
        catch(RuntimeException e){
            System.out.println(e);
            payroll.setName("Clement Mihailescu");
        }

        try{
            // does anyone even recognize this meme anymore?
            payroll.setID("dsfargeg");
        }
        catch(RuntimeException e){
            System.out.println(e);
            payroll.setID("ZZ2626");
        }

        try{
            payroll.setHours(-1);
        }
        catch(RuntimeException e){
            System.out.println(e);
            payroll.setHours(0);
        }

        try{
            payroll.setRate(26.0);
        }
        catch(RuntimeException e){
            System.out.println(e);
            payroll.setRate(25.0);
        }

        // test getters again
        printPayroll(payroll);
    }
}
