package exam.web.beans;

import exam.domain.models.service.JobAppServiceModel;
import exam.domain.models.view.JobAppProfileViewModel;
import exam.service.JobAppService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Map;

@Named
@RequestScoped
public class JobAppDetailsBean extends BaseBean{

    private JobAppService jobAppService;
    private ModelMapper modelMapper;

    public JobAppDetailsBean() {
    }

    @Inject
    public JobAppDetailsBean(JobAppService jobAppService, ModelMapper modelMapper) {
        this.jobAppService = jobAppService;
        this.modelMapper = modelMapper;
    }

    public JobAppProfileViewModel takeJobAppProfile() {
        Map<String, String> requestParameterMap = FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap();

        String id = requestParameterMap.get("id");

        JobAppServiceModel jobAppServiceModel = this.jobAppService.findJobAppById(id);

        /*JobAppServiceModel jobAppServiceModel =this.jobAppService.findJobAppById(id);*/
        if (jobAppServiceModel == null) {
            throw new IllegalArgumentException("Something went wrong!");
        }
        return modelMapper.map(jobAppServiceModel, JobAppProfileViewModel.class);
    }
}
