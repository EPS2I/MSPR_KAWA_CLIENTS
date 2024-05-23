package com.mspr_kawa.clients.main.db_services;

import com.mspr_kawa.clients.main.model.Customer;
import com.mspr_kawa.clients.main.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerSyncScheduler {

    private final CustomerSyncService customerSyncService;
    private final CustomerService customerService;


    @Autowired
    public CustomerSyncScheduler(CustomerSyncService customerSyncService, CustomerService customerService) {
        this.customerSyncService = customerSyncService;
        this.customerService = customerService;
    }

    @Scheduled(fixedRate = 3000)  // Sync every 3 seconds
    public void syncClients() {
        List<Customer> customers = this.fetchClientsFromSQLite();
        customerSyncService.syncCustomersToMainDb(customers);
    }

    private List<Customer> fetchClientsFromSQLite() {
        return this.customerService.getAllCustomers();
    }
}
