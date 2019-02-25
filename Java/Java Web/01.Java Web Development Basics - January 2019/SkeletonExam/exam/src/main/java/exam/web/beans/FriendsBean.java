package exam.web.beans;

import exam.domain.models.service.UserServiceModel;
import exam.domain.models.view.FriendsViewModel;
import org.modelmapper.ModelMapper;
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
public class FriendsBean extends BaseBean{
    private UserServiceModel loggedInUser;
    private List<FriendsViewModel> model;
    private UserService userService;
    private ModelMapper modelMapper;

    public FriendsBean() {
    }

    @Inject
    public FriendsBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void init() {
        String currentUserId = (String) ((HttpSession) FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getSession(true)).getAttribute("userId");

        this.loggedInUser = userService.findUserById(currentUserId);
        this.model = this.loggedInUser.getFriends()
                .stream().map(u->modelMapper.map(u, FriendsViewModel.class))
                .collect(Collectors.toList());
    }

    public void unFriend(String id) throws IOException {
        UserServiceModel user = this.userService.findUserById(id);

        this.loggedInUser.setFriends(this.loggedInUser.getFriends()
                        .stream()
                        .filter(u->!u.getUsername().equals(user.getUsername()))
                        .collect(Collectors.toList()));

        user.setFriends(user.getFriends().stream().filter(u->!u.getUsername().equals(this.loggedInUser.getUsername())).collect(Collectors.toList()));

        boolean isCurrentUserUpdate = this.userService.userUpdate(this.loggedInUser);
        boolean isUserUpdate = this.userService.userUpdate(user);

        if (!isCurrentUserUpdate || !isUserUpdate){
            throw new IllegalArgumentException("Cannot unfriend user!");
        }

        this.redirect("friends");
    }

    public UserServiceModel getLoggedInUser() {
        return this.loggedInUser;
    }

    public void setLoggedInUser(UserServiceModel loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public List<FriendsViewModel> getModel() {
        return this.model;
    }

    public void setModel(List<FriendsViewModel> model) {
        this.model = model;
    }
}
