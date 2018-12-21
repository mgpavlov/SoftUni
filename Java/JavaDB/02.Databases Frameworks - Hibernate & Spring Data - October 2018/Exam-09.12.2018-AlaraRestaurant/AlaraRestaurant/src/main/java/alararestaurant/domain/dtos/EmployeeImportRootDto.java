package alararestaurant.domain.dtos;

import com.google.gson.annotations.Expose;

import java.util.List;

public class EmployeeImportRootDto {

    @Expose
    private List<EmployeeImportDto> employeeImportDtos;

    public EmployeeImportRootDto() {
    }

    public List<EmployeeImportDto> getEmployeeImportDtos() {
        return this.employeeImportDtos;
    }

    public void setEmployeeImportDtos(List<EmployeeImportDto> employeeImportDtos) {
        this.employeeImportDtos = employeeImportDtos;
    }
}
