package alararestaurant.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "categories")
public class Category extends BaseEntity {

    private String name;
    private List<Item> items;

    public Category() {
        this.items = new ArrayList<>();
    }

    @Column(name = "name", nullable = false, columnDefinition = "varchar(255)")
    @Size(min = 3, max = 30)
    public String getName() {
        return this.name;
    }

    @OneToMany(mappedBy = "category")
    public List<Item> getItems() {
        return this.items;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
