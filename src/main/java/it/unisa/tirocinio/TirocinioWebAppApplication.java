package it.unisa.tirocinio;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@EnableConfigurationProperties
@SpringBootApplication
public class TirocinioWebAppApplication {
    @Bean
    public ObjectMapper mapper() {
        return new ObjectMapper().registerModule(new JavaTimeModule());
    }
    public static void main(String[] args) {
        SpringApplication.run(TirocinioWebAppApplication.class, args);
    }

}