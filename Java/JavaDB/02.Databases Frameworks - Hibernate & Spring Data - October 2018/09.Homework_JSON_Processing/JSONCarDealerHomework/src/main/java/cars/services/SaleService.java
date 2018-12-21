package cars.services;

import cars.dto.views.sale.SaleInfoViewDto;
import cars.entities.Sale;

import java.util.List;

public interface SaleService {

    List<Sale> findAll();

    List<SaleInfoViewDto> getSalesWithAppliedDiscount();

    Sale findById(long id);

    Sale createOne(Sale sale);

    List<Sale> createMany();

    List<Sale> createMany(List<Sale> sales);

    Sale updateOne(Sale sale);

    List<Sale> updateMany(Iterable<Sale> sales);

    void deleteById(long id);

    void deleteBySale(Sale sale);
}
