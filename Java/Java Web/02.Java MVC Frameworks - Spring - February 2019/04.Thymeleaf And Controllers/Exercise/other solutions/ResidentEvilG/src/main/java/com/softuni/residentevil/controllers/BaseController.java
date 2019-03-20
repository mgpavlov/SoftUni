package com.softuni.residentevil.controllers;

import com.softuni.residentevil.utils.MessageWrapper;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.ModelAndView;

abstract class BaseController {

    private static final String APPLICATION_TITLE = "application.title";
    private static final String BASE_PAGE_LAYOUT = "/fragments/base-layout";
    private static final String PROPERTY_VIEW_NAME = "viewName";
    private static final String PROPERTY_VIEW_MODEL = "viewModel";
    private static final String PROPERTY_TITLE = "title";
    private static final String REDIRECT_KEYWORD = "redirect:";

    private final MessageWrapper messageWrapper;

    protected BaseController(final MessageWrapper messageWrapper) {
        this.messageWrapper = messageWrapper;
    }

    final ModelAndView view(final String viewName,
                            final Object viewModel,
                            String title) {
        if (title == null) {
            title = this.messageWrapper.getMessage(APPLICATION_TITLE);
        }

        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(BASE_PAGE_LAYOUT);
        modelAndView.addObject(PROPERTY_VIEW_NAME, viewName);
        modelAndView.addObject(PROPERTY_VIEW_MODEL, viewModel);
        modelAndView.addObject(PROPERTY_TITLE, title);

        return modelAndView;
    }

    final ModelAndView view(final String viewName,
                            final String title) {
        return this.view(viewName, null, title);
    }

    final ModelAndView view(final String viewName,
                            final Object viewModel) {
        return this.view(viewName, viewModel, null);
    }

    final ModelAndView view(final String viewName) {
        return this.view(viewName, null, null);
    }

    final ModelAndView redirect(final String redirectUrl, final Object viewModel) {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(PROPERTY_VIEW_MODEL, viewModel);
        modelAndView.setViewName(REDIRECT_KEYWORD + redirectUrl);
        return modelAndView;
    }

    final ModelAndView redirect(final String redirectUrl) {
        return this.redirect(redirectUrl, null);
    }

    @InitBinder
    private void initBinder(final WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(false)); //Trim form input strings
    }

/*    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }*/
}
