package exam.web.beans;

import exam.domain.models.service.JobAppServiceModel;
import exam.domain.models.view.JobAppDeleteViewModel;
import exam.domain.models.view.JobAppProfileViewModel;
import exam.service.JobAppService;
import org.modelmapper.ModelMapper;
import org.primefaces.util.Constants;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Named
@RequestScoped
public class JobAppDeleteBean extends BaseBean{

    private JobAppService jobAppService;
    private ModelMapper modelMapper;
    private String jobId;

    public JobAppDeleteBean() {
    }

    @Inject
    public JobAppDeleteBean(JobAppService jobAppService, ModelMapper modelMapper) {
        this.jobAppService = jobAppService;
        this.modelMapper = modelMapper;
    }

    public JobAppDeleteViewModel jobAppDetails(String id) {
        JobAppServiceModel jobAppServiceModel = this.jobAppService.findJobAppById(id);
        if (jobAppServiceModel == null) {
            throw new IllegalArgumentException("Something went wrong!");
        }
        return modelMapper.map(jobAppServiceModel, JobAppDeleteViewModel.class);
    }

    public void removeJobApp() {
        String id = ((HttpServletRequest)FacesContext
                .getCurrentInstance().getExternalContext()
                .getRequest()).getParameter("jobId");

        JobAppServiceModel jobAppServiceModel = this.jobAppService.findJobAppById(id);

        if (!this.jobAppService.jobAppDelete(jobAppServiceModel)) {
            throw new IllegalArgumentException("Cannot delete job application!");
        }
        this.redirect("home");
    }

    public String getJobId() {
        return this.jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }
}
