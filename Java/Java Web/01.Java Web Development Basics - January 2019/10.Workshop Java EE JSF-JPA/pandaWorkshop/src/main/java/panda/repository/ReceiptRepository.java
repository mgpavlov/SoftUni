package panda.repository;

import panda.domain.entities.Package;
import panda.domain.entities.Receipt;
import panda.domain.entities.Status;
import panda.domain.entities.User;

import java.util.List;

public interface ReceiptRepository extends GenericRepository<Receipt, String> {

    List<Receipt> findAllReceiptsByRecipientUsername(String username);
}
