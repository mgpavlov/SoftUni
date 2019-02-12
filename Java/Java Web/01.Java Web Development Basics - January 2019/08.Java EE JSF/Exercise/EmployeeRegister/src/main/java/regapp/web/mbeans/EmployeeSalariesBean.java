package regapp.web.mbeans;

import org.modelmapper.ModelMapper;
import regapp.domain.models.view.EmployeeListViewModel;
import regapp.service.EmployeeService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class EmployeeSalariesBean {

    private BigDecimal totalMoneyNeeded;
    private BigDecimal averageSalary;

    private EmployeeService employeeService;
    private ModelMapper modelMapper;

    public EmployeeSalariesBean() {
    }

    @Inject
    public EmployeeSalariesBean(EmployeeService employeeService, ModelMapper modelMapper) {
        this.employeeService = employeeService;
        this.modelMapper = modelMapper;
        this.totalMoneyNeeded = this.employeeService.getTotalMoneyNeeded();
        this.averageSalary = this.employeeService.getAverageSalary();
    }


    public BigDecimal getTotalMoneyNeeded() {
        return this.totalMoneyNeeded;
    }

    public void setTotalMoneyNeeded(BigDecimal totalMoneyNeeded) {
        this.totalMoneyNeeded = totalMoneyNeeded;
    }

    public BigDecimal getAverageSalary() {
        return this.averageSalary;
    }

    public void setAverageSalary(BigDecimal averageSalary) {
        this.averageSalary = averageSalary;
    }
}
