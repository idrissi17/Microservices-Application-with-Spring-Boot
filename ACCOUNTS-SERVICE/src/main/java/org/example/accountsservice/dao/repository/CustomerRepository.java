package org.example.accountsservice.dao.repository;

import org.example.accountsservice.dao.entities.Customer;
import org.example.accountsservice.dao.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Optional<Customer> findByPhoneNumber(String phoneNumber);
}
