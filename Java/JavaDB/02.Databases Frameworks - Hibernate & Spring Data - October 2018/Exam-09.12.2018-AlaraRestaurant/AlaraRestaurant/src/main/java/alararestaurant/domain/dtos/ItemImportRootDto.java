package alararestaurant.domain.dtos;

import com.google.gson.annotations.Expose;

import java.util.List;

public class ItemImportRootDto {

    @Expose
    private List<ItemImportDto> itemImportDtos;

    public ItemImportRootDto() {
    }

    public List<ItemImportDto> getItemImportDtos() {
        return this.itemImportDtos;
    }

    public void setItemImportDtos(List<ItemImportDto> itemImportDtos) {
        this.itemImportDtos = itemImportDtos;
    }
}
