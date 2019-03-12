package exodia.web.controllers;

import exodia.domain.models.bindings.DocumentScheduleBindingModel;
import exodia.domain.models.services.DocumentServiceModel;
import exodia.domain.models.views.DocumentDetailsViewModel;
import exodia.domain.models.views.DocumentPrintViewModel;
import exodia.service.DocumentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

import static exodia.constants.ConstantMessages.*;

@Controller
public class DocumentController {

    private final DocumentService documentService;
    private final ModelMapper modelMapper;

    @Autowired
    public DocumentController(DocumentService documentService, ModelMapper modelMapper) {
        this.documentService = documentService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/schedule")
    public ModelAndView schedule(ModelAndView modelAndView, HttpSession session) {
        if (session.getAttribute("username") == null) {
            modelAndView.setViewName("redirect:/login");
        } else {
            modelAndView.setViewName("schedule");
        }
        return modelAndView;
    }

    @PostMapping("/schedule")
    public ModelAndView scheduleConfirm(@ModelAttribute DocumentScheduleBindingModel scheduleBindingModel, ModelAndView modelAndView) {
        DocumentServiceModel documentServiceModel =
                this.documentService.scheduleDocument(this.modelMapper.map(scheduleBindingModel, DocumentServiceModel.class));

        if (documentServiceModel == null) {
            throw new IllegalArgumentException(DOCUMENT_CREATION_FAILED);
        }

        modelAndView.setViewName("redirect:/details/" + documentServiceModel.getId());
        return modelAndView;
    }

    @GetMapping("/details/{id}")
    public ModelAndView details(@PathVariable("id") String id, ModelAndView modelAndView, HttpSession session) {
        if (session.getAttribute("username") == null) {
            modelAndView.setViewName("redirect:/login");
        } else {

            DocumentServiceModel documentServiceModel = this.documentService.findDocumentById(id);
            if (documentServiceModel == null) {
                throw new IllegalArgumentException(DOCUMENT_NOT_FOUND);
            }

            modelAndView.setViewName("details");
            modelAndView.addObject("model",
                    this.modelMapper.map(documentServiceModel, DocumentDetailsViewModel.class));
        }
        return modelAndView;
    }

    @GetMapping("/print/{id}")
    public ModelAndView print(@PathVariable("id") String id, ModelAndView modelAndView, HttpSession session) {

        if (session.getAttribute("username") == null) {
            modelAndView.setViewName("redirect:/login");
        } else {

            DocumentServiceModel documentServiceModel = this.documentService.findDocumentById(id);
            if (documentServiceModel == null) {
                throw new IllegalArgumentException(DOCUMENT_NOT_FOUND);
            }

            modelAndView.setViewName("print");
            modelAndView.addObject("model", this.modelMapper.map(documentServiceModel, DocumentPrintViewModel.class));
        }

        return modelAndView;
    }

    @PostMapping("/print/{id}")
    public ModelAndView printConfirm(@PathVariable("id") String id, ModelAndView modelAndView, HttpSession session) {
        if (session.getAttribute("username") == null) {
            modelAndView.setViewName("redirect:/login");
        } else {

            if (!this.documentService.printDocumentById(id)) {
                throw new IllegalArgumentException(DOCUMENT_CAN_NOT_BE_PRINTED);
            }

            modelAndView.setViewName("redirect:/home");
        }
        return modelAndView;
    }
}