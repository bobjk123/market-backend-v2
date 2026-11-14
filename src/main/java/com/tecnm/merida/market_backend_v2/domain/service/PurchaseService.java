package com.tecnm.merida.market_backend_v2.domain.service;

import com.tecnm.merida.market_backend_v2.domain.Purchase;
import com.tecnm.merida.market_backend_v2.domain.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    public List<Purchase> getAll() {
        return purchaseRepository.getAll();
    }

    public Optional<List<Purchase>> getByClient(String clientId) {
        return purchaseRepository.getByClient(clientId);
    }

    @Transactional
    public Purchase save(Purchase purchase) {
        if (purchase.getDate() == null) {
            purchase.setDate(LocalDateTime.now());
        }
        if (purchase.getState() == null) {
            purchase.setState("P");
        }
        return purchaseRepository.save(purchase);
    }
}
