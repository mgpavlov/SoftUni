package _03_Inheritance.EXERCISES._03_Mankind;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        Scanner scan = new Scanner(System.in);

        try {
            String[] studentsArgs = scan.nextLine().split("\\s+");

            String firstName = studentsArgs[0];
            String lastName = studentsArgs[1];
            String facultyNumber = studentsArgs[2];

            Student student = new Student(firstName, lastName, facultyNumber);
            System.out.println(student);

            String[] workerArgs = scan.nextLine().split("\\s+");
            firstName = workerArgs[0];
            lastName = workerArgs[1];
            Double salary = Double.valueOf(workerArgs[2]);
            Double workingHours = Double.valueOf(workerArgs[3]);

            Worker worker = new Worker(firstName, lastName, salary, workingHours);
            System.out.println(worker);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
}
