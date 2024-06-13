package com.mspr_kawa.clients.main.db_services;

import com.mspr_kawa.clients.main.model.Customer;
import com.mspr_kawa.clients.main.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

/*@Component*/
public class ApplicationStartUp {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationStartUp.class);

    private final CustomerSyncService customerSyncService;

    private final CustomerRepository customerRepository;

    public ApplicationStartUp(CustomerSyncService customerSyncService, CustomerRepository customerRepository) {
        this.customerSyncService = customerSyncService;
        this.customerRepository = customerRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationEvent() throws IOException {
        logger.info("Application started, fetching clients from main db...");
        try {
            List<Customer> customerList = customerSyncService.fetchClientsFromMainDb();
            customerRepository.saveAll(customerList);
            logger.info("Fetched and saved {} customers from main db.", customerList.size());
        } catch (Exception e) {
            logger.error("Error fetching clients from main db", e);
        }
    }
}
