package com.topstudy.com.topstudy.repository;

import com.topstudy.com.topstudy.model.Escalation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EscalationRepository extends JpaRepository<Escalation, Long> {
}
