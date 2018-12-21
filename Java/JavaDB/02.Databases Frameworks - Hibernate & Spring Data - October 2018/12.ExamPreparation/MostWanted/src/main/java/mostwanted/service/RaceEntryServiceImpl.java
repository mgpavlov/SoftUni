package mostwanted.service;

import mostwanted.common.Constants;
import mostwanted.domain.dtos.raceentries.RaceEntryImportDto;
import mostwanted.domain.dtos.raceentries.RaceEntryImportRootDto;
import mostwanted.domain.entities.Car;
import mostwanted.domain.entities.Race;
import mostwanted.domain.entities.RaceEntry;
import mostwanted.domain.entities.Racer;
import mostwanted.repository.CarRepository;
import mostwanted.repository.RaceEntryRepository;
import mostwanted.repository.RaceRepository;
import mostwanted.repository.RacerRepository;
import mostwanted.util.FileUtil;
import mostwanted.util.ValidationUtil;
import mostwanted.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Service
public class RaceEntryServiceImpl implements RaceEntryService {

    private final RaceEntryRepository raceEntryRepository;
    private final CarRepository carRepository;
    private final RacerRepository racerRepository;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final FileUtil fileUtil;
    private final ValidationUtil validationUtil;

    @Autowired
    public RaceEntryServiceImpl(RaceEntryRepository raceEntryRepository, CarRepository carRepository, RacerRepository racerRepository, XmlParser xmlParser, ModelMapper modelMapper, FileUtil fileUtil, ValidationUtil validationUtil) {
        this.raceEntryRepository = raceEntryRepository;
        this.carRepository = carRepository;
        this.racerRepository = racerRepository;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.fileUtil = fileUtil;
        this.validationUtil = validationUtil;
    }

    @Override
    public Boolean raceEntriesAreImported() {
        return this.raceEntryRepository.count() != 0;
    }

    @Override
    public String readRaceEntriesXmlFile() throws IOException {
        String raceEntriesFileContent = this.fileUtil.readFile(Constants.RACE_ENTRIES_XML_FILE_PATH);
        return raceEntriesFileContent;
    }

    @Override
    public String importRaceEntries() throws FileNotFoundException, UnsupportedEncodingException, JAXBException {
        StringBuilder importResult = new StringBuilder();

        RaceEntryImportRootDto raceEntryImportRootDto = this.xmlParser
                .parseXml(RaceEntryImportRootDto.class, Constants.RACE_ENTRIES_XML_FILE_PATH);

        for (RaceEntryImportDto raceEntryImportDto : raceEntryImportRootDto.getRaceEntryImportDtos()) {
            Car carEntity = this.carRepository.findById(Integer.parseInt(raceEntryImportDto.getCarId())).orElse(null);
            Racer racerEntity = this.racerRepository.findByName(raceEntryImportDto.getRacerName()).orElse(null);

            if (!validationUtil.isValid(raceEntryImportDto) || carEntity == null || racerEntity == null){
                importResult.append(Constants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());

                continue;
            }

            RaceEntry raceEntryEntity = this.modelMapper.map(raceEntryImportDto, RaceEntry.class);
            raceEntryEntity.setRacer(racerEntity);
            raceEntryEntity.setCar(carEntity);
            raceEntryEntity.setRace(null);

            raceEntryEntity = this.raceEntryRepository.saveAndFlush(raceEntryEntity);

            importResult.append(String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE, raceEntryEntity.getClass().getSimpleName(), raceEntryEntity.getId())).append(System.lineSeparator());
        }
        return importResult.toString().trim();
    }
}
