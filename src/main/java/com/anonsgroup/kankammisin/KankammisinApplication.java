package com.anonsgroup.kankammisin;

import com.anonsgroup.kankammisin.model.Soru;
import com.anonsgroup.kankammisin.repositories.SoruRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.util.List;

@SpringBootApplication
public class KankammisinApplication extends SpringBootServletInitializer {


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(KankammisinApplication.class);
    }
    public static void main(String[] args) {
        SpringApplication.run(KankammisinApplication.class, args);
    }


}
