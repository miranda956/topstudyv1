package com.topstudy.com.topstudy.service;

import com.topstudy.com.topstudy.dto.ClientAccountDTO;
import com.topstudy.com.topstudy.model.ClientAccount;
import com.topstudy.com.topstudy.repository.ClientAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientAccountService {

    @Autowired
    private ClientAccountRepository clientAccountRepository;

    @Transactional
    public ClientAccount createClientAccount(ClientAccountDTO clientAccountDTO) {
        ClientAccount clientAccount = new ClientAccount();
        clientAccount.setName(clientAccountDTO.getName());
        clientAccount.setEmail(clientAccountDTO.getEmail());

        // Set other client account properties as needed

        return clientAccountRepository.save(clientAccount);
    }

    @Transactional
    public ClientAccount updateClientAccount(Long clientId, ClientAccountDTO clientAccountDTO) {
        ClientAccount existingClientAccount = clientAccountRepository.findById(clientId).orElse(null);

        if (existingClientAccount != null) {
            existingClientAccount.setName(clientAccountDTO.getName());
            existingClientAccount.setEmail(clientAccountDTO.getEmail());

            // Update other client account properties as needed

            return clientAccountRepository.save(existingClientAccount);
        }

        return null; // Return null if the client account with the specified ID is not found
    }

    @Transactional(readOnly = true)
    public ClientAccount getClientAccountById(Long clientId) {
        return clientAccountRepository.findById(clientId).orElse(null);
    }
}
