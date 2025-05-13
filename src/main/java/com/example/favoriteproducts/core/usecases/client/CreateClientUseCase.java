package com.example.favoriteproducts.core.usecases.client;

import com.example.favoriteproducts.core.entities.Client;
import com.example.favoriteproducts.core.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CreateClientUseCase {

    private final ClientRepository clientRepository;

    @Autowired
    public CreateClientUseCase(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client execute(String name, String email, String password) {
        validateInputs(name, email, password);

        if (clientRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email is already registered.");
        }

        Client client = new Client(UUID.randomUUID(), name, email, password);
        return clientRepository.save(client);
    }

    private void validateInputs(String name, String email, String password) {
        if (isNullOrEmpty(name)) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        if (isNullOrEmpty(email)) {
            throw new IllegalArgumentException("Email cannot be null or empty.");
        }
        if (isNullOrEmpty(password)) {
            throw new IllegalArgumentException("Password cannot be null or empty.");
        }
    }

    private boolean isNullOrEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }
}

