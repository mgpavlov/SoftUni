package cars.services;

import cars.dto.bindings.SupplierImportDto;
import cars.dto.views.supplier.LocalSupplierViewDto;
import cars.entities.Supplier;
import cars.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cars.utilities.MapperConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Transactional
@Service
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;
    private final MapperConverter mapperConverter;

    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepository, MapperConverter mapperConverter) {
        this.supplierRepository = supplierRepository;
        this.mapperConverter = mapperConverter;
    }

    @Override
    public List<Supplier> findAll() {
        return this.supplierRepository.findAll();
    }

    @Override
    public Supplier findById(long id) {
        return this.supplierRepository.findById(id).orElse(null);
    }

    @Override
    public List<LocalSupplierViewDto> getLocalSuppliers() {
        LocalSupplierViewDto[] localSupplierViewDtos = this.mapperConverter.convert(this.supplierRepository.getSuppliersByImporterIsFalse(), LocalSupplierViewDto[].class);
        return Arrays.asList(localSupplierViewDtos);
    }

    @Override
    public Supplier createOne(Supplier supplier) {
        return this.supplierRepository.save(supplier);
    }

    @Override
    public List<Supplier> createMany(List<SupplierImportDto> supplierImportDtos) {
        Supplier[] suppliers = this.mapperConverter.convert(supplierImportDtos, Supplier[].class);
        if (suppliers == null){
            return new ArrayList<>();
        }
        for (Supplier supplier : suppliers) {
            this.supplierRepository.save(supplier);
        }
        return Arrays.asList(suppliers);
    }

    @Override
    public Supplier updateOne(Supplier supplier) {
        return this.supplierRepository.save(supplier);
    }

    @Override
    public List<Supplier> updateMany(Iterable<Supplier> suppliers) {
        if (suppliers == null){
            return new ArrayList<>();
        }
        for (Supplier supplier : suppliers) {
            this.supplierRepository.save(supplier);
        }
        return (List<Supplier>) suppliers;
    }

    @Override
    @Modifying
    public void deleteById(long id) {
        this.supplierRepository.deleteById(id);
    }

    @Override
    @Modifying
    public void deleteBySupplier(Supplier supplier) {
        this.supplierRepository.delete(supplier);
    }

}
