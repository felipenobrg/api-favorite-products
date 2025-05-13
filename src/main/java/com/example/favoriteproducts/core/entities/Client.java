package com.example.favoriteproducts.core.entities;

import java.util.UUID;

public class Client {
    private UUID id;
    private String name;
    private String email;
    private String password;

    public Client(UUID id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        validate();
    }

    private void validate() {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do cliente não pode ser vazio");
        }

        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email do cliente não pode ser vazio");
        }

        if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new IllegalArgumentException("Formato de email inválido");
        }
    }

    public String getPassword() {
        return password;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

}
