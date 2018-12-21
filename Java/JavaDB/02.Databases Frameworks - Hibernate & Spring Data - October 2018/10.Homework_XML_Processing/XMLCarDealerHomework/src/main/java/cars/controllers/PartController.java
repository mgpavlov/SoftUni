package cars.controllers;

import cars.dto.bindings.PartImportDto;
import cars.dto.bindings.PartsImportXmlDto;
import cars.serializers.Serializer;
import cars.services.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import java.util.Arrays;


@Controller
public class PartController {
    private static final String PARTS_JASON_INPUT_PATH = "/json/input/parts.json";
    private static final String PARTS_XML_INPUT_PATH = "/xml/input/parts.xml";

    private final PartService partService;
    private final Serializer jsonSerializer;
    private final Serializer xmlSerializer;

    @Autowired
    public PartController(PartService partService, @Qualifier("json") Serializer jsonSerializer, @Qualifier("xml") Serializer xmlSerializer) {
        this.partService = partService;
        this.jsonSerializer = jsonSerializer;
        this.xmlSerializer = xmlSerializer;
    }

    public void importPartsFromJson() {
        PartImportDto[] partImportDtos = this.jsonSerializer.deserialize(PartImportDto[].class, PARTS_JASON_INPUT_PATH);
        this.partService.createMany(Arrays.asList(partImportDtos));
    }

    public void importPartsFromXml() {
        PartsImportXmlDto partsImportXmlDto = this.xmlSerializer.deserialize(PartsImportXmlDto.class, PARTS_XML_INPUT_PATH);
        this.partService.createMany(partsImportXmlDto.getPartImportDtos());
    }
}
