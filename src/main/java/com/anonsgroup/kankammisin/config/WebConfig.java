package com.anonsgroup.kankammisin.config;

import com.anonsgroup.kankammisin.Conventers.StringToKategori;
import com.anonsgroup.kankammisin.Conventers.StringToUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(
                "/webjars/**",
                "/images/**",
                "/css/**",
                "/js/**")
                .addResourceLocations(
                        "classpath:/META-INF/resources/webjars/",
                        "classpath:/static/images/",
                        "classpath:/static/css/",
                        "classpath:/static/js/");
    }

    @Autowired
    StringToUser userFormatter;
    @Autowired
    StringToKategori kategoriFormatter;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(userFormatter);
        registry.addFormatter(kategoriFormatter);

    }
}