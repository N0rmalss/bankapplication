package com.example.bankapp;

import com.example.bankapp.data.entity.Contract;
import com.example.bankapp.data.entity.LoanRequest;
import com.example.bankapp.data.entity.UserLoanInf;
import org.hibernate.SessionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;

@SpringBootApplication
public class BankappApplication {
    private final ApplicationContext applicationContext;

    public BankappApplication(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        templateResolver.setPrefix("/WEB-INF/views/");
        templateResolver.setSuffix(".html");
        templateResolver.setCharacterEncoding("UTF-8");
        return templateResolver;
    }
    @Bean
    public SessionFactory sessionFactory() {
        org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
        configuration.addAnnotatedClass(UserLoanInf.class);
        configuration.addAnnotatedClass(LoanRequest.class);
        configuration.addAnnotatedClass(Contract.class);
        configuration.configure();
        return configuration.buildSessionFactory();
    }

    public static void main(String[] args) {
        SpringApplication.run(BankappApplication.class, args);
    }

}
