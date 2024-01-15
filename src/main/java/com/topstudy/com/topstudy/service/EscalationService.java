package com.topstudy.com.topstudy.service;

import com.topstudy.com.topstudy.dto.EscalationDTO;
import com.topstudy.com.topstudy.model.Escalation;
import com.topstudy.com.topstudy.repository.EscalationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class EscalationService {

    @Autowired
    private EscalationRepository escalationRepository;

    @Transactional
    public Escalation createEscalation(EscalationDTO escalationDTO) {
        Escalation escalation = new Escalation();
        escalation.setRequestReason(escalationDTO.getRequestReason());
        escalation.setDescription(escalationDTO.getDescription());
        escalation.setRequestDate(new Date());
        return escalationRepository.save(escalation);
    }

    @Transactional
    public Escalation updateEscalation(Long escalationId, EscalationDTO escalationDTO) {
        Escalation existingEscalation = escalationRepository.findById(escalationId).orElse(null);

        if (existingEscalation != null) {
            existingEscalation.setRequestReason(escalationDTO.getRequestReason());
            existingEscalation.setDescription(escalationDTO.getDescription());
            return escalationRepository.save(existingEscalation);
        }

        return null; // Return null if the escalation with the specified ID is not found
    }

    @Transactional(readOnly = true)
    public Escalation getEscalationById(Long escalationId) {
        return escalationRepository.findById(escalationId).orElse(null);
    }
}
