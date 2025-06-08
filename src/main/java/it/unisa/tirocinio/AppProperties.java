package it.unisa.tirocinio;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app")
public class AppProperties {
    String vaultItemsServiceUrl;
    String vaultItemsServicePort;
    String usersServiceUrl;
    String usersServicePort;

    String fabricRestUrl;
    String fabricRestPort;
}
