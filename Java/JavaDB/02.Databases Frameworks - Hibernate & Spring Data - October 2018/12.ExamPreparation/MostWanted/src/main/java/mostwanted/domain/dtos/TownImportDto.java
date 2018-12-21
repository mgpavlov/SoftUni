package mostwanted.domain.dtos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import mostwanted.domain.entities.Racer;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class TownImportDto {
    @Expose
    private String name;



    public TownImportDto() {
    }

    @NotNull
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
