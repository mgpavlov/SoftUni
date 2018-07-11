package p03Mankind;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] inputStudent = reader.readLine().split("\\s+");
        String firstStudentName = inputStudent[0];
        String lastStudentName = inputStudent[1];
        String facultyNumber = inputStudent[2];

        String[] inputWorker = reader.readLine().split("\\s+");
        String firstWorkerName = inputWorker[0];
        String lastWorkerName = inputWorker[1];
        double salary = Double.parseDouble(inputWorker[2]);
        double workingHoursPerWeek = Double.parseDouble(inputWorker[3]);

        try {
            Student student = new Student(firstStudentName, lastStudentName, facultyNumber);
            Worker worker = new Worker(firstWorkerName, lastWorkerName, salary, workingHoursPerWeek);

            System.out.print(student);
            System.out.println(worker);
        }catch (IllegalArgumentException ex){
            System.out.println(ex.getMessage());
        }
    }
}
