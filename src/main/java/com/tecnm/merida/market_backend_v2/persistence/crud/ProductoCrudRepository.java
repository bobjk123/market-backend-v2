package com.tecnm.merida.market_backend_v2.persistence.crud;

import com.tecnm.merida.market_backend_v2.persistence.entity.Producto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

//Métodos abstractos que después se implementaran
public interface ProductoCrudRepository extends CrudRepository<Producto, Integer> {

    /*SQL Query
    SELECT *
    FROM productos
    WHERE id_categoria = 10?
    ORDER BY nombre ASC
     */
    List<Producto> findByIdCategoriaOrderByNombreAsc(int idCategoria);

    //Cantidad stock
    Optional<List<Producto>> findByCantidadStockLessThanAndEstado(int cantidadStock, boolean estado);

}
