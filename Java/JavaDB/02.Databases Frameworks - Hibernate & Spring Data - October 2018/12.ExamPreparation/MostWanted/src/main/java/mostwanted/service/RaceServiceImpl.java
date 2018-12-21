package mostwanted.service;

import mostwanted.common.Constants;
import mostwanted.domain.dtos.races.EntryImportDto;
import mostwanted.domain.dtos.races.RaceImportDto;
import mostwanted.domain.dtos.races.RaceImportRootDto;
import mostwanted.domain.entities.District;
import mostwanted.domain.entities.Race;
import mostwanted.domain.entities.RaceEntry;
import mostwanted.repository.DistrictRepository;
import mostwanted.repository.RaceEntryRepository;
import mostwanted.repository.RaceRepository;
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
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class RaceServiceImpl implements RaceService {
    private final RaceRepository raceRepository;
    private final RaceEntryRepository raceEntryRepository;
    private final DistrictRepository districtRepository;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final FileUtil fileUtil;
    private final ValidationUtil validationUtil;


    @Autowired
    public RaceServiceImpl(RaceRepository raceRepository, RaceEntryRepository raceEntryRepository, DistrictRepository districtRepository, XmlParser xmlParser, ModelMapper modelMapper, FileUtil fileUtil, ValidationUtil validationUtil) {
        this.raceRepository = raceRepository;
        this.raceEntryRepository = raceEntryRepository;
        this.districtRepository = districtRepository;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.fileUtil = fileUtil;
        this.validationUtil = validationUtil;
    }

    @Override
    public Boolean racesAreImported() {
        return this.raceRepository.count() != 0;
    }

    @Override
    public String readRacesXmlFile() throws IOException {
        return this.fileUtil.readFile(Constants.RACE_ENTRIES_XML_FILE_PATH);
    }

    @Override
    public String importRaces() throws FileNotFoundException, UnsupportedEncodingException, JAXBException {
        StringBuilder importResult = new StringBuilder();

        RaceImportRootDto raceImportRootDto = this.xmlParser
                .parseXml(RaceImportRootDto.class, Constants.RACES_XML_FILE_PATH);

        for (RaceImportDto raceImportDto : raceImportRootDto.getRaceImportDtos()) {
            District districtEntity = this.districtRepository.findByName(raceImportDto.getDistrict()).orElse(null);

            if (!validationUtil.isValid(raceImportDto) || districtEntity == null){
                importResult.append(Constants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());

                continue;
            }

            Race raceEntity = this.modelMapper.map(raceImportDto, Race.class);
            raceEntity.setDistrict(districtEntity);
            Set<RaceEntry> raceEntries = new HashSet<>();
            for (EntryImportDto entryImportDto : raceImportDto.getEntryImportRootDto().getEntryImportDto()) {
                RaceEntry raceEntryEntity = this.raceEntryRepository.getById(Integer.valueOf(entryImportDto.getId())).orElse(null);
                if (raceEntryEntity == null){
                    importResult.append(Constants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                    continue;
                }
                raceEntryEntity.setRace(raceEntity);
                raceEntries.add(raceEntryEntity);
            }
            raceEntity.setEntries(raceEntries);
            this.raceRepository.saveAndFlush(raceEntity);
            /*this.raceEntryRepository.saveAll(raceEntries);*/

            importResult.append(String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE, raceEntity.getClass().getSimpleName(), raceEntity.getId())).append(System.lineSeparator());
        }
        return importResult.toString().trim();
    }
}
