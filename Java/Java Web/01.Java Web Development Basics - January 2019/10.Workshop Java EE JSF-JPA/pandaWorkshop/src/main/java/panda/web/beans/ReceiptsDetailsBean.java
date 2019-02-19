package panda.web.beans;

import org.modelmapper.ModelMapper;
import panda.domain.models.service.PackageServiceModel;
import panda.domain.models.service.ReceiptServiceModel;
import panda.domain.models.view.PackageViewModel;
import panda.service.PackageService;
import panda.service.ReceiptService;
import panda.service.UserService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class ReceiptsDetailsBean {

    private ReceiptService receiptService;

    public ReceiptsDetailsBean() {
    }

    @Inject
    public ReceiptsDetailsBean(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    public ReceiptServiceModel getReceipt(String id) {
        return this.receiptService.getReceiptById(id);
    }
}
