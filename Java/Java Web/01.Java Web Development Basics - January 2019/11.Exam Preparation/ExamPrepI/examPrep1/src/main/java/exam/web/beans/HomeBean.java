package exam.web.beans;

import exam.domain.models.service.JobAppServiceModel;
import exam.domain.models.service.UserServiceModel;
import exam.domain.models.view.JobAppHomeViewModel;
import exam.service.JobAppService;
import exam.service.UserService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class HomeBean extends BaseBean {

    private List<JobAppHomeViewModel> jobs;
    private UserServiceModel loggedInUser;

    private UserService userService;
    private JobAppService jobAppService;
    private ModelMapper modelMapper;

    public HomeBean() {
    }

    @Inject
    public HomeBean(UserService userService, ModelMapper modelMapper, JobAppService jobAppService) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.jobAppService = jobAppService;
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

        this.jobs = this.jobAppService.findAllJobApps().stream()
                .map(u -> modelMapper.map(u, JobAppHomeViewModel.class))
                .collect(Collectors.toList());
    }

    public List<JobAppHomeViewModel> getJobs() {
        return this.jobs;
    }

    public void setJobs(List<JobAppHomeViewModel> jobs) {
        this.jobs = jobs;
    }

    public UserServiceModel getLoggedInUser() {
        return this.loggedInUser;
    }

    public void setLoggedInUser(UserServiceModel loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

}
