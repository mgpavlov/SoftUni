package metube.domain.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User extends BaseEntity{
    private String username;
    private String password;
    private String email;
    private List<Tube> tubes;

    public User() {
    }

    @Column(name = "usernam", nullable = false, unique = true)
    public String getUsername() {
        return this.username;
    }

    @Column(name = "password", nullable = false)
    public String getPassword() {
        return this.password;
    }

    @Column(name = "email", nullable = false)
    public String getEmail() {
        return this.email;
    }

    @OneToMany(targetEntity = Tube.class, mappedBy = "uploader")
    public List<Tube> getTubes() {
        return this.tubes;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTubes(List<Tube> tubes) {
        this.tubes = tubes;
    }
}
