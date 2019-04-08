package org.softuni.onlinemarket.service;

import org.modelmapper.ModelMapper;
import org.softuni.onlinemarket.domain.entities.Category;
import org.softuni.onlinemarket.domain.entities.Product;
import org.softuni.onlinemarket.domain.models.service.ProductServiceModel;
import org.softuni.onlinemarket.repository.ProductRepository;
import org.softuni.onlinemarket.validation.ProductValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final CloudinaryService cloudinaryService;
    private final ProductValidationService productValidation;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductServiceImpl(
            ProductRepository productRepository,
            CategoryService categoryService,
            CloudinaryService cloudinaryService, ProductValidationService productValidation,
            ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.cloudinaryService = cloudinaryService;
        this.productValidation = productValidation;
        this.modelMapper = modelMapper;
    }

    @Override
    public ProductServiceModel createProduct(ProductServiceModel productServiceModel, MultipartFile image) throws IOException {
        /*if(!productValidation.isValid(productServiceModel)) {
            throw new IllegalArgumentException();
        }*/
        productServiceModel.setCategories(
                this.categoryService.findAllCategories()
                        .stream()
                        .filter(c -> productServiceModel.getCategories().contains(c.getId()))
                        .collect(Collectors.toList())
        );

        Product product = this.modelMapper.map(productServiceModel, Product.class);

        product.setCategories(
                productServiceModel.getCategories()
                        .stream()
                        .map(c -> this.modelMapper.map(c, Category.class))
                        .collect(Collectors.toList())
        );
        product.setImageUrl(
                this.cloudinaryService.uploadImage(image)
        );
        product = this.productRepository.saveAndFlush(product);
        if (product == null){
            return null;
        }
        return this.modelMapper.map(product, ProductServiceModel.class);
    }

    @Override
    public List<ProductServiceModel> findAllProducts() {
        return this.productRepository.findAll()
                .stream()
                .map(p -> this.modelMapper.map(p, ProductServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductServiceModel findProductById(String id) {
        return this.productRepository.findById(id)
                .map(p -> this.modelMapper.map(p, ProductServiceModel.class))
                .orElseThrow(() -> new IllegalArgumentException());
    }

    @Override
    public ProductServiceModel editProduct(String id, ProductServiceModel productServiceModel) {
        Product product = this.productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException());

        productServiceModel.setCategories(
                this.categoryService.findAllCategories()
                        .stream()
                        .filter(c -> productServiceModel.getCategories().contains(c.getId()))
                        .collect(Collectors.toList())
        );

        product.setName(productServiceModel.getName());
        product.setDescription(productServiceModel.getDescription());
        product.setPrice(productServiceModel.getPrice());
        product.setCategories(
                productServiceModel.getCategories()
                        .stream()
                        .map(c -> this.modelMapper.map(c, Category.class))
                        .collect(Collectors.toList())
        );

        return this.modelMapper.map(this.productRepository.saveAndFlush(product), ProductServiceModel.class);
    }

    @Override
    public void deleteProduct(String id) {
        Product product = this.productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());

        this.productRepository.delete(product);
    }

    @Override
    public List<ProductServiceModel> findAllByCategory(String category) {
        //TODO: OPTIMIZE FILTERING

        return this.productRepository.findAll()
                .stream()
                .filter(product -> product.getCategories().stream().anyMatch(categoryStream -> categoryStream.getName().equals(category)))
                .map(product -> this.modelMapper.map(product, ProductServiceModel.class))
                .collect(Collectors.toList());
    }

}
