package panda.web.beans;

import org.modelmapper.ModelMapper;
import panda.domain.entities.Status;
import panda.domain.models.view.PackageViewModel;
import panda.service.PackageService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class DeliveredPackagesBean {

    private List<PackageViewModel> packages;

    private PackageService packageService;
    private ModelMapper modelMapper;

    public DeliveredPackagesBean() {
    }

    @Inject
    public DeliveredPackagesBean(PackageService packageService, ModelMapper modelMapper) {
        this.packageService = packageService;
        this.modelMapper = modelMapper;
        this.initPackages();
    }

    private void initPackages() {
        this.packages = this.packageService
                .findAllPackagesByStatus(Status.Delivered)
                .stream()
                .map(p -> {
                    PackageViewModel packageViewModel = this.modelMapper.map(p, PackageViewModel.class);
                    packageViewModel.setRecipient(p.getRecipient().getUsername());

                    return packageViewModel;
                })
                .collect(Collectors.toList());
    }

    public List<PackageViewModel> getPackages() {
        return packages;
    }

    public void setPackages(List<PackageViewModel> packages) {
        this.packages = packages;
    }

    /*public void viewDetails(String id) throws IOException {

        FacesContext.getCurrentInstance()
                .getExternalContext()
                .redirect("/view/packages/details.xhtml");
    }*/
}
