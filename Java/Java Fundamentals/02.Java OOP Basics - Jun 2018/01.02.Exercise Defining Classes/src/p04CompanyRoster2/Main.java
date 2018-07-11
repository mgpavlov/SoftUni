package p04CompanyRoster2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        List<Employee> employees =  new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String[] input = reader.readLine().split("\\s+");
            String name = input[0];
            double salary = Double.parseDouble(input[1]);
            String position = input[2];
            String department = input[3];
            Employee employee;
            if (input.length == 4){
                employee = new Employee(name, salary, position, department);
            }else if (input.length == 5){
                if (input[4].contains("@")){
                    String email = input[4];
                    employee = new Employee(name, salary, position, department, email);
                }else{
                    int age = Integer.parseInt(input[4]);
                    employee = new Employee(name, salary, position, department, age);
                }
            }else {
                String email = input[4];
                int age = Integer.parseInt(input[5]);
                employee = new Employee(name, salary, position, department, email, age);
            }
            employees.add(employee);
        }
        StringBuilder result = new StringBuilder();
        employees.stream().collect(Collectors.groupingBy(Employee::getDepartment)).entrySet().stream()
        .sorted((e1, e2)->{
            return Double.compare(
                    e2.getValue().stream().mapToDouble(Employee::getSalary).average().getAsDouble(),
                    e1.getValue().stream().mapToDouble(e->e.getSalary()).average().getAsDouble()
            );
        }).limit(1).forEach(e->{
            result.append(String.format("Highest Average Salary: %s", e.getKey())).append(System.lineSeparator());
            e.getValue().stream().sorted(Comparator.comparing(Employee::getSalary, Comparator.reverseOrder()))
                    .forEach(en->{
                result.append(en.toString()).append(System.lineSeparator());
            });
        });
        System.out.println(result);
    }
}
