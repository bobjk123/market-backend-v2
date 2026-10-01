package com.tecnm.merida.market_backend_v2.persistence.crud;

import com.tecnm.merida.market_backend_v2.persistence.entity.Producto;
import org.springframework.data.repository.CrudRepository;

public interface ProductoCrudRepository extends CrudRepository<Producto, Integer> {
}
