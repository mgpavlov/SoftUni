package app.ccb.domain.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity (name = "clients")
public class Client extends BaseEntity {

    private String fullName;
    private Integer age;
    private List<Employee> employees;
    private BankAccount bankAccount;

    public Client() {
        this.employees = new ArrayList<>();
    }

    @Column(name = "full_name", nullable = false)
    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Column(name = "age")
    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @OneToOne(targetEntity = BankAccount.class, mappedBy = "client")
    /*@JoinColumn(
            name = "bank_account_id",
            referencedColumnName = "id"
    )*/
    public BankAccount getBankAccount() {
        return this.bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    @ManyToMany(targetEntity = Employee.class)
    @JoinTable(
            name = "employees_clients",
            joinColumns = @JoinColumn(name = "client_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id")
    )
    public List<Employee> getEmployees() {
        return this.employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
