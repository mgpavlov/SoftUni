package com.company;

import java.util.Scanner;

public class l_04_Greeting {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String firstName = input.nextLine();
        String lastName = input.nextLine();

        if (firstName.equals("")) {
            firstName = "*****";
        }
        if (lastName.equals("")) {
            lastName = "*****";
        }
        System.out.println("Hello, " + firstName + " " + lastName + "!");

    }
}
