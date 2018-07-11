package com.company;

import java.util.Scanner;  // 100/100

public class _02_Parse_URL2 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String[] url = scanner.nextLine().split("://");
        if (url.length == 2) {
            String protocol = url[0];
            int index = url[1].indexOf("/");
            String server = url[1].substring(0, index);
            String resource = url[1].substring(index + 1);
            System.out.printf("Protocol = %s\nServer = %s\nResources = %s\n", protocol, server, resource);
        } else {
            System.out.println("Invalid URL");
        }
    }
}
