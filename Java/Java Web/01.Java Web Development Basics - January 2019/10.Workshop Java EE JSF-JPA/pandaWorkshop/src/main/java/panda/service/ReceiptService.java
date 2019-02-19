package panda.service;

import panda.domain.entities.Receipt;
import panda.domain.models.service.ReceiptServiceModel;

import java.util.List;

public interface ReceiptService {

    List<ReceiptServiceModel> findAllReceiptsByUsername(String userId);

    void receiptRegister (ReceiptServiceModel receiptServiceModel);

    ReceiptServiceModel getReceiptById(String id);
}
