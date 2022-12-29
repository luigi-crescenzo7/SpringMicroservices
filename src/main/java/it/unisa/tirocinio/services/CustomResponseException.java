package it.unisa.tirocinio.services;

import org.springframework.http.ResponseEntity;

public class CustomResponseException extends RuntimeException {
    private final ResponseEntity<String> responseEntity;

    public CustomResponseException(ResponseEntity<String> responseEntity) {
        super(responseEntity.getBody());
        this.responseEntity = responseEntity;
    }


    String getResponseStatusCode() {
        return responseEntity.getStatusCode().toString();
    }

    String getResponseBody() {
        return responseEntity.getBody();
    }

    @Override
    public String toString() {
        return "CustomResponseException{" +
                "StatusCode=" + this.getResponseStatusCode() +
                " Body=" + this.getResponseBody() +
                '}';
    }
}
