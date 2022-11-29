package it.unisa.tirocinio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class TirocinioWebAppApplication {
    @Bean
    public PasswordEncoder bcryptPasswordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    public static void main(String[] args) {
        SpringApplication.run(TirocinioWebAppApplication.class, args);
    }

}