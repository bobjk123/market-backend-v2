package com.tecnm.merida.market_backend_v2.web.controller;

import com.tecnm.merida.market_backend_v2.domain.Purchase;
import com.tecnm.merida.market_backend_v2.domain.service.PurchaseService;
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
@RequestMapping("/purchases")
@Tag(name = "Purchase", description = "Manage purchases and master-detail orders")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @GetMapping("")
    @Operation(summary = "Get all purchases", description = "Returns a list of all registered purchases")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of purchases")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<Purchase>> getAll() {
        return new ResponseEntity<>(purchaseService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/client/{clientId}")
    @Operation(summary = "Get purchases by client ID", description = "Returns purchases made by a specific client")
    @ApiResponse(responseCode = "200", description = "Purchases found for the client")
    @ApiResponse(responseCode = "404", description = "No purchases found for the client")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<Purchase>> getByClient(
            @Parameter(description = "Client ID", example = "CLI-001", required = true)
            @PathVariable("clientId") String clientId) {
        return purchaseService.getByClient(clientId)
                .filter(purchases -> !purchases.isEmpty())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("")
    @Operation(
            summary = "Create a new purchase (Master-Detail)",
            description = "Registers a purchase along with its items in a single transactional operation",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            examples = @ExampleObject(
                                    value = """
                                            {
                                              "clientId": "CLI-001",
                                              "paymentMethod": "TARJETA",
                                              "comment": "Entregar por la tarde",
                                              "items": [
                                                {
                                                  "id": 1,
                                                  "quantity": 2,
                                                  "total": 500.0,
                                                  "active": true
                                                },
                                                {
                                                  "id": 2,
                                                  "quantity": 1,
                                                  "total": 150.0,
                                                  "active": true
                                                }
                                              ]
                                            }
                                            """
                            )
                    )
            )
    )
    @ApiResponse(responseCode = "201", description = "Purchase created successfully with cascade details")
    @ApiResponse(responseCode = "400", description = "Invalid purchase payload")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<Purchase> save(@RequestBody Purchase purchase) {
        return new ResponseEntity<>(purchaseService.save(purchase), HttpStatus.CREATED);
    }
}
