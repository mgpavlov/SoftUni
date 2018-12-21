package products.dto.bindings;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoriesImportXmlDto {

    @XmlElement(name = "category")
    private List<CategoryImportDto> categoryImportDtos;

    public CategoriesImportXmlDto() {
    }

    public List<CategoryImportDto> getCategoryImportDtos() {
        return this.categoryImportDtos;
    }

    public void setCategoryImportDtos(List<CategoryImportDto> categoryImportDtos) {
        this.categoryImportDtos = categoryImportDtos;
    }
}
