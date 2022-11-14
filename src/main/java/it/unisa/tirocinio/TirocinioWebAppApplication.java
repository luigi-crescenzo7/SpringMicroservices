package it.unisa.tirocinio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class TirocinioWebAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(TirocinioWebAppApplication.class, args);
    }

}