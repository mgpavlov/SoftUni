package org.softuni.onlinegrocery.web.controllers;

import org.modelmapper.ModelMapper;

import org.softuni.onlinegrocery.domain.models.service.ReceiptServiceModel;
import org.softuni.onlinegrocery.domain.models.view.ReceiptViewModel;
import org.softuni.onlinegrocery.service.ReceiptService;

import org.softuni.onlinegrocery.web.annotations.PageTitle;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/receipts")
public class ReceiptsController extends BaseController {

    private final ReceiptService receiptService;
    private final ModelMapper mapper;

    public ReceiptsController (ReceiptService receiptService,
            ModelMapper modelMapper){
        this.receiptService = receiptService;
        this.mapper = modelMapper;
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PageTitle("Receipts")
    public ModelAndView getAllReceipts(ModelAndView modelAndView) {
        List<ReceiptViewModel> allReceipts = receiptService.findAllReceipts()
                .stream()
                .map(r -> mapper.map(r, ReceiptViewModel.class))
                .collect(Collectors.toList());

        modelAndView.addObject("receipts", allReceipts);

        return view("receipt/receipts", modelAndView);
    }

    @GetMapping("/all/details/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PageTitle("Receipts Details")
    public ModelAndView allReceiptDetails(@PathVariable String id, ModelAndView modelAndView) {
        ReceiptServiceModel receipt = this.receiptService.findReceiptById(id);
        modelAndView.addObject("receipt", this.mapper.map(receipt, ReceiptViewModel.class));

        return super.view("receipt/receipt-details", modelAndView);
    }

    @GetMapping("/my")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView getMyOrders(ModelAndView modelAndView, Principal principal) {
        String customerName = principal.getName();
        List<ReceiptViewModel> myReceipts = receiptService.findAllReceiptsByUsername(customerName)
                .stream()
                .map(r -> mapper.map(r, ReceiptViewModel.class))
                .collect(Collectors.toList());

        modelAndView.addObject("receipts", myReceipts);


        return view("receipt/receipts", modelAndView);
    }

    @GetMapping("/my/details/{id}")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("Receipts Details")
    public ModelAndView myOrderDetails(@PathVariable String id, ModelAndView modelAndView) {
        ReceiptServiceModel receipt = this.receiptService.findReceiptById(id);
        modelAndView.addObject("receipt", this.mapper.map(receipt, ReceiptViewModel.class));

        return super.view("receipt/receipt-details", modelAndView);
    }

    @PostMapping("/create")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView createReceipt(String orderId, Principal principal) {
        this.receiptService.createReceipt(orderId, principal.getName());

        return super.redirect("/receipts/my");
    }

    @PostMapping("/print")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView printReceipt(String receiptId, Principal principal) throws Exception {
        this.receiptService.printReceipt(receiptId, principal.getName());

        return redirect("/receipts/my");
    }

}
