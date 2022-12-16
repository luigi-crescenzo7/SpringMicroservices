package it.unisa.tirocinio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties
@SpringBootApplication
public class TirocinioWebAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(TirocinioWebAppApplication.class, args);
    }

}