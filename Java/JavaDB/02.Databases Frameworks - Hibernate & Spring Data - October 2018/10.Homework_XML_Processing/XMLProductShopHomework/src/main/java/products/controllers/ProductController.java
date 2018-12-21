package products.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import products.dto.bindings.ProductImportDto;
import products.dto.bindings.ProductsImportXmlDto;
import products.dto.views.product.ProductViewDto;
import products.dto.views.product.ProductsViewXmlDto;
import products.serializers.Serializer;
import products.services.ProductService;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Controller
public class ProductController {
    private static final String PRODUCTS_JASON_INPUT_PATH = "/json/input/products.json";
    private static final String PRODUCTS_XML_INPUT_PATH = "/xml/input/products.xml";
    private static final String PRODUCTS_IN_RANGE_JSON_OUTPUT_PATH = "src/main/resources/json/output/productsInRange.json";
    private static final String PRODUCTS_IN_RANGE_XML_OUTPUT_PATH = "src/main/resources/xml/output/productsInRange.xml";
    private static final BigDecimal MIN_PRODUCT_PRICE = new BigDecimal(500);
    private static final BigDecimal MAX_PRODUCT_PRICE = new BigDecimal(1000);

    private final ProductService productService;
    private final Serializer jsonSerializer;
    private final Serializer xmlSerializer;

    @Autowired
    public ProductController(ProductService productService, @Qualifier("JsonSerializer") Serializer jsonSerializer, @Qualifier("XmlSerializer") Serializer xmlSerializer) {
        this.productService = productService;
        this.jsonSerializer = jsonSerializer;
        this.xmlSerializer = xmlSerializer;
    }

    public void importProductsFromJson() throws IOException, InstantiationException, IllegalAccessException {
        ProductImportDto[] productImportDtos = this.jsonSerializer.deserialize(ProductImportDto[].class, PRODUCTS_JASON_INPUT_PATH);
        this.productService.createMany(Arrays.asList(productImportDtos));
    }

    public void importProductsFromXml() throws InstantiationException, IllegalAccessException {
        ProductsImportXmlDto productsImportXmlDto = this.xmlSerializer.deserialize(ProductsImportXmlDto.class, PRODUCTS_XML_INPUT_PATH);
        this.productService.createMany(productsImportXmlDto.getProductImportDtos());

    }

    public void exportProductsInRangeToJson() throws InstantiationException, IllegalAccessException, IOException {
        List<ProductViewDto> productViewDtos = this.productService.getProductsInRange(MIN_PRODUCT_PRICE, MAX_PRODUCT_PRICE);
        this.jsonSerializer.serialize(productViewDtos, PRODUCTS_IN_RANGE_JSON_OUTPUT_PATH);
    }

    public void exportProductsInRangeToXml() throws IllegalAccessException, InstantiationException {
        List<ProductViewDto> productViewDtos = this.productService.getProductsInRange(MIN_PRODUCT_PRICE, MAX_PRODUCT_PRICE);
        ProductsViewXmlDto productsViewXmlDto = new ProductsViewXmlDto();
        productsViewXmlDto.setProductViewDtos(productViewDtos);
        this.xmlSerializer.serialize(productsViewXmlDto, PRODUCTS_IN_RANGE_XML_OUTPUT_PATH);
    }
}
