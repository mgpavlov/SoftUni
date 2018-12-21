package cars.services;

import cars.dto.bindings.CustomerImportDto;
import cars.dto.views.customer.CustomerTotalSalesViewDto;
import cars.dto.views.customer.OrderedCustomersViewDto;
import cars.entities.Customer;
import cars.repositories.CustomerRepository;
import cars.utilities.MapperConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Transactional
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final MapperConverter mapperConverter;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, MapperConverter mapperConverter) {
        this.customerRepository = customerRepository;
        this.mapperConverter = mapperConverter;
    }

    @Override
    public List<Customer> findAll() {
        return this.customerRepository.findAll();
    }

    @Override
    public Customer findById(long id) {
        return this.customerRepository.findById(id).orElse(null);
    }

    @Override
    public Customer createOne(Customer customer) {
        return this.customerRepository.save(customer);
    }

    @Override
    public List<Customer> createMany(List<CustomerImportDto> customerImportDtos) {
        Customer[] customers = this.mapperConverter.convert(customerImportDtos, Customer[].class);
        for (Customer customer : customers) {
            this.customerRepository.save(customer);
        }
        return Arrays.asList(customers);
    }

    @Override
    public List<OrderedCustomersViewDto> orderedCustomers() {
        List<Customer> customers = this.customerRepository.getCustomersByOrderByBirthDateAscYoungDriverAsc();
        OrderedCustomersViewDto[] orderedCustomersViewDtos = this.mapperConverter.convert(customers, OrderedCustomersViewDto[].class);
        return Arrays.asList(orderedCustomersViewDtos);
    }

    @Override
    public Customer updateOne(Customer customer) {
        return this.customerRepository.save(customer);
    }

    @Override
    public List<Customer> updateMany(Iterable<Customer> customers) {
        for (Customer customer : customers) {
            this.customerRepository.save(customer);
        }
        return (List<Customer>) customers;
    }

    @Override
    @Modifying
    public void deleteById(long id) {
        this.customerRepository.deleteById(id);
    }

    @Override
    @Modifying
    public void deleteByCustomer(Customer customer) {
        this.customerRepository.delete(customer);
    }

    @Override
    public List<CustomerTotalSalesViewDto> getTotalSalesByCustomer() {
        List<Customer> customers = this.customerRepository.getCustomersByBoughtCars();
        CustomerTotalSalesViewDto[] customerTotalSalesViewDtos = this.mapperConverter.convert(customers, CustomerTotalSalesViewDto[].class);
        List<CustomerTotalSalesViewDto> customerTotalSalesViewDtoList = Arrays.asList(customerTotalSalesViewDtos);
        customerTotalSalesViewDtoList.sort((c1, c2) -> {
            int comp = Double.compare(c2.getSpentMoney(), c1.getSpentMoney());

            if (comp != 0) {
                return comp;
            }
            return c2.getBoughtCars() - c1.getBoughtCars();
        });
        return customerTotalSalesViewDtoList;
    }

}
