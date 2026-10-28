package com.tecnm.merida.market_backend_v2.domain.repository;

import com.tecnm.merida.market_backend_v2.domain.Purchase;
import com.tecnm.merida.market_backend_v2.persistence.entity.Compra;

import java.util.List;
import java.util.Optional;

public interface PurchaseRepository {

    List<Purchase> getAll();
    Optional<List<Purchase>> getByClient(String clientId);
    Purchase save(Purchase purchase);
}
