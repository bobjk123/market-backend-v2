package com.tecnm.merida.market_backend_v2.persistence.crud;

import com.tecnm.merida.market_backend_v2.persistence.entity.Categoria;
import org.springframework.data.repository.CrudRepository;

public interface CategoriaCrudRepository extends CrudRepository<Categoria, Integer> {
}
