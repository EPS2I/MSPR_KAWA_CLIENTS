package com.mspr_kawa.clients.main.service;

import com.mspr_kawa.clients.main.model.Customer;
import com.mspr_kawa.clients.main.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // Méthode pour récupérer une liste de clients
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // Méthode pour récupérer un client par son identifiant
    public Customer getCustomerById(UUID id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        return optionalCustomer.orElse(null);
    }

    // Méthode pour créer un nouveau client
    public Customer createCustomer(Customer customer) {
        // Assurez-vous que l'ID est vide pour créer un nouveau client
        customer.setId(null);
        return customerRepository.save(customer);
    }

    // Méthode pour mettre à jour un client existant
    public Customer updateCustomer(UUID id, Customer customer) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            // Assurez-vous que l'ID du client à mettre à jour correspond à l'ID fourni
            Customer existingCustomer = optionalCustomer.get();
            if (existingCustomer.getId().equals(customer.getId())) {
                return customerRepository.save(customer);
            }
        }
        return null;
    }

    // Méthode pour supprimer un client
    public boolean deleteCustomer(UUID id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            customerRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

