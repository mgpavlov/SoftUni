package org.softuni.onlinemarket.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
/*import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;*/

@Configuration
public class ThymeleafConfig {

    @Bean
    public SpringSecurityDialect springSecurityDialect(){
        return new SpringSecurityDialect();
    }

    @Bean
    public ClassLoaderTemplateResolver emailTemplateResolver(){
        ClassLoaderTemplateResolver emailTemplateResolver=new ClassLoaderTemplateResolver();
        emailTemplateResolver.setPrefix("templates/");
        emailTemplateResolver.setTemplateMode("HTML5");
        emailTemplateResolver.setSuffix(".html");
        emailTemplateResolver.setTemplateMode("XHTML");
        emailTemplateResolver.setCharacterEncoding("UTF-8");
        emailTemplateResolver.setOrder(1);
        return emailTemplateResolver;
    }
}
