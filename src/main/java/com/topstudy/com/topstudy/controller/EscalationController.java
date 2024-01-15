package com.topstudy.com.topstudy.controller;

import com.topstudy.com.topstudy.dto.EscalationDTO;
import com.topstudy.com.topstudy.model.Escalation;
import com.topstudy.com.topstudy.service.EscalationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/escalations")
public class EscalationController {

    @Autowired
    private EscalationService escalationService; // You'll need to create this service

    // Create an escalation request
    @PostMapping
    public ResponseEntity<Escalation> createEscalation(@RequestBody EscalationDTO escalationDTO) {
        Escalation createdEscalation = escalationService.createEscalation(escalationDTO);
        return new ResponseEntity<>(createdEscalation, HttpStatus.CREATED);
    }

    // Update escalation details by ID
    @PutMapping("/{escalation_id}")
    public ResponseEntity<Escalation> updateEscalation(
            @PathVariable("escalation_id") Long escalationId,
            @RequestBody EscalationDTO escalationDTO) {
        Escalation updatedEscalation = escalationService.updateEscalation(escalationId, escalationDTO);
        if (updatedEscalation != null) {
            return new ResponseEntity<>(updatedEscalation, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Get escalation details by ID
    @GetMapping("/{escalation_id}")
    public ResponseEntity<Escalation> getEscalationById(@PathVariable("escalation_id") Long escalationId) {
        Escalation escalation = escalationService.getEscalationById(escalationId);
        if (escalation != null) {
            return new ResponseEntity<>(escalation, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
