package org.softuni.onlinegrocery.integration.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.softuni.onlinegrocery.domain.entities.Product;
import org.softuni.onlinegrocery.domain.models.service.CategoryServiceModel;
import org.softuni.onlinegrocery.domain.models.service.ProductServiceModel;
import org.softuni.onlinegrocery.repository.ProductRepository;
import org.softuni.onlinegrocery.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductServiceTests {
    @Autowired
    private ProductService service;

    @MockBean
    private ProductRepository mockProductRepository;

    @Test
    public void createProduct_whenValid_productCreated() throws IOException {
        ProductServiceModel product = new ProductServiceModel();
        product.setCategories(List.of(new CategoryServiceModel()));
        when(mockProductRepository.save(any()))
                .thenReturn(new Product());

        service.createProduct(product, null);
        verify(mockProductRepository)
              .save(any());
    }

    @Test(expected = IllegalArgumentException.class)
    public void createProduct_whenNull_throw() throws IOException {
        service.createProduct(null, null);
        verify(mockProductRepository)
                .save(any());
    }
}
