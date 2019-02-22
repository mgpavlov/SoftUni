package exam.web.beans;

import exam.domain.models.view.UserHomeViewModel;
import org.modelmapper.ModelMapper;
import exam.domain.models.service.UserServiceModel;
import exam.service.UserService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class HomeBean extends BaseBean{

    private List<UserHomeViewModel> notFriends;
    private UserServiceModel loggedInUser;

    private UserService userService;
    private ModelMapper modelMapper;

    public HomeBean() {
    }

    @Inject
    public HomeBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void init() {
        String currentUsername = (String) ((HttpSession) FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getSession(true)).getAttribute("username");

        String currentUserId = (String) ((HttpSession) FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getSession(true)).getAttribute("userId");

        this.loggedInUser = userService
                .findUserById(currentUserId);

        this.notFriends = this.userService.findAllUsers().stream()
                .filter(u->!u.getId().equals(currentUserId))
                .filter(u-> !loggedInUser.getFriends().stream().map(UserServiceModel::getUsername).collect(Collectors.toList()).contains(u.getUsername()))
                .map(u->modelMapper.map(u, UserHomeViewModel.class))
                .collect(Collectors.toList());
    }

    public List<UserHomeViewModel> getNotFriends() {
        return this.notFriends;
    }

    public void setNotFriends(List<UserHomeViewModel> notFriends) {
        this.notFriends = notFriends;
    }

    public UserServiceModel getLoggedInUser() {
        return this.loggedInUser;
    }

    public void setLoggedInUser(UserServiceModel loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public void addFriend(String id){

        UserServiceModel newFriend = this.userService.findUserById(id);

        this.loggedInUser.getFriends().add(newFriend);
        newFriend.getFriends().add(this.loggedInUser);

        if (!this.userService.userUpdate(this.loggedInUser) || !this.userService.userUpdate(newFriend)){
            throw new IllegalArgumentException("Cannot add friend!");
        }
        this.redirect("home");
    }

    public void removeUser(String id){
        UserServiceModel userServiceModel = this.userService.findUserById(id);

        if (!this.userService.userDelete(userServiceModel)){
            throw new IllegalArgumentException("Cannot delete friend!");
        }
        this.redirect("home");
    }
}
