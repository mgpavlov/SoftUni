/*
package com.softuni.residentevil.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.Locale;

@Configuration
//@EnableWebMvc
@ComponentScan("com.softuni.residentevil.controllers")
public class WebMvcConfig implements WebMvcConfigurer {

    private static final String PARAMETER_LANGUAGE = "lang";
    private static final String LOCALE_COOKIE_NAME = "my-locale";
    private static final int COOKIE_MAX_AGE_IN_SECONDS = 3600;

    @Bean
    public MessageSourceAccessor messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        return new MessageSourceAccessor(messageSource, LocaleContextHolder.getLocale());
    }

    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver localeResolver = new CookieLocaleResolver();
        localeResolver.setDefaultLocale(Locale.ENGLISH);
        localeResolver.setCookieName(LOCALE_COOKIE_NAME);
        localeResolver.setCookieMaxAge(COOKIE_MAX_AGE_IN_SECONDS);
        return localeResolver;
    }

    private HandlerInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName(PARAMETER_LANGUAGE);
        return interceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
}
*/
