package cars.services;

import cars.dto.bindings.PartImportDto;
import cars.entities.Part;

import java.util.List;

public interface PartService {

    List<Part> findAll();

    Part findById(long id);

    Part createOne(Part part);

    List<Part> createMany(Iterable<PartImportDto> partImportDtos);

    Part updateOne(Part part);

    List<Part> updateMany(Iterable<Part> parts);

    void deleteById(long id);

    void deleteByPart(Part part);
}
