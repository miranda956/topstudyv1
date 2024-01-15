package com.topstudy.com.topstudy.repository;

import com.topstudy.com.topstudy.model.ClientAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientAccountRepository extends JpaRepository<ClientAccount, Long> {
    // You can add custom query methods here if needed
}
