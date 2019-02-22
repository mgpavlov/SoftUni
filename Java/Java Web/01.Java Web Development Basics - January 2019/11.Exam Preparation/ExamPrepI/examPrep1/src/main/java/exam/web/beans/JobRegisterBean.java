package exam.web.beans;

import exam.domain.entities.JobApplication;
import exam.domain.models.binding.JobAppRegisterBindingModel;
import exam.domain.models.service.JobAppServiceModel;
import exam.service.JobAppService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class JobRegisterBean extends BaseBean {

    private JobAppRegisterBindingModel jobAppRegisterBindingModel;

    private JobAppService jobAppService;
    private ModelMapper modelMapper;

    public JobRegisterBean() {
    }

    @Inject
    public JobRegisterBean(JobAppService jobAppService, ModelMapper modelMapper) {
        this.jobAppService = jobAppService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void init() {
        this.jobAppRegisterBindingModel = new JobAppRegisterBindingModel();
    }

    public JobAppRegisterBindingModel getJobAppRegisterBindingModel() {
        return jobAppRegisterBindingModel;
    }

    public void setJobAppRegisterBindingModel(JobAppRegisterBindingModel jobAppRegisterBindingModel) {
        this.jobAppRegisterBindingModel = jobAppRegisterBindingModel;
    }

    public void register() throws IOException {

            if (!this.jobAppService.jobAppRegister(this.modelMapper.map(this.jobAppRegisterBindingModel, JobAppServiceModel.class))) {
                throw new IllegalArgumentException("Cannot register jobApp!");
            }

            this.redirect("home");
        /*try {

        } catch (Exception ex){
            this.redirect("register");
        }*/
    }
}
