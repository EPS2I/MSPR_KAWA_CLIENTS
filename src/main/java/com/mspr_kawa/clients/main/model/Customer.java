package com.mspr_kawa.clients.main.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

// visualisation of object Customer
//    id	"11"
//    name	"Krista Crooks"
//    username	"Gerald_Hansen"
//    firstName	"Gabriel"
//    lastName	"Connelly"
//    address
//        postalCode	"84288"
//        city	"Heaneyfort"
//    profile
//        firstName	"Dorthy"
//        lastName	"Herman"
//    company
//        companyName	"Steuber - Wilderman"
//    createdAt	"2024-04-18T16:28:23.736Z"

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @UuidGenerator
    private UUID id;

    private String name;
    private String username;
    private String firstName;
    private String lastName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Adress adress;

    @Embedded
    private ProfileCustomer profile;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private Company company;

}