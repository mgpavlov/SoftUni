package p09CollectionHierarchy;

import p09CollectionHierarchy.AddCollection;
import p09CollectionHierarchy.AddRemoveCollection;
import p09CollectionHierarchy.MyList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static java.lang.System.in;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        String[] arr = reader.readLine().split(" ");
        int n = Integer.parseInt(reader.readLine());
        reader.close();

        AddCollection addCollection = new AddCollection(new ArrayList<>());
        AddRemoveCollection addRemoveCollection = new AddRemoveCollection(new ArrayList<>());
        MyList myList = new MyList(new ArrayList<>());

        for (int i = 0; i < arr.length; i++) {
            System.out.print(addCollection.add(arr[i]));
            if (i < arr.length-1) {
                System.out.print(" ");
            }else {
                System.out.println();
            }
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(addRemoveCollection.add(arr[i]));
            if (i < arr.length-1) {
                System.out.print(" ");
            }else {
                System.out.println();
            }

        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(myList.add(arr[i]));
            if (i < arr.length-1) {
                System.out.print(" ");
            }else {
                System.out.println();
            }
        }

        for (int i = 0; i < n; i++) {
            System.out.print(addRemoveCollection.remove());
            if (i < n-1) {
                System.out.print(" ");
            }else {
                System.out.println();
            }
        }

        for (int i = 0; i < n; i++) {
            System.out.print(myList.remove());
            if (i < n-1) {
                System.out.print(" ");
            }else {
                System.out.println();
            }
        }



    }
}
