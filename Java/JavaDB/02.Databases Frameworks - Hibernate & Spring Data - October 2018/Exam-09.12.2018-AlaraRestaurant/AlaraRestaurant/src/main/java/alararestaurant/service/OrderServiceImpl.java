package alararestaurant.service;

import alararestaurant.common.Constants;
import alararestaurant.domain.dtos.orders.OrderImportDto;
import alararestaurant.domain.dtos.orders.OrderImportRootDto;
import alararestaurant.domain.dtos.orders.OrderItemImportDto;
import alararestaurant.domain.entities.Employee;
import alararestaurant.domain.entities.Item;
import alararestaurant.domain.entities.Order;
import alararestaurant.domain.entities.OrderItem;
import alararestaurant.repository.EmployeeRepository;
import alararestaurant.repository.ItemRepository;
import alararestaurant.repository.OrderItemRepository;
import alararestaurant.repository.OrderRepository;
import alararestaurant.util.FileUtil;
import alararestaurant.util.ValidationUtil;
import alararestaurant.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final OrderItemRepository orderItemRepository;
    private final EmployeeRepository employeeRepository;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final FileUtil fileUtil;
    private final ValidationUtil validationUtil;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, ItemRepository itemRepository, OrderItemRepository orderItemRepository, EmployeeRepository employeeRepository, XmlParser xmlParser, ModelMapper modelMapper, FileUtil fileUtil, ValidationUtil validationUtil) {
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
        this.orderItemRepository = orderItemRepository;
        this.employeeRepository = employeeRepository;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.fileUtil = fileUtil;
        this.validationUtil = validationUtil;
    }

    @Override
    public Boolean ordersAreImported() {
        return this.orderRepository.count() > 0;
    }

    @Override
    public String readOrdersXmlFile() throws IOException {
        return this.fileUtil.readFile(Constants.ORDERS_ENTRIES_XML_FILE_PATH);
    }

    @Override
    public String importOrders() throws JAXBException {

        StringBuilder importResult = new StringBuilder();

        OrderImportRootDto orderImportRootDto = this.xmlParser
                .parseXml(OrderImportRootDto.class, Constants.ORDERS_ENTRIES_XML_FILE_PATH);
        List<Order> orders = new ArrayList<>();

        for (OrderImportDto orderImportDto : orderImportRootDto.getOrderImportDtos()) {
            Employee employeeEntity = this.employeeRepository.findByName(orderImportDto.getEmployee()).orElse(null);

            if (!validationUtil.isValid(orderImportDto) || employeeEntity == null) {
                importResult.append(Constants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }

            Order orderEntity = this.modelMapper.map(orderImportDto, Order.class);

            orderEntity.setEmployee(employeeEntity);

            orderEntity.setCustomer(orderImportDto.getCustomer());

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            String date = orderImportDto.getDateTime().replaceAll("/", "-");
            LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
            orderEntity.setDateTime(dateTime);

            this.orderRepository.saveAndFlush(orderEntity);

            employeeEntity.getOrders().add(orderEntity);

            for (OrderItemImportDto orderItemImportDto : orderImportDto.getOrderItemImportRootDto().getOrderItemImportDtos()) {
                Item item = this.itemRepository.findByName(orderItemImportDto.getName()).orElse(null);
                if (item == null) {
                    importResult.append(Constants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                    continue;
                }

                OrderItem orderItemEntity = new OrderItem();
                orderItemEntity.setItem(item);
                orderItemEntity.setQuantity(orderItemImportDto.getQuantity());

                if (!validationUtil.isValid(orderItemEntity)) {
                    importResult.append(Constants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                    continue;
                }
                this.itemRepository.saveAndFlush(item);
                this.orderItemRepository.saveAndFlush(orderItemEntity);
                item.getOrderItems().add(orderItemEntity);
                this.itemRepository.saveAndFlush(item);

                orderItemEntity.setOrder(orderEntity);
            }

            this.orderRepository.saveAndFlush(orderEntity);

            importResult
                    .append(String.format("Order for %s on %s added", orderImportDto.getCustomer(), orderImportDto.getDateTime()))
                    .append(System.lineSeparator());
        }
        return importResult.toString().trim();
    }

    @Override
    public String exportOrdersFinishedByTheBurgerFlippers() {
        StringBuilder exportResult = new StringBuilder();
        List<Order> ordersByBurgerFlippers = this.orderRepository.exportOrdersByBurgerFlippers();

        for (Order order : ordersByBurgerFlippers) {
            exportResult.append(String.format("Name: %s", order.getEmployee().getName())).append(System.lineSeparator());
            exportResult.append("Orders:").append(System.lineSeparator());
            exportResult.append(String.format("\tCustomer: %s", order.getCustomer())).append(System.lineSeparator());
            exportResult.append("\tItems:").append(System.lineSeparator());

            for (OrderItem orderItem : order.getOrderItems()) {
                exportResult.append(String.format("\tName:%s", orderItem.getItem().getName())).append(System.lineSeparator());
                exportResult.append(String.format("\tPrice:%s", orderItem.getItem().getPrice())).append(System.lineSeparator());
                exportResult.append(String.format("\tQuantity:%d", orderItem.getQuantity())).append(System.lineSeparator());
                exportResult.append(System.lineSeparator());
            }
        }
        return exportResult.toString().trim();
    }
}
