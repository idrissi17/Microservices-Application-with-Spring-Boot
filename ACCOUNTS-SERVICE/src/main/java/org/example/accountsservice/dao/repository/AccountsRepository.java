package org.example.accountsservice.dao.repository;

import jakarta.transaction.Transactional;
import org.example.accountsservice.dao.entities.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts, Integer> {


    Optional<Accounts> findByCustomerId(Integer customerId);


    void deleteByCustomerId(Integer customerId);
}
