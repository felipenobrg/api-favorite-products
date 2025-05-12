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

    public Client execute(String nome, String email) {
        if (clientRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email already registered");
        }

        Client client = new Client(UUID.randomUUID(), nome, email);
        return clientRepository.save(client);
    }
}
