package com.company;

import java.util.Scanner;  // 100/100

public class _02_Parse_URL {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        String[] input = scan.nextLine().split("://");

        if (input.length != 2) {
            System.out.println("Invalid URL");
        } else {
            String protocol = input[0];
            String[] server = input[1].split("/");
            System.out.println("Protocol = " + protocol);
            System.out.println("Server = " + server[0]);
            StringBuilder rest = new StringBuilder();

            for (int i = 1; i < server.length; i++) {
                if (i < server.length - 1) {
                    rest.append(server[i]).append("/");
                } else {
                    rest.append(server[i]);
                }
            }
            System.out.println("Resources = " + rest);
        }
    }
}
