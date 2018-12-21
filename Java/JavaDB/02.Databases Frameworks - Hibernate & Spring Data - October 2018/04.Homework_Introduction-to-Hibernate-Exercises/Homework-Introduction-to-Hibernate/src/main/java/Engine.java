import entities.Address;
import entities.Employee;
import entities.Project;
import entities.Town;

import javax.persistence.EntityManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;


public class Engine implements Runnable {

    private final EntityManager entityManager;

    public Engine(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void run() {
//       this.p02RemoveObjects();

        try {
            this.p03ContainsEmployee();
        } catch (IOException e) {
            e.printStackTrace();
        }

//       this.p04EmployeesWithSalaryOver50000();

//       this.p05EmployeesFromDepartment();

//       this.p06AddingАNewAddressАndUpdatingEmployee();

//       this.p07AddressesWithEmployeeCount();

//       this.p08GetEmployeeWithProject();

//       this.p09FindLatest10Projects();

//       this.p10IncreaseSalaries();

//       this.p11RemoveTowns();

//       this.p12FindEmployeesByFirstName();

//       this.p13EmployeesMaximumSalaries();
    }

    private void p02RemoveObjects(){
        try {
            List<Town> towns = entityManager
                    .createQuery("SELECT t FROM Town t", Town.class)
                    .getResultList();

            for (Town town : towns) {
                if (town.getName().length() > 5) {
                    entityManager.detach(town);
                }
            }

            entityManager.getTransaction().begin();

            for (Town town : towns) {
                if (entityManager.contains(town)) {
                    System.out.print(town.getName() + " -> ");

                    town.setName(town.getName().toLowerCase());

                    System.out.println(town.getName());
                }
            }

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    private void p03ContainsEmployee() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Employee's first and last names: ");
        String[] employeeNames = reader.readLine().trim().split("\\s+");

        List<Employee> employees = entityManager
                .createQuery("SELECT e FROM Employee AS e WHERE e.firstName = :firstName AND e.lastName = :lastName", Employee.class)
                .setParameter("firstName", employeeNames[0])
                .setParameter("lastName", employeeNames[1])
                .getResultList();

        if (!employees.isEmpty()) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }

    private void p04EmployeesWithSalaryOver50000(){
        entityManager
                .createQuery("SELECT e FROM Employee AS e WHERE e.salary > 50000", Employee.class)
                .getResultList()
                .forEach(employee -> System.out.println(employee.getFirstName()));
    }

    private void p05EmployeesFromDepartment(){
        entityManager
                .createQuery("SELECT e FROM Employee AS e WHERE e.department.name = 'Research and Development' ORDER BY e.salary, e.id", Employee.class)
                .getResultList()
                .forEach(employee -> System.out.printf("%s %s from %s - $%.2f%n",
                        employee.getFirstName(), employee.getLastName(),
                        employee.getDepartment().getName(), employee.getSalary()));
    }

    private void p06AddingANewAddressAndUpdateEmployee(){
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Last name: ");
            String lastName = reader.readLine().trim();
            Employee employee = entityManager
                    .createQuery("SELECT e FROM Employee AS e WHERE e.lastName = :lastName", Employee.class)
                    .setParameter("lastName", lastName)
                    .getSingleResult();

            Town sofia = entityManager
                    .createQuery("SELECT t FROM Town AS t WHERE t.name = 'Sofia'", Town.class)
                    .getSingleResult();

            Address address = new Address();
            address.setText("Vitoshka 15");
            address.setTown(sofia);

            entityManager.getTransaction().begin();

            entityManager.persist(address);

            employee.setAddress(address);

            entityManager.getTransaction().commit();

            System.out.printf("%s %s changed address to %s, %s%n",
                    employee.getFirstName(), employee.getLastName(),
                    employee.getAddress().getText(), employee.getAddress().getTown().getName());

        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    private void p07AddressesWithEmployeeCount(){
        StringBuilder addresses = new StringBuilder();

        entityManager
                .createQuery("SELECT a FROM  Address AS a ORDER BY a.employees.size DESC, a.town.id", Address.class)
                .setMaxResults(10)
                .getResultList()
                .forEach(address -> addresses.append(String.format("%s, %s - %d employees%n",
                        address.getText(), address.getTown().getName(), address.getEmployees().size())));
        System.out.println(addresses.toString().trim());
    }

    private void p08GetEmployeeWithProject() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Employee id: ");
            Integer id = Integer.valueOf(reader.readLine());

            Employee employee = entityManager
                    .createQuery("SELECT e FROM Employee AS e WHERE e.id = :id", Employee.class)
                    .setParameter("id", id)
                    .getSingleResult();

            StringBuilder sb = new StringBuilder();

            sb.append(employee.getFirstName()).append(" ").append(employee.getLastName())
                    .append(" - ").append(employee.getJobTitle()).append(System.lineSeparator());

            employee.getProjects().stream()
                    .sorted(Comparator.comparing(Project::getName))
                    .forEach(project -> sb.append("\t").append(project.getName()).append(System.lineSeparator()));

            System.out.println(sb.toString().trim());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void p09FindLatest10Projects(){
        StringBuilder projects = new StringBuilder();

        entityManager
                .createQuery("SELECT p FROM Project AS p ORDER BY p.startDate DESC", Project.class)
                .setMaxResults(10)
                .getResultList()
                .stream()
                .sorted(Comparator.comparing(Project::getName))
                .forEach(project -> projects
                        .append("Project name: ").append(project.getName()).append(System.lineSeparator())
                        .append("\tProject Description: ").append(project.getDescription()).append(System.lineSeparator())
                        .append("\tProject Start Date: ").append(project.getStartDate()).append(System.lineSeparator())
                        .append("\tProject End Date: ").append(project.getEndDate()).append(System.lineSeparator()));

        System.out.println(projects.toString().trim());
    }

    private void p10IncreaseSalaries(){
        entityManager.getTransaction().begin();

        entityManager
                .createQuery("SELECT e FROM Employee AS e " +
                        "WHERE e.department.name IN ('Engineering', 'Tool Design', 'Marketing', 'Information Services') " +
                        "ORDER BY e.id", Employee.class)
                .getResultList()
                .forEach(employee -> {
                    employee.setSalary(employee.getSalary().multiply(BigDecimal.valueOf(1.12)));
                    System.out.printf("%s %s($%.2f)%n", employee.getFirstName(),
                            employee.getLastName(), employee.getSalary());
                });

        entityManager.getTransaction().commit();
    }

    private void p11RemoveTowns(){

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Town name: ");
            String townName = reader.readLine().trim();

            Town town = entityManager.createQuery("SELECT t FROM Town AS t WHERE t.name = :townName", Town.class)
                    .setParameter("townName", townName)
                    .getSingleResult();

            List<Address> addresses = entityManager
                    .createQuery("SELECT a FROM Address AS a WHERE a.town.name = :townName", Address.class)
                    .setParameter("townName", townName)
                    .getResultList();

            String output = String.format("%d address%s in %s deleted%n",
                    addresses.size(), (addresses.size() != 1) ? "es" : "", town.getName());

            entityManager.getTransaction().begin();

            addresses.forEach(address -> {
                for (Employee employee : address.getEmployees()) {
                    employee.setAddress(null);
                }
                address.setTown(null);
                entityManager.remove(address);
            });

            entityManager.remove(town);

            entityManager.getTransaction().commit();

            System.out.println(output);

        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    private void p12FindEmployeesByFirstName(){

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("First name starting letters: ");
            String p = reader.readLine().trim();
            entityManager
                    .createQuery("SELECT e FROM Employee AS e WHERE e.firstName LIKE :p", Employee.class)
                    .setParameter("p", p + "%")
                    .getResultList()
                    .forEach(employee -> System.out.printf("%s %s - %s - ($%.2f)%n", employee.getFirstName(),
                            employee.getLastName(), employee.getJobTitle(), employee.getSalary()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void p13EmployeesMaximumSalaries(){
        StringBuilder employees = new StringBuilder();

        entityManager
                .createQuery("SELECT e FROM  Employee AS e " +
                        "WHERE e.salary NOT BETWEEN 30000 AND 70000 " +
                        "GROUP BY e.department " +
                        "ORDER BY e.salary DESC", Employee.class)
                .getResultList()
                .stream()
                .sorted(Comparator.comparing(e -> e.getDepartment().getId()))
                .forEach(employee -> employees.append(String.format("%s - %.2f%n",
                        employee.getDepartment().getName(), employee.getSalary())));


        System.out.println(employees.toString().trim());
    }

}
