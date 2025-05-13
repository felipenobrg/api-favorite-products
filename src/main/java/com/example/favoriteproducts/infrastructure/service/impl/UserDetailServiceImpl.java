package com.example.favoriteproducts.infrastructure.service.impl;

import java.util.Collections;

import com.example.favoriteproducts.core.entities.Client;
import com.example.favoriteproducts.core.repository.ClientRepository;
import com.example.favoriteproducts.infrastructure.security.model.UserDetailImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private final ClientRepository clientRepository;

    @Autowired
    public UserDetailServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Client client = clientRepository.findByName(username);

        return UserDetailImpl.build(client, Collections.emptyList());
    }
}
