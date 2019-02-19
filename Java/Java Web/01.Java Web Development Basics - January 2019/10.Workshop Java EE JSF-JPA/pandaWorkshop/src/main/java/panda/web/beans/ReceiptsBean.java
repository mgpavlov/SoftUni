package panda.web.beans;

import org.modelmapper.ModelMapper;
import panda.domain.entities.Package;
import panda.domain.entities.User;
import panda.domain.models.binding.ReceiptBindingModel;
import panda.domain.models.service.ReceiptServiceModel;
import panda.domain.models.service.UserServiceModel;
import panda.domain.models.view.PackageViewModel;
import panda.domain.models.view.ReceiptViewModel;
import panda.service.PackageService;
import panda.service.ReceiptService;
import panda.service.UserService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class ReceiptsBean {

    private List<ReceiptViewModel> receipts;
    private PackageService packageService;
    private ReceiptService receiptService;
    private UserService userService;
    private ModelMapper modelMapper;

    public ReceiptsBean() {
    }

    @Inject
    public ReceiptsBean(ReceiptService receiptService, PackageService packageService, UserService userService, ModelMapper modelMapper) {
        this.receiptService = receiptService;
        this.userService = userService;
        this.packageService = packageService;
        this.modelMapper = modelMapper;
        this.initReceipts();
    }

    private void initReceipts() {
        String currentUsername = (String) ((HttpSession) FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getSession(true)).getAttribute("username");

        this.receipts = this.receiptService
                .findAllReceiptsByUsername(currentUsername)
                .stream()
                .map(r -> {
                    ReceiptViewModel receiptViewModel = this.modelMapper.map(r, ReceiptViewModel.class);
                    receiptViewModel.setRecipient(r.getRecipient());

                    return receiptViewModel;
                })
                .collect(Collectors.toList());
    }

    public List<ReceiptViewModel> getReceipts() {
        return this.receipts;
    }

    public void setReceipts(List<ReceiptViewModel> receipts) {
        this.receipts = receipts;
    }

    public void viewDetails (String id) throws IOException {
        FacesContext.getCurrentInstance()
                .getExternalContext()
                .redirect("/faces/view/receipts/details.xhtml");
    }

    public void createReceipt (PackageViewModel pack) throws IOException {

        ReceiptBindingModel receiptBindingModel = new ReceiptBindingModel();

        receiptBindingModel.setaPackage(modelMapper.map(pack, Package.class));

        String currentUsername = (String) ((HttpSession) FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getSession(true)).getAttribute("username");

        UserServiceModel userByUsername = userService.findUserByUsername(currentUsername);

        receiptBindingModel.setRecipient(modelMapper.map(userByUsername, User.class));
        receiptBindingModel.setFee(BigDecimal.valueOf(pack.getWeight()*2.67));
        receiptBindingModel.setIssuedOn(LocalDateTime.now());

        this.receiptService.receiptRegister(this.modelMapper.map(receiptBindingModel, ReceiptServiceModel.class));

        this.packageService.packageStatusChange(pack.getId());

        FacesContext.getCurrentInstance()
                .getExternalContext()
                .redirect("/faces/view/receipts.xhtml");
    }
}
