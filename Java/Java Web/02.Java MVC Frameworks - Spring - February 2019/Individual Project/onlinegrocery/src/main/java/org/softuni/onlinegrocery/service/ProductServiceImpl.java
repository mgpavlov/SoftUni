package org.softuni.onlinegrocery.service;

import org.modelmapper.ModelMapper;
import org.softuni.onlinegrocery.domain.entities.Product;
import org.softuni.onlinegrocery.domain.models.service.ProductServiceModel;
/*import org.softuni.onlinegrocery.error.ProductNameAlreadyExistsException;
import org.softuni.onlinegrocery.error.ProductNotFoundException;*/
import org.softuni.onlinegrocery.repository.ProductRepository;
import org.softuni.onlinegrocery.validation.ProductValidationService;
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

        if (productRepository.findByName(productServiceModel.getName())
                .orElse(null) != null) {
            /*throw new ProductNameAlreadyExistsException("Product already exists");*/
        }

        Product product = this.modelMapper.map(productServiceModel, Product.class);

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
                .map(p -> this.modelMapper.map(p, ProductServiceModel.class)).orElse(null);
                /*.orElseThrow(() -> new ProductNotFoundException("Product with the given id was not found!"));*/
    }

    @Override
    public List<ProductServiceModel> findProductsByPartOfName(String name) {
        return this.productRepository.findAll()
                .stream()
                .filter(p->p.getName().toLowerCase().contains(name.toLowerCase()))
                .map(p -> this.modelMapper.map(p, ProductServiceModel.class))
                .collect(Collectors.toList());
    }


    @Override
    public ProductServiceModel editProduct(String id, ProductServiceModel productServiceModel, boolean isNewImageUploaded, MultipartFile image) throws IOException {
        Product product = this.productRepository.findById(id).orElse(null);

        productServiceModel.setId(id);
        Product update = modelMapper.map(productServiceModel, Product.class);

        if (product == null || update == null){
            /*throw new ProductNotFoundException("Product with the given id was not found!");*/
        }

        if (isNewImageUploaded){
            update.setImageUrl(
                    this.cloudinaryService.uploadImage(image)
            );
        }else {
            update.setImageUrl(product.getImageUrl());
        }

        /*productServiceModel.setCategories(
                this.categoryService.findAllCategories()
                        .stream()
                        .filter(c -> productServiceModel.getCategories().contains(c.getId()))
                        .collect(Collectors.toList())
        );*/
        return this.modelMapper.map(this.productRepository.saveAndFlush(update), ProductServiceModel.class);
    }

    @Override
    public void deleteProduct(String id) {
        Product product = this.productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());
        product.setDeleted(true);

        this.productRepository.save(product);
    }

    @Override
    public List<ProductServiceModel> findAllByCategory(String category) {
        List<String> categories = this.categoryService.findAllCategories().stream().map(c -> c.getName()).collect(Collectors.toList());
        if (!categories.contains(category)){
            throw new IllegalArgumentException();
        }

        return this.productRepository.findAll()
                .stream()
                .filter(product -> product.getCategories().stream().anyMatch(categoryStream -> categoryStream.getName().equals(category)))
                .map(product -> this.modelMapper.map(product, ProductServiceModel.class))
                .collect(Collectors.toList());
    }

}
