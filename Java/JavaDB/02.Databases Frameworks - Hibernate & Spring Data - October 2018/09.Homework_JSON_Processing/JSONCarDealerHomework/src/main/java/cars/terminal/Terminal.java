package cars.terminal;

import cars.controllers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Terminal implements CommandLineRunner {

    private final SupplierController supplierController;
    private final PartController partController;
    private final CarController carController;
    private final CustomerController customerController;
    private final SaleController saleController;

    @Autowired
    public Terminal(SupplierController supplierController, PartController partController, CarController carController, CustomerController customerController, SaleController saleController) {
        this.supplierController = supplierController;
        this.partController = partController;
        this.carController = carController;
        this.customerController = customerController;
        this.saleController = saleController;
    }

    @Override
    public void run(String... strings) throws Exception {
        // JSON Import
        this.supplierController.importSuppliersFromJson();
        this.partController.importPartsFromJson();
        this.carController.importCarsFromJson();
        this.customerController.importCustomersFromJson();
        this.saleController.importSales();

        // Query and Export Data - JSON Processing
        this.customerController.exportOrderedCustomersToJson();
        this.carController.exportToyotaCarsToJson();
        this.supplierController.exportLocalSuppliersToJson();
        this.carController.exportCarsWithPartsToJson();
        this.customerController.exportTotalSalesByCustomerToJson();
        this.saleController.exportSalesWithAppliedDiscountsToJson();
    }
}
