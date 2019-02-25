/*
package exam.domain.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "users")
public class Base extends BaseEntity {

    private String username;
    private String password;
    private Gender gender;
    private String email;
    private BigDecimal salary;
    private List<Base> friends;

    public Base() {
    }

    @Column(name = "username", nullable = false, unique = true, updatable = false)
    public String getUsername() {
        return username;
    }


    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    public Gender getGender() {
        return this.gender;
    }


    @Column(name = "email", nullable = false)
    public String getEmail() {
        return this.email;
    }


    @Column(name = "salary", nullable = false, columnDefinition = "DECIMAL(10, 2) DEFAULT '0.00'")
    public BigDecimal getSalary() {
        return this.salary;
    }

    @ManyToMany
    @JoinTable(
            name = "users_friends",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id", referencedColumnName = "id")
    )
    public List<Base> getFriends() {
        return this.friends;
    }

}

    @Column(name = "customer", nullable = false, columnDefinition = "TEXT")
    public String getCustomer() {
        return this.customer;
    }

    @Column(name = "name", nullable = false, columnDefinition = "varchar(255)")
    @Size(min = 3, max = 30)
    public String getName() {
        return this.name;
    }

    @Column(name = "age", nullable = false)
    @Min(15)
    @Max(80)
    public Integer getAge() {
        return this.age;
    }

    @Column(name = "name", nullable = false, unique = true, columnDefinition = "varchar(255)")
    @Size(min = 3, max = 30)
    public String getName() {
        return this.name;
    }

    @Column(name = "price", nullable = false)
    @DecimalMin("0.01")
    public BigDecimal getPrice() {
        return this.price;
    }

    @Column(name = "date_time", nullable = false)
    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    @Column(name = "type", nullable = false, columnDefinition = "ENUM('ForHere', 'ToGo') default 'ForHere'")
    @Enumerated(value = EnumType.STRING)
    public OrderType getType() {
        return this.type;
    }*/
