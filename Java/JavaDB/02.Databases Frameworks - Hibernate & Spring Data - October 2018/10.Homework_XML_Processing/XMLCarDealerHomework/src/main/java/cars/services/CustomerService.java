package cars.services;

import cars.dto.bindings.CustomerImportDto;
import cars.dto.views.customer.CustomerTotalSalesViewDto;
import cars.dto.views.customer.OrderedCustomersViewDto;
import cars.entities.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> findAll();

    Customer findById(long id);

    Customer createOne(Customer customer);

    List<Customer> createMany(List<CustomerImportDto> customerImportDtos);

    List<OrderedCustomersViewDto> orderedCustomers();

    Customer updateOne(Customer customer);

    List<Customer> updateMany(Iterable<Customer> customers);

    void deleteById(long id);

    void deleteByCustomer(Customer customer);

    List<CustomerTotalSalesViewDto> getTotalSalesByCustomer();
}
