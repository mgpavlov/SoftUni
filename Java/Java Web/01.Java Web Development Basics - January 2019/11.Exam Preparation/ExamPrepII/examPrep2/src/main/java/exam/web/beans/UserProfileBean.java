package exam.web.beans;

import exam.domain.models.service.UserServiceModel;
import exam.domain.models.view.UserProfileViewModel;
import org.modelmapper.ModelMapper;
import exam.service.UserService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class UserProfileBean extends BaseBean{

    private UserService userService;
    private ModelMapper modelMapper;

    public UserProfileBean() {
    }

    @Inject
    public UserProfileBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    public UserProfileViewModel takeUserProfile(String id) {
        /*Map<String, String> requestParameterMap = FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap();

        String id = requestParameterMap.get("id");

        UserServiceModel userServiceModel = this.userService.getUserById(id);*/

        UserServiceModel userServiceModel =this.userService.findUserById(id);
        if (userServiceModel == null) {
            throw new IllegalArgumentException("Something went wrong!");
        }
        return modelMapper.map(userServiceModel, UserProfileViewModel.class);
    }
}
