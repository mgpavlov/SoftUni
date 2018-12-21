package mostwanted.service;

import com.google.gson.Gson;
import mostwanted.common.Constants;
import mostwanted.domain.dtos.RacerImportDto;
import mostwanted.domain.entities.Car;
import mostwanted.domain.entities.Racer;
import mostwanted.domain.entities.Town;
import mostwanted.repository.RacerRepository;
import mostwanted.repository.TownRepository;
import mostwanted.util.FileUtil;
import mostwanted.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RacerServiceImpl implements RacerService {

    private final RacerRepository racerRepository;
    private final TownRepository townRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final FileUtil fileUtil;
    private final ValidationUtil validationUtil;


    @Autowired
    public RacerServiceImpl(RacerRepository racerRepository, TownRepository townRepository, Gson gson, ModelMapper modelMapper, FileUtil fileUtil, ValidationUtil validationUtil) {
        this.racerRepository = racerRepository;
        this.townRepository = townRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.fileUtil = fileUtil;
        this.validationUtil = validationUtil;
    }

    @Override
    public Boolean racersAreImported() {
        return this.racerRepository.count() != 0;
    }

    @Override
    public String readRacersJsonFile() throws IOException {
        String racersFileContent = this.fileUtil.readFile(Constants.RACERS_JSON_FILE_PATH);
        return racersFileContent;
    }

    @Override
    public String importRacers(String racersFileContent) {
        StringBuilder importResult = new StringBuilder();
        RacerImportDto[] racerImportDtos = this.gson.fromJson(racersFileContent, RacerImportDto[].class);


        for (RacerImportDto racerImportDto : racerImportDtos) {
            Racer racerEntity = this.racerRepository.findByName(racerImportDto.getName()).orElse(null);

            if (racerEntity != null) {
                importResult.append(Constants.DUPLICATE_DATA_MESSAGE).append(System.lineSeparator());

                continue;
            }
            Town town = this.townRepository.findByName(racerImportDto.getHomeTown()).orElse(null);
            if (!this.validationUtil.isValid(racerImportDto) || town == null) {
                importResult.append(Constants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());

                continue;
            }

            racerEntity = this.modelMapper.map(racerImportDto, Racer.class);
            racerEntity.setHomeTown(town);

            this.racerRepository.saveAndFlush(racerEntity);

            importResult.append(String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE, racerEntity.getClass().getSimpleName(), racerEntity.getName())).append(System.lineSeparator());
        }
        return importResult.toString().trim();
    }

    @Override
    public String exportRacingCars() {
        StringBuilder result = new StringBuilder();

        List<Racer> racers = this.racerRepository.findAllByCarsIsNotNull().stream().filter(c->c.getCars().size()>0).sorted((r1,r2)->{
            if (r2.getCars().size() == r1.getCars().size()){
                return r1.getName().compareTo(r2.getName());
            }
            return Integer.compare(r2.getCars().size(), r1.getCars().size());
        }).collect(Collectors.toList());

        List<Racer> racers1 = this.racerRepository.exportRacingCars();

        for (Racer racer : racers) {
            result.append(String.format("Name: %s", racer.getName())).append(System.lineSeparator());
            if (racer.getAge() != null){
                result.append(String.format("Age: %d", racer.getAge())).append(System.lineSeparator());
            }
            result.append("Cars:").append(System.lineSeparator());
            for (Car car : racer.getCars()) {
                result.append(String.format("\t%s %s %d", car.getBrand(), car.getModel(), car.getYearOfProduction())).append(System.lineSeparator());
            }
            result.append(System.lineSeparator());
        }
        return result.toString().trim();
    }
}
