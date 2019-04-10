package org.softuni.onlinegrocery.service;

import org.modelmapper.ModelMapper;
import org.softuni.onlinegrocery.domain.entities.Order;
import org.softuni.onlinegrocery.domain.entities.Receipt;
import org.softuni.onlinegrocery.domain.entities.User;
import org.softuni.onlinegrocery.domain.models.service.ReceiptServiceModel;
import org.softuni.onlinegrocery.repository.OrderRepository;
import org.softuni.onlinegrocery.repository.ReceiptRepository;
import org.softuni.onlinegrocery.repository.UserRepository;
import org.softuni.onlinegrocery.util.PdfGenaratorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReceiptServiceImpl implements ReceiptService {

    private final ReceiptRepository receiptRepository;
    private final OrderRepository orderRepository;
    private final OrderService orderService;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;


    @Autowired
    PdfGenaratorUtil pdfGenaratorUtil;

    @Autowired
    public ReceiptServiceImpl(ReceiptRepository receiptRepository, OrderRepository orderRepository, OrderService orderService, UserRepository userRepository, ModelMapper modelMapper) {
        this.receiptRepository = receiptRepository;
        this.orderRepository = orderRepository;
        this.orderService = orderService;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<ReceiptServiceModel> findAllReceiptsByUsername(String username) {
        return this.receiptRepository
                .findAllReceiptsByRecipient_UsernameOrderByIssuedOn(username)
                .stream()
                .map(r -> this.modelMapper.map(r, ReceiptServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ReceiptServiceModel> findAllReceipts() {
        return this.receiptRepository
                .findAll()
                .stream()
                .map(r -> this.modelMapper.map(r, ReceiptServiceModel.class))
                .collect(Collectors.toList());
    }
    @Override
    public void receiptRegister(ReceiptServiceModel receiptServiceModel) {
        Receipt receipt = this.modelMapper.map(receiptServiceModel, Receipt.class);
        this.receiptRepository.save(receipt);
    }

    @Override
    public ReceiptServiceModel getReceiptById(String id) {
        Receipt receipt = this.receiptRepository.findById(id).orElse(null);
        return modelMapper.map(receipt, ReceiptServiceModel.class);
    }

    @Override
    public void createReceipt(String orderId, String name) {
        Order order = this.orderRepository.findById(orderId).orElse(null);
        User recipient = this.userRepository.findByUsername(name).orElse(null);

        Receipt receipt = new Receipt();

        receipt.setFee(order.getTotalPrice());
        receipt.setIssuedOn(LocalDateTime.now());
        receipt.setOrder(order);
        receipt.setRecipient(recipient);

        this.receiptRepository.save(receipt);

        this.orderService.changeOrderStatus(orderId);
    }

    @Override
    public ReceiptServiceModel findReceiptById(String receiptId) {

        Receipt receipt = this.receiptRepository.findById(receiptId).orElse(null);

        return modelMapper.map(receipt, ReceiptServiceModel.class);
    }

    @Override
    public void printReceipt(String receiptId, String username) throws Exception {
        Map<String,String> data = new HashMap<String,String>();
        data.put("name", username+"-"+receiptId);
        pdfGenaratorUtil.createPdf("test", data);
    }
}
