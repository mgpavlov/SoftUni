package mostwanted.service;

import com.google.gson.Gson;
import mostwanted.common.Constants;
import mostwanted.domain.dtos.DistrictImportDto;
import mostwanted.domain.entities.District;
import mostwanted.domain.entities.Town;
import mostwanted.repository.DistrictRepository;
import mostwanted.repository.TownRepository;
import mostwanted.util.FileUtil;
import mostwanted.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class DistrictServiceImpl implements DistrictService {

    private final DistrictRepository districtRepository;
    private final TownRepository townRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final FileUtil fileUtil;
    private final ValidationUtil validationUtil;


    @Autowired
    public DistrictServiceImpl(DistrictRepository districtRepository, TownRepository townRepository, Gson gson, ModelMapper modelMapper, FileUtil fileUtil, ValidationUtil validationUtil) {
        this.districtRepository = districtRepository;
        this.townRepository = townRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.fileUtil = fileUtil;
        this.validationUtil = validationUtil;
    }

    @Override
    public Boolean districtsAreImported() {
        return this.districtRepository.count() != 0;
    }

    @Override
    public String readDistrictsJsonFile() throws IOException {
        String districtsFileContent = this.fileUtil.readFile(Constants.DISTRICTS_JSON_FILE_PATH);
        return districtsFileContent;
    }

    @Override
    public String importDistricts(String districtsFileContent) {
        StringBuilder importResult = new StringBuilder();
        DistrictImportDto[] districtImportDtos = this.gson.fromJson(districtsFileContent, DistrictImportDto[].class);


        for (DistrictImportDto districtImportDto : districtImportDtos) {
            District districtEntity = this.districtRepository.findByName(districtImportDto.getName()).orElse(null);

            if (districtEntity != null) {
                importResult.append(Constants.DUPLICATE_DATA_MESSAGE).append(System.lineSeparator());

                continue;
            }
            Town town = this.townRepository.findByName(districtImportDto.getTown()).orElse(null);
            if (!this.validationUtil.isValid(districtImportDto) || town == null) {
                importResult.append(Constants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());

                continue;
            }

            districtEntity = this.modelMapper.map(districtImportDto, District.class);
            districtEntity.setTown(town);

            this.districtRepository.saveAndFlush(districtEntity);

            importResult.append(String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE, districtEntity.getClass().getSimpleName(), districtEntity.getName())).append(System.lineSeparator());
        }
        return importResult.toString().trim();
    }
}
