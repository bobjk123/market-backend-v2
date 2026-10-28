package com.tecnm.merida.market_backend_v2.persistence;

import com.tecnm.merida.market_backend_v2.domain.Purchase;
import com.tecnm.merida.market_backend_v2.domain.repository.PurchaseRepository;
import com.tecnm.merida.market_backend_v2.persistence.crud.CompraCrudRepository;
import com.tecnm.merida.market_backend_v2.persistence.entity.Compra;
import com.tecnm.merida.market_backend_v2.persistence.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CompraRepository implements PurchaseRepository {

    @Autowired
    private CompraCrudRepository compraCrudRepository;

    private PurchaseMapper mapper;

    @Override
    public List<Purchase> getAll() {
        return mapper.toPurchases((List<Compra>) compraCrudRepository.findAll());
    }

    @Override
    public Optional<List<Purchase>> getByClient(String clientId) {
        return compraCrudRepository.findByIdCliente(clientId)
                .map(mapper::toPurchases);
    }

    @Override
    public Purchase save(Purchase purchase) {
        Compra compra = mapper.toCompra(purchase);
        compra.getProductos().forEach(producto -> producto.setCompra(compra));
        return mapper.toPurchase(compraCrudRepository.save(compra));
    }
}
