package p04CompanyRoster1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        Map<String, Double> depSalary = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String[] input = reader.readLine().split("\\s+");
            String name = input[0];
            double salary = Double.parseDouble(input[1]);
            String position = input[2];
            String department = input[3];
            Employee employee = new Employee(name, salary, position, department);

            depSalary.putIfAbsent(department, 0d);
            depSalary.put(department, depSalary.get(department)+salary);

            if (input.length > 4){
                if (input[4].contains("@")){
                    String email = input[4];
                    employee.setEmail(email);
                    if (input.length == 6){
                        int age = Integer.parseInt(input[5]);
                        employee.setAge(age);
                    }
                }else{
                    int age = Integer.parseInt(input[4]);
                    employee.setAge(age);
                    if (input.length == 6){
                        String email = input[5];
                        employee.setEmail(email);
                    }
                }
            }

            Employee.setEmployees(employee);
        }

        StringBuilder result = new StringBuilder("Highest Average Salary: ");
        StringBuilder mvpDep = new StringBuilder();
        depSalary.entrySet().stream().sorted((a,b)->{
            return b.getValue().compareTo(a.getValue());
        }).limit(1).forEach(e-> mvpDep.append(e.getKey()));

        result.append(mvpDep).append("\n");

        List<Employee> resultList = Employee.getEmployees();

        resultList.stream().filter(e->e.getDepartment().equals(mvpDep.toString())).sorted((a, b)->{
            return Double.compare(b.getSalary(), a.getSalary());
        }).forEach( p ->{
            result.append(p.getName()).append(" ").append(String.format("%.2f",(p.getSalary()))).append(" ").append(p.getEmail()).append(" ").append(p.getAge()).append("\n");
        });
        System.out.println(result);
    }
}
