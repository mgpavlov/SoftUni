package app.ccb.domain.dtos;

import javax.validation.constraints.NotEmpty;

public class BranchDto {

    @NotEmpty
    private String name;

    public BranchDto() {
    }

    public BranchDto(@NotEmpty String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
