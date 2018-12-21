package cars.services;

import cars.dto.bindings.SupplierImportDto;
import cars.dto.views.supplier.LocalSupplierViewDto;
import cars.entities.Supplier;

import java.util.List;

public interface SupplierService {

    List<Supplier> findAll();

    Supplier findById(long id);

    List<LocalSupplierViewDto> getLocalSuppliers();

    Supplier createOne(Supplier supplier);

    List<Supplier> createMany(List<SupplierImportDto> supplierImportDtos);

    Supplier updateOne(Supplier supplier);

    List<Supplier> updateMany(Iterable<Supplier> suppliers);

    void deleteById(long id);

    void deleteBySupplier(Supplier supplier);
}
