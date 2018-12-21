package cars.services;

import cars.dto.bindings.SaleImportDto;
import cars.dto.utilities.CarIdDto;
import cars.dto.utilities.CustomerIdYoungDriverDto;
import cars.dto.views.sale.SaleInfoViewDto;
import cars.entities.Car;
import cars.entities.Customer;
import cars.entities.Sale;
import cars.repositories.CarRepository;
import cars.repositories.CustomerRepository;
import cars.repositories.SaleRepository;
import cars.utilities.RandomNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cars.utilities.MapperConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class SaleServiceImpl implements SaleService {
    private static final double[] DISCOUNTS = {0, 0.05, 0.1, 0.15, 0.2, 0.3, 0.4, 0.5};

    private final SaleRepository saleRepository;
    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;
    private final MapperConverter mapperConverter;

    @Autowired
    public SaleServiceImpl(SaleRepository saleRepository, CarRepository carRepository, CustomerRepository customerRepository, MapperConverter mapperConverter) {
        this.saleRepository = saleRepository;
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
        this.mapperConverter = mapperConverter;
    }

    @Override
    public List<Sale> findAll() {
        return this.saleRepository.findAll();
    }

    @Override
    public List<SaleInfoViewDto> getSalesWithAppliedDiscount() {
        List<Sale> sales = this.saleRepository.findAll();
        SaleInfoViewDto[] saleInfoViewDtos = this.mapperConverter.convert(sales, SaleInfoViewDto[].class);
        return Arrays.asList(saleInfoViewDtos);
    }

    @Override
    public Sale findById(long id) {
        return this.saleRepository.findById(id).orElse(null);
    }

    @Override
    public Sale createOne(Sale sale) {
        return this.saleRepository.save(sale);
    }

    @Override
    public List<Sale> createMany() {
        List<Car> cars = this.carRepository.findAll();
        List<Customer> customers = this.customerRepository.findAll();

        CarIdDto[] carIdDtos = this.mapperConverter.convert(cars, CarIdDto[].class);
        CustomerIdYoungDriverDto[] customerIdYoungDriverDtos = this.mapperConverter.convert(customers, CustomerIdYoungDriverDto[].class);

        List<SaleImportDto> saleImportDtos = new ArrayList<>();
        for (int i = 0; i < RandomNumber.getRandomNumber(50, 200); i++) {
            SaleImportDto saleImportDto = new SaleImportDto();
            while (true) {
                CarIdDto carIdDto = carIdDtos[RandomNumber.getRandomNumber(carIdDtos.length - 1)];
                if (!saleImportDtos.stream().map(x -> x.getCar().getId()).collect(Collectors.toList()).contains(carIdDto.getId())) {
                    saleImportDto.setCar(carIdDto);
                    break;
                }
            }
            saleImportDto.setCustomer(customerIdYoungDriverDtos[RandomNumber.getRandomNumber(customerIdYoungDriverDtos.length - 1)]);
            saleImportDto.setDiscount(DISCOUNTS[RandomNumber.getRandomNumber(DISCOUNTS.length - 1)]);
            if (saleImportDto.getCustomer().isYoungDriver()) {
                saleImportDto.setDiscount(saleImportDto.getDiscount() + 0.05);
            }
            saleImportDtos.add(saleImportDto);
        }

        Sale[] sales = this.mapperConverter.convert(saleImportDtos, Sale[].class);
        for (Sale sale : sales) {
            this.saleRepository.save(sale);
        }
        return Arrays.asList(sales);
    }

    @Override
    public List<Sale> createMany(List<Sale> sales) {
        for (Sale sale : sales) {
            this.saleRepository.save(sale);
        }
        return sales;
    }

    @Override
    public Sale updateOne(Sale sale) {
        return this.saleRepository.save(sale);
    }

    @Override
    public List<Sale> updateMany(Iterable<Sale> sales) {
        for (Sale sale : sales) {
            this.saleRepository.save(sale);
        }
        return (List<Sale>) sales;
    }

    @Override
    @Modifying
    public void deleteById(long id) {
        this.saleRepository.deleteById(id);
    }

    @Override
    @Modifying
    public void deleteBySale(Sale sale) {
        this.saleRepository.delete(sale);
    }

}
