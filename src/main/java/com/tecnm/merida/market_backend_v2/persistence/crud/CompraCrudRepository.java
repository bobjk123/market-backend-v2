package com.tecnm.merida.market_backend_v2.persistence.crud;

import com.tecnm.merida.market_backend_v2.persistence.entity.Compra;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CompraCrudRepository extends CrudRepository<Compra, Integer> {

    Optional<List<Compra>> findByIdCliente(String idCliente);
}
