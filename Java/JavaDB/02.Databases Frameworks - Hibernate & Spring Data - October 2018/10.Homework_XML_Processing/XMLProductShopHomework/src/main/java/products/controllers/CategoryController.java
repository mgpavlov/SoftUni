package products.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import products.dto.bindings.CategoriesImportXmlDto;
import products.dto.views.category.CategoriesByProductsCountXmlViewDto;
import products.dto.views.category.CategoryByProductsCountViewDto;
import products.dto.bindings.CategoryImportDto;
import products.serializers.Serializer;
import products.services.CategoryService;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Controller
public class CategoryController {
    private static final String CATEGORIES_JASON_INPUT_PATH = "/json/input/categories.json";
    private static final String CATEGORIES_XML_INPUT_PATH = "/xml/input/categories.xml";
    private static final String PRODUCTS_CATEGORIES_BY_PRODUCTS_COUNT_JSON_OUTPUT_PATH = "src/main/resources/json/output/categories-by-products.json";
    private static final String PRODUCTS_CATEGORIES_BY_PRODUCTS_COUNT_XML_OUTPUT_PATH = "src/main/resources/xml/output/categories-by-products.xml";

    private final CategoryService categoryService;
    private final Serializer jsonSerializer;
    private final Serializer xmlSerializer;

    @Autowired
    public CategoryController(CategoryService categoryService, @Qualifier("JsonSerializer") Serializer jsonSerializer, @Qualifier("XmlSerializer") Serializer xmlSerializer) {
        this.categoryService = categoryService;
        this.jsonSerializer = jsonSerializer;
        this.xmlSerializer = xmlSerializer;
    }

    public void importCategoriesFromJson() throws IOException, InstantiationException, IllegalAccessException {
        CategoryImportDto[] categoryImportDtos = this.jsonSerializer.deserialize(CategoryImportDto[].class, CATEGORIES_JASON_INPUT_PATH);
        this.categoryService.createMany(Arrays.asList(categoryImportDtos));
    }

    public void importCategoriesFromXml() {
        CategoriesImportXmlDto categoriesImportXmlDto = this.xmlSerializer.deserialize(CategoriesImportXmlDto.class, CATEGORIES_XML_INPUT_PATH);
        this.categoryService.createMany(categoriesImportXmlDto.getCategoryImportDtos());
    }

    public void exportCategoriesByProductsCountToJson() throws IOException {
        List<CategoryByProductsCountViewDto> categories = this.categoryService.getCategoriesByProductsCount();
        this.jsonSerializer.serialize(categories, PRODUCTS_CATEGORIES_BY_PRODUCTS_COUNT_JSON_OUTPUT_PATH);
    }

    public void exportCategoriesByProductsCountToXml() {
        List<CategoryByProductsCountViewDto> categories = this.categoryService.getCategoriesByProductsCount();
        CategoriesByProductsCountXmlViewDto categoriesByProductsCountXmlViewDto = new CategoriesByProductsCountXmlViewDto();
        categoriesByProductsCountXmlViewDto.setCategoryByProductsCountViewDtos(categories);
        this.xmlSerializer.serialize(categoriesByProductsCountXmlViewDto, PRODUCTS_CATEGORIES_BY_PRODUCTS_COUNT_XML_OUTPUT_PATH);
    }
}
