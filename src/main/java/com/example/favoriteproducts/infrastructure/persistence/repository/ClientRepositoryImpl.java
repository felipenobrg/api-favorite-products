package com.example.favoriteproducts.infrastructure.persistence.repository;

import com.example.favoriteproducts.core.entities.Client;
import com.example.favoriteproducts.core.repository.ClientRepository;
import com.example.favoriteproducts.infrastructure.adapters.ClientEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class ClientRepositoryImpl implements ClientRepository {

    private final JpaClientRepository jpaClientRepository;

    public ClientRepositoryImpl(JpaClientRepository jpaClientRepository) {
        this.jpaClientRepository = jpaClientRepository;
    }

    @Override
    public Client save(Client client) {
        ClientEntity entity = ClientEntity.fromDomain(client);
        return jpaClientRepository.save(entity).toDomain();
    }

    @Override
    public Optional<Client> findById(UUID id) {
        return jpaClientRepository.findById(id).map(ClientEntity::toDomain);
    }

    @Override
    public Optional<Client> findByEmail(String email) {
        return jpaClientRepository.findByEmail(email).map(ClientEntity::toDomain);
    }

    @Override
    public Client findByName(String name) {
        return jpaClientRepository.findByName(name)
                .map(ClientEntity::toDomain)
                .orElse(null);
    }

    @Override
    public List<Client> findAll() {
        return jpaClientRepository.findAll()
                .stream()
                .map(ClientEntity::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(UUID id) {
        jpaClientRepository.deleteById(id);
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpaClientRepository.existsByEmail(email);
    }
}
