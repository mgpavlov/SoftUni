package com.softuni.residentevil.utils;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public final class MessageWrapper {

    private final MessageSource messageSource;

    public MessageWrapper(final MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getMessage(final String code, final Object... args) {
        return this.messageSource.getMessage(code, args, LocaleContextHolder.getLocale()); // Locale.getDefault()
    }
}
