package exam.domain.models.view;

import exam.domain.entities.Gender;
import exam.domain.entities.User;

import java.util.List;

public class FriendsViewModel {
    private String id;
    private String username;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
