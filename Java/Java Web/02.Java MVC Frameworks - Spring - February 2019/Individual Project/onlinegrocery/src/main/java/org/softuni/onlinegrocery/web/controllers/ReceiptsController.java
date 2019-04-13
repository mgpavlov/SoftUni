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
    private final ModelMapper modelMapper;

    public ReceiptsController (ReceiptService receiptService,
            ModelMapper modelMapper){
        this.receiptService = receiptService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PageTitle("Receipts")
    public ModelAndView getAllReceipts(ModelAndView modelAndView) {

        List<ReceiptViewModel> allReceipts = mapReceiptServiceToViewModel(receiptService.findAllReceipts());

        modelAndView.addObject("receipts", allReceipts);

        return view("receipt/receipts", modelAndView);
    }

    @GetMapping("/all/details/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PageTitle("Receipts Details")
    public ModelAndView allReceiptDetails(@PathVariable String id, ModelAndView modelAndView) {

        ReceiptViewModel receiptViewModel = modelMapper.map(receiptService.findReceiptById(id), ReceiptViewModel.class);

        modelAndView.addObject("receipt", receiptViewModel);

        return super.view("receipt/receipt-details", modelAndView);
    }

    @GetMapping("/my")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView getMyOrders(ModelAndView modelAndView, Principal principal) {

        List<ReceiptViewModel> myReceipts =
                mapReceiptServiceToViewModel(receiptService.findAllReceiptsByUsername(principal.getName()));

        modelAndView.addObject("receipts", myReceipts);

        return view("receipt/receipts", modelAndView);
    }

    @GetMapping("/my/details/{id}")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("Receipts Details")
    public ModelAndView myOrderDetails(@PathVariable String id, ModelAndView modelAndView) {

        ReceiptServiceModel receipt = receiptService.findReceiptById(id);

        modelAndView.addObject("receipt", modelMapper.map(receipt, ReceiptViewModel.class));

        return super.view("receipt/receipt-details", modelAndView);
    }

    @PostMapping("/create")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView createReceipt(String orderId, Principal principal) {

        receiptService.createReceipt(orderId, principal.getName());

        return super.redirect("/receipts/my");
    }

    @PostMapping("/print")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView printReceipt(String receiptId, Principal principal) throws Exception {

        receiptService.printReceipt(receiptId, principal.getName());

        return redirect("/receipts/my");
    }

    private List<ReceiptViewModel> mapReceiptServiceToViewModel(List<ReceiptServiceModel> receiptServiceModels){
        return receiptServiceModels.stream()
                .map(product -> modelMapper.map(product, ReceiptViewModel.class))
                .collect(Collectors.toList());
    }

}
