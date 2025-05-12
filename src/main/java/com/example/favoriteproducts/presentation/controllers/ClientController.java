package com.example.favoriteproducts.presentation.controllers;

import com.example.favoriteproducts.core.entities.Client;
import com.example.favoriteproducts.core.usecases.client.CreateClientUseCase;
import com.example.favoriteproducts.presentation.dto.mappers.ClientMapper;
import com.example.favoriteproducts.presentation.dto.request.ClientRequest;
import com.example.favoriteproducts.presentation.dto.response.ClientResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final CreateClientUseCase createClientUseCase;
    private final ClientMapper clientMapper;

    public ClientController(
            CreateClientUseCase createClientUseCase,
            ClientMapper clientMapper
    ) {
        this.createClientUseCase = createClientUseCase;
        this.clientMapper = clientMapper;
    }

    @PostMapping
    public ResponseEntity<ClientResponse> createClient(@Valid @RequestBody ClientRequest request) {
        Client client = createClientUseCase.execute(request.getName(), request.getEmail());
        return new ResponseEntity<>(clientMapper.mapToResponse(client), HttpStatus.CREATED);
    }
}
