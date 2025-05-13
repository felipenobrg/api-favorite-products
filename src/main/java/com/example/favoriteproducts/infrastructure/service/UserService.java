package com.example.favoriteproducts.infrastructure.service;

import com.example.favoriteproducts.core.entities.Client;

public interface UserService {

    Client createUser(Client client);

    Client login(Client client);
}