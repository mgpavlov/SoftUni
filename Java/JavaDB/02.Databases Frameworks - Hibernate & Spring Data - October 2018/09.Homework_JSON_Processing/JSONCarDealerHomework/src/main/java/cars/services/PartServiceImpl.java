package cars.services;

import cars.dto.bindings.PartImportDto;
import cars.dto.utilities.SupplierIdDto;
import cars.entities.Part;
import cars.entities.Supplier;
import cars.repositories.PartRepository;
import cars.repositories.SupplierRepository;
import cars.utilities.MapperConverter;
import cars.utilities.RandomNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Transactional
@Service
public class PartServiceImpl implements PartService {

    private final PartRepository partRepository;
    private final SupplierRepository supplierRepository;
    private final MapperConverter mapperConverter;

    @Autowired
    public PartServiceImpl(PartRepository partRepository, SupplierRepository supplierRepository, MapperConverter mapperConverter) {
        this.partRepository = partRepository;
        this.supplierRepository = supplierRepository;
        this.mapperConverter = mapperConverter;
    }

    @Override
    public List<Part> findAll() {
        return this.partRepository.findAll();
    }

    @Override
    public Part findById(long id) {
        return this.partRepository.findById(id).orElse(null);
    }

    @Override
    public Part createOne(Part part) {
        return this.partRepository.save(part);
    }

    @Override
    public List<Part> createMany(Iterable<PartImportDto> partImportDtos) {
        List<Supplier> suppliers = this.supplierRepository.findAll();
        SupplierIdDto[] supplierIdDtos = this.mapperConverter.convert(suppliers, SupplierIdDto[].class);

        for (PartImportDto partImportDto : partImportDtos) {
            partImportDto.setSupplier(supplierIdDtos[RandomNumber.getRandomNumber(supplierIdDtos.length - 1)]);
        }

        Part[] parts = this.mapperConverter.convert(partImportDtos, Part[].class);
        for (Part part : parts) {
            this.partRepository.save(part);
        }
        return Arrays.asList(parts);
    }

    @Override
    public Part updateOne(Part part) {
        return this.partRepository.save(part);
    }

    @Override
    public List<Part> updateMany(Iterable<Part> parts) {
        for (Part part : parts) {
            this.partRepository.save(part);
        }
        return (List<Part>) parts;
    }

    @Override
    @Modifying
    public void deleteById(long id) {
        this.partRepository.deleteById(id);
    }

    @Override
    @Modifying
    public void deleteByPart(Part part) {
        this.partRepository.delete(part);
    }

}
