package org.softuni.onlinegrocery.domain.models.service;

public class CategoryServiceModel extends BaseServiceModel {

    private String name;
    private boolean isDeleted;

    public CategoryServiceModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDeleted() {
        return this.isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
