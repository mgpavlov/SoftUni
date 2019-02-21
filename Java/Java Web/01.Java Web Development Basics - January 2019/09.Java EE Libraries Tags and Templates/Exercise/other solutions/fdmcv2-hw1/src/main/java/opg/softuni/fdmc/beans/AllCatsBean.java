package opg.softuni.fdmc.beans;

import opg.softuni.fdmc.domain.dto.CatViewDto;
import opg.softuni.fdmc.services.CatService;
import opg.softuni.fdmc.util.ApplicationUtils;
import opg.softuni.fdmc.util.GenericComparator;

import javax.inject.Inject;
import javax.inject.Named;
import java.beans.IntrospectionException;
import java.util.List;

@Named(value = "allCats")
public class AllCatsBean {
    private final CatService employeeService;


    @Inject
    public AllCatsBean(CatService employeeService) {
        this.employeeService = employeeService;
    }

    public List<CatViewDto> getListOfCats() {
        List<CatViewDto> allCats = this.employeeService.listAllCats();
        String field = ApplicationUtils.getRequest().getParameter("sort");
        if(field == null){
            return allCats;
        }else{
            try {
                return GenericComparator.compare(allCats,field, CatViewDto.class);
            } catch (ReflectiveOperationException | IntrospectionException e) {
//            e.printStackTrace();
                return allCats;
            }
        }

    }



}
