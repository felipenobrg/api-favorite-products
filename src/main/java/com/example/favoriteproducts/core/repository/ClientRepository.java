package com.example.favoriteproducts.core.repository;

import com.example.favoriteproducts.core.entities.Client;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClientRepository {
    Client save(Client client);
    Optional<Client> findById(UUID id);
    Optional<Client> findByEmail(String email);
    List<Client> findAll();
    void deleteById(UUID id);
    boolean existsByEmail(String email);
    Client findByName(String name);
}
