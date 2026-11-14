package com.tecnm.merida.market_backend_v2.web.controller;

import com.tecnm.merida.market_backend_v2.domain.Client;
import com.tecnm.merida.market_backend_v2.domain.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
@Tag(name = "Client", description = "Manage customers")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("")
    @Operation(summary = "Get all clients", description = "Returns a list of all registered clients")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of clients")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<Client>> getAll() {
        return new ResponseEntity<>(clientService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a client by ID", description = "Returns a client by their ID if found")
    @ApiResponse(responseCode = "200", description = "Client found")
    @ApiResponse(responseCode = "404", description = "Client not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<Client> getClient(
            @Parameter(description = "Client ID", example = "CLI-001", required = true)
            @PathVariable("id") String clientId) {
        return clientService.getClient(clientId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("")
    @Operation(
            summary = "Create a new client",
            description = "Registers a new client and returns it",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            examples = @ExampleObject(
                                    value = """
                                            {
                                              "clientId": "CLI-001",
                                              "name": "Juan",
                                              "lastName": "Pérez",
                                              "cellphone": "9991234567",
                                              "address": "Calle 60 #123, Centro",
                                              "email": "juan.perez@example.com"
                                            }
                                            """
                            )
                    )
            )
    )
    @ApiResponse(responseCode = "201", description = "Client created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid client data")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<Client> save(@RequestBody Client client) {
        return new ResponseEntity<>(clientService.save(client), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update an existing client",
            description = "Updates the client with the given ID and returns the updated client",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            examples = @ExampleObject(
                                    value = """
                                            {
                                              "name": "Juan Carlos",
                                              "lastName": "Pérez Gómez",
                                              "cellphone": "9997654321",
                                              "address": "Calle 60 #123 Depto 4, Centro",
                                              "email": "jc.perez@example.com"
                                            }
                                            """
                            )
                    )
            )
    )
    @ApiResponse(responseCode = "200", description = "Client updated successfully")
    @ApiResponse(responseCode = "404", description = "Client not found")
    @ApiResponse(responseCode = "400", description = "Invalid client data")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<Client> update(
            @Parameter(description = "Client ID to update", example = "CLI-001", required = true)
            @PathVariable("id") String clientId,
            @RequestBody Client client) {
        return clientService.update(clientId, client)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a client by ID", description = "Deletes a client by their ID if found")
    @ApiResponse(responseCode = "200", description = "Client deleted successfully")
    @ApiResponse(responseCode = "404", description = "Client not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<Void> delete(
            @Parameter(description = "Client ID to delete", example = "CLI-001", required = true)
            @PathVariable("id") String clientId) {
        if (clientService.delete(clientId)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
