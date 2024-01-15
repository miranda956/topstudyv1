package com.topstudy.com.topstudy.controller;

import com.topstudy.com.topstudy.dto.ClientAccountDTO;
import com.topstudy.com.topstudy.model.ClientAccount;
import com.topstudy.com.topstudy.service.ClientAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clients")
public class ClientAccountController {

    @Autowired
    private ClientAccountService clientAccountService;

    // Create a new client account
    @PostMapping
    public ResponseEntity<ClientAccount> createClientAccount(@RequestBody ClientAccountDTO clientAccountDTO) {
        ClientAccount createdClientAccount = clientAccountService.createClientAccount(clientAccountDTO);
        return new ResponseEntity<>(createdClientAccount, HttpStatus.CREATED);
    }

    // Update client account details by ID
    @PutMapping("/{client_id}")
    public ResponseEntity<ClientAccount> updateClientAccount(
            @PathVariable("client_id") Long clientId,
            @RequestBody ClientAccountDTO clientAccountDTO) {
        ClientAccount updatedClientAccount = clientAccountService.updateClientAccount(clientId, clientAccountDTO);
        if (updatedClientAccount != null) {
            return new ResponseEntity<>(updatedClientAccount, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Get client account details by ID
    @GetMapping("/{client_id}")
    public ResponseEntity<ClientAccount> getClientAccountById(@PathVariable("client_id") Long clientId) {
        ClientAccount clientAccount = clientAccountService.getClientAccountById(clientId);
        if (clientAccount != null) {
            return new ResponseEntity<>(clientAccount, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
