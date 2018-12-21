package mostwanted.service;

import com.google.gson.Gson;
import mostwanted.common.Constants;
import mostwanted.domain.dtos.TownImportDto;
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

@Service
public class TownServiceImpl implements TownService {
    private final TownRepository townRepository;
    private final RacerRepository racerRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final FileUtil fileUtil;
    private final ValidationUtil validationUtil;

    @Autowired
    public TownServiceImpl(TownRepository townRepository, Gson gson, ModelMapper modelMapper, FileUtil fileUtil, ValidationUtil validationUtil, Constants constants, RacerRepository racerRepository) {
        this.townRepository = townRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.fileUtil = fileUtil;
        this.validationUtil = validationUtil;
        this.racerRepository = racerRepository;
    }

    @Override
    public Boolean townsAreImported() {
        return this.townRepository.count() != 0;
    }

    @Override
    public String readTownsJsonFile() throws IOException {
        String townsFileContent = this.fileUtil.readFile(Constants.TOWNS_JSON_FILE_PATH);
        return townsFileContent;

    }

    @Override
    public String importTowns(String townsFileContent) {
        StringBuilder importResult = new StringBuilder();
        TownImportDto[] townImportDtos = this.gson.fromJson(townsFileContent, TownImportDto[].class);

        for (TownImportDto townImportDto : townImportDtos) {
            Town townEntity = this.townRepository.findByName(townImportDto.getName()).orElse(null);

            if (townEntity != null) {
                importResult.append(Constants.DUPLICATE_DATA_MESSAGE).append(System.lineSeparator());

                continue;
            }
            if (!this.validationUtil.isValid(townImportDto)) {
                importResult.append(Constants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());

                continue;
            }

            townEntity = this.modelMapper.map(townImportDto, Town.class);

            this.townRepository.saveAndFlush(townEntity);

            importResult.append(String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE, townEntity.getClass().getSimpleName(), townEntity.getName())).append(System.lineSeparator());
        }
        return importResult.toString().trim();
    }

    @Override
    public String exportRacingTowns() {
        StringBuilder sb = new StringBuilder();
/*        List<Town> towns = this.racerRepository.findAll()
                .stream()
                .filter(r -> r.getHomeTown() != null)
                .map(Racer::getHomeTown)
                .collect(Collectors.toList());

        Map<String, Integer> townsRacers = new TreeMap<>();
        for (Town town : towns) {
            townsRacers.putIfAbsent(town.getName(), 0);
            townsRacers.put(town.getName(), townsRacers.get(town.getName())+1);
        }

        List<String> result = townsRacers.entrySet().stream()
                .sorted((t1,t2)->Integer.compare(t2.getValue(), t1.getValue()))
                .map(tr-> String.format("Name: %s\nRacers: %d", tr.getKey(), tr.getValue()))
                .collect(Collectors.toList());

        return String.join(System.lineSeparator()+System.lineSeparator(), result);*/

        List<Town> towns = this.townRepository.racersTowns();
        for (Town town : towns) {
            sb.append(String.format("Name: %s", town.getName()));
            sb.append(System.lineSeparator());
            sb.append(String.format("Racers: %d", town.getRacers().size()));
            sb.append(System.lineSeparator()).append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}
