package org.softuni.cardealer.domain.models.binding;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AddSupplierBindingModel {
    private String name;

    private boolean isImporter;

    public AddSupplierBindingModel() {
    }

    @NotNull
    @NotEmpty
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    public boolean getIsImporter() {
        return this.isImporter;
    }

    public void setIsImporter(boolean importer) {
        isImporter = importer;
    }
}
