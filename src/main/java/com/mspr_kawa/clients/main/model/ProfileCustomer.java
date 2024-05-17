package com.mspr_kawa.clients.main.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Data
@Embeddable
//@NoArgsConstructor
//@AllArgsConstructor
public class ProfileCustomer {

    @Id
    @UuidGenerator
    private UUID id;
    @Transient
    private String firstName;
    @Transient
    private String lastName;
}
