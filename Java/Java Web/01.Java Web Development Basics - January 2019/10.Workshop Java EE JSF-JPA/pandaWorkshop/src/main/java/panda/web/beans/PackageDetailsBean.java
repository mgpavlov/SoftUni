package panda.web.beans;

import panda.domain.models.service.PackageServiceModel;
import panda.service.PackageService;


import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class PackageDetailsBean {

    private PackageService packageService;

    public PackageDetailsBean() {
    }

    @Inject
    public PackageDetailsBean(PackageService packageService) {
        this.packageService = packageService;
    }

    public PackageServiceModel getPackage(String id) {
        return this.packageService.getPackageById(id);
    }
}
