package com.example.favoriteproducts.presentation.controllers;

import com.example.favoriteproducts.core.entities.Client;
import com.example.favoriteproducts.core.repository.ClientRepository;
import com.example.favoriteproducts.core.usecases.client.CreateClientUseCase;
import com.example.favoriteproducts.presentation.dto.request.LoginRequest;
import com.example.favoriteproducts.presentation.dto.request.ClientRequest;
import com.example.favoriteproducts.presentation.dto.response.ClientResponse;
import com.example.favoriteproducts.presentation.dto.mappers.ClientMapper;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class ClientController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CreateClientUseCase createClientUseCase;

    @Autowired
    private ClientMapper clientMapper;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateClient(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        Client client = clientRepository.findByName(loginRequest.getUsername());

        ClientResponse response = clientMapper.mapToResponse(client);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerClient(@Valid @RequestBody ClientRequest request) {
        if (clientRepository.findByName(request.getName()) != null) {
            return ResponseEntity.badRequest().body("Error: Username is already taken!");
        }

        if (clientRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity.badRequest().body("Error: Email is already in use!");
        }

        Client client = createClientUseCase.execute(
                request.getName(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword())
        );

        return ResponseEntity.ok(clientMapper.mapToResponse(client));
    }
}
