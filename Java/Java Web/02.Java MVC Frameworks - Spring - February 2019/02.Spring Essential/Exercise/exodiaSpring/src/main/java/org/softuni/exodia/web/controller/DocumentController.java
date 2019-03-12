package org.softuni.exodia.web.controller;

import org.modelmapper.ModelMapper;
import org.softuni.exodia.domain.model.binding.DocumentCreateBindingModel;
import org.softuni.exodia.domain.model.service.DocumentServiceModel;
import org.softuni.exodia.domain.model.view.DocumentDetailsViewModel;
import org.softuni.exodia.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class DocumentController extends BaseController {
    private final DocumentService documentService;
    private final ModelMapper modelMapper;

    @Autowired
    public DocumentController(DocumentService documentService, ModelMapper modelMapper) {
        this.documentService = documentService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/schedule")
    public ModelAndView schedule(ModelAndView modelAndView, HttpSession session) {
        if (!this.isLoggedIn(session)) {
            return this.view("redirect:/", modelAndView);
        }
        return this.view("schedule", modelAndView);
    }

    @PostMapping("/schedule")
    public ModelAndView confirmSchedule(@ModelAttribute(name = "model") DocumentCreateBindingModel model, ModelAndView modelAndView) {
        DocumentServiceModel documentServiceModel = this.documentService.registerDocument(this.modelMapper.map(model, DocumentServiceModel.class));
        if (documentServiceModel != null) {
            return this.view("redirect:/details/" + documentServiceModel.getId(), modelAndView);
        }
        return this.view("redirect:/schedule", modelAndView);
    }

    @GetMapping("/details/{id}")
    public ModelAndView details(@PathVariable(name = "id") String id, ModelAndView modelAndView, HttpSession session) {
        if (!this.isLoggedIn(session)) {
            return this.view("redirect:/", modelAndView);
        }
        DocumentDetailsViewModel documentDetailsViewModel = mapDocument(id);
        modelAndView.setViewName("details");
        modelAndView.addObject("document", documentDetailsViewModel);

        return modelAndView;
    }

    @GetMapping("/print/{id}")
    public ModelAndView delete(@PathVariable(name = "id") String id, ModelAndView modelAndView, HttpSession session) {
        if (!this.isLoggedIn(session)) {
            return this.view("redirect:/", modelAndView);
        }
        DocumentDetailsViewModel documentDetailsViewModel = mapDocument(id);
        modelAndView.setViewName("print");
        modelAndView.addObject("document", documentDetailsViewModel);
        return modelAndView;
    }

    @PostMapping("/print/{id}")
    public ModelAndView confirmDelete(@PathVariable(name = "id") String id,
                                      ModelAndView modelAndView, HttpSession session) {
        if (!this.isLoggedIn(session)) {
            return this.view("redirect:/", modelAndView);
        }
        this.documentService.deleteById(id);
        return this.view("redirect:/home", modelAndView);
    }

    private DocumentDetailsViewModel mapDocument(String id) {
        DocumentServiceModel documentServiceModel = this.documentService.findById(id);
        if (documentServiceModel == null) {
            throw new IllegalArgumentException("Document doesn't exist");
        }
        return this.modelMapper.map(documentServiceModel, DocumentDetailsViewModel.class);
    }
}
