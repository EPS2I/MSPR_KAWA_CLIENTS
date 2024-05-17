package com.mspr_kawa.clients.main.repository;

import com.mspr_kawa.clients.main.model.Adress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AdressRepository extends JpaRepository<Adress, UUID> {
}
