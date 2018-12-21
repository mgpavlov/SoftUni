package alararestaurant.service;

import alararestaurant.common.Constants;
import alararestaurant.domain.dtos.ItemImportDto;
import alararestaurant.domain.entities.Category;
import alararestaurant.domain.entities.Item;
import alararestaurant.repository.CategoryRepository;
import alararestaurant.repository.ItemRepository;
import alararestaurant.util.FileUtil;
import alararestaurant.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final FileUtil fileUtil;
    private final ValidationUtil validationUtil;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository, CategoryRepository categoryRepository, Gson gson, ModelMapper modelMapper, FileUtil fileUtil, ValidationUtil validationUtil) {
        this.itemRepository = itemRepository;
        this.categoryRepository = categoryRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.fileUtil = fileUtil;
        this.validationUtil = validationUtil;
    }

    @Override
    public Boolean itemsAreImported() {
        return this.itemRepository.count() > 0;
    }

    @Override
    public String readItemsJsonFile() throws IOException {
        return this.fileUtil.readFile(Constants.ITEMS_JSON_FILE_PATH);
    }

    @Override
    public String importItems(String items) {
        StringBuilder importResult = new StringBuilder();
        ItemImportDto[] itemImportDtos = this.gson.fromJson(items, ItemImportDto[].class);

        for (ItemImportDto itemImportDto : itemImportDtos) {
            Item itemEntity = this.itemRepository.findByName(itemImportDto.getName()).orElse(null);
            Category categoryEntity = this.categoryRepository.findByName(itemImportDto.getCategory()).orElse(null);

            if (!this.validationUtil.isValid(itemImportDto)) {
                importResult.append(Constants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());

                continue;
            }

            if (itemEntity != null){
                importResult.append(Constants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());

                continue;
            }

            itemEntity = this.modelMapper.map(itemImportDto, Item.class);
            if (categoryEntity == null) {
                categoryEntity = new Category();

                categoryEntity.setName(itemImportDto.getCategory());
                categoryEntity.getItems().add(itemEntity);

                if (!this.validationUtil.isValid(categoryEntity)) {
                    importResult.append(Constants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                    continue;
                }

            }
            categoryEntity.getItems().add(itemEntity);
            this.categoryRepository.saveAndFlush(categoryEntity);

            itemEntity.setCategory(categoryEntity);

            this.itemRepository.saveAndFlush(itemEntity);

            importResult.append(String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE, itemEntity.getName())).append(System.lineSeparator());
        }
        return importResult.toString().trim();
    }
}
