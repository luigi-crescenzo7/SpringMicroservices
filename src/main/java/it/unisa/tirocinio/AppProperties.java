package it.unisa.tirocinio;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "app")
public class AppProperties {
    String usersRestUrl;
    String usersRestPort;

    String fabricRestUrl;
    String fabricRestPort;
}
