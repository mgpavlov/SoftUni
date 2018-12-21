package products.terminal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import products.controllers.CategoryController;
import products.controllers.ProductController;
import products.controllers.UserController;

@Component
public class Terminal implements CommandLineRunner {

    private final UserController userController;
    private final CategoryController categoryController;
    private final ProductController productController;

    @Autowired
    public Terminal(UserController userController, CategoryController categoryController, ProductController productController) {
        this.userController = userController;
        this.categoryController = categoryController;
        this.productController = productController;
    }

    @Override
    public void run(String... strings) throws Exception {
        // JSON Import
        this.userController.importUsersFromJson();
        this.categoryController.importCategoriesFromJson();
        this.productController.importProductsFromJson();

        // Query and Export Data - JSON Processing
        this.productController.exportProductsInRangeToJson();
        this.userController.exportUsersWithSoldProductsToJson();
        this.categoryController.exportCategoriesByProductsCountToJson();
        this.userController.exportUsersAndProductsToJson();
        
    }
}
