package regapp.web.mbeans;

import org.modelmapper.ModelMapper;
import regapp.domain.entities.Employee;
import regapp.domain.models.binding.EmployeeRegisterBindingModel;
import regapp.domain.models.service.EmployeeServiceModel;
import regapp.service.EmployeeService;
import regapp.util.ValidationUtil;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class EmployeeRegisterBean {

    private EmployeeRegisterBindingModel employeeRegisterBindingModel;
    private ValidationUtil validationUtil;
    private EmployeeService employeeService;
    private ModelMapper modelMapper;


    public EmployeeRegisterBean() {
        this.employeeRegisterBindingModel = new EmployeeRegisterBindingModel();
    }

    @Inject
    public EmployeeRegisterBean(EmployeeService employeeService, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this();
        this.employeeService = employeeService;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    public EmployeeRegisterBindingModel getEmployeeRegisterBindingModel() {
        return this.employeeRegisterBindingModel;
    }

    public void setEmployeeRegisterBindingModel(EmployeeRegisterBindingModel employeeRegisterBindingModel) {
        this.employeeRegisterBindingModel = employeeRegisterBindingModel;
    }

    public void register() throws IOException {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        if(!this.validationUtil.validate(this.modelMapper.map(this.getEmployeeRegisterBindingModel(), Employee.class))){
            context.redirect("/");
        }
        this.employeeService
                .saveEmployee(this.modelMapper.map(this.employeeRegisterBindingModel, EmployeeServiceModel.class));
        context.redirect("/");

        /*this.employeeService
                .saveEmployee(this.modelMapper.map(this.employeeRegisterBindingModel, EmployeeServiceModel.class));

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect("/");*/
    }





}
