package com.mspr_kawa.clients.main.db_services;

import com.mspr_kawa.clients.main.model.Customer;
import com.mspr_kawa.clients.main.security.KeycloakConfigCustom;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@Service
public class CustomerSyncService {

    @Value("${keycloak.resource}")
    private String clientId;

    @Value("${keycloak.credentials.secret}")
    private String clientSecret;

    @Value("${app.config-url.api-db}")
    private String apiDbUrl;

    private final RestTemplate restTemplate;

    private final KeycloakConfigCustom keycloakConfigCustom;

    public CustomerSyncService(RestTemplate restTemplate, KeycloakConfigCustom keycloakConfigCustom) {
        this.restTemplate = restTemplate;
        this.keycloakConfigCustom = keycloakConfigCustom;
    }

    public List<Customer> fetchClientsFromMainDb() throws IOException {
        String apiBaseUrl = "http://" + this.apiDbUrl + "/customers";
        String accessToken = keycloakConfigCustom.getAccessToken();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.set("Accept", "application/json");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Customer[]> response = restTemplate.exchange(apiBaseUrl, HttpMethod.GET, entity, Customer[].class);
        if (response.getStatusCode().is2xxSuccessful()) {
            return List.of(response.getBody());
        } else {
            throw new RuntimeException("Failed to get data from API BDD: " + response.getStatusCode());
        }
    }

    public void syncCustomersToMainDb(List<Customer> customers) {
        String apiBaseUrl = "http://" + this.apiDbUrl + "/customers";
        for (Customer customer : customers) {
            restTemplate.postForEntity(apiBaseUrl, customer, Customer.class);
        }
    }
}
