package com.example.favoriteproducts.infrastructure.adapters;
import com.example.favoriteproducts.core.entities.Client;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.UUID;

@Entity
public class ClientEntity {
    @Id
    private UUID id;
    private String name;
    private String email;
    private String password;


    public Client toDomain() {
        return new Client(id, name, email, password);
    }

    public static ClientEntity fromDomain(Client client) {
        ClientEntity entity = new ClientEntity();
        entity.id = client.getId();
        entity.name = client.getName();
        entity.email = client.getEmail();
        entity.password = client.getPassword();
        return entity;
    }
}
