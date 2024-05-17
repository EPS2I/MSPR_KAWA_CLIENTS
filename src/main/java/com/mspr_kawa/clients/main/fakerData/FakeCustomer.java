package com.mspr_kawa.clients.main.fakerData;


import com.github.javafaker.Faker;
import com.mspr_kawa.clients.main.model.Adress;
import com.mspr_kawa.clients.main.model.Company;
import com.mspr_kawa.clients.main.model.Customer;
import com.mspr_kawa.clients.main.model.ProfileCustomer;
import com.mspr_kawa.clients.main.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
public class FakeCustomer {

    private final CustomerRepository customerRepository;

    private final Faker faker;

    @Autowired
    public FakeCustomer(Faker faker, CustomerRepository customerRepository) {
        this.faker = faker;
        this.customerRepository = customerRepository;
        this.initDB();
    }

    public void initDB(){
        for (int i = 0; i < 100; i++) {
            this.customerRepository.save(this.generateFakeCustomer());
        }
    }
    public Customer generateFakeCustomer() {
        Customer customer = new Customer();
        customer.setFirstName(faker.name().firstName());
        customer.setLastName(faker.name().lastName());
        customer.setUsername(faker.name().username());
        customer.setName(customer.getFirstName() + " " + customer.getLastName());

        Adress address = new Adress();
        address.setPostalCode(faker.address().zipCode());
        address.setCity(faker.address().city());
        customer.setAdress(address);

        Company company = new Company();
        company.setCompanyName(faker.company().name());
        customer.setCompany(company);

        ProfileCustomer profile = new ProfileCustomer();
        profile.setFirstName(faker.name().firstName());
        profile.setLastName(faker.name().lastName());
        customer.setProfile(profile);

        return customer;
    }


}
