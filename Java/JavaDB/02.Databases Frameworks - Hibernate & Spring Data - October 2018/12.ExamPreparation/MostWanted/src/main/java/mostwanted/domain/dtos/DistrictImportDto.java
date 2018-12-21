package mostwanted.domain.dtos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.validation.constraints.NotNull;

public class DistrictImportDto {
    @Expose
    private String name;
    @Expose
    private String townName;

    public DistrictImportDto() {
    }

    @NotNull
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTown() {
        return this.townName;
    }

    public void setTown(String town) {
        this.townName = town;
    }
}
