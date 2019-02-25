package exam.util;

import javax.validation.Validation;
import javax.validation.Validator;

public class ValidationUtil {
    private Validator validator;

    public ValidationUtil(){
        this.validator = Validation
                .buildDefaultValidatorFactory()
                .getValidator();
    }

    public <M> boolean validate(M model){
        return this.validator.validate(model).size() == 0;
    }
}


/*
    public void register() throws IOException {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        if(!this.validationUtil.validate(this.modelMapper.map(this.getEmployeeRegisterBindingModel(), Employee.class))){
            context.redirect("/");
        }
        this.employeeService
                .saveEmployee(this.modelMapper.map(this.employeeRegisterBindingModel, EmployeeServiceModel.class));
        context.redirect("/");

    }*/
