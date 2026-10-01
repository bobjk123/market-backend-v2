package com.tecnm.merida.market_backend_v2.persistence;

import com.tecnm.merida.market_backend_v2.persistence.crud.ProductoCrudRepository;
import com.tecnm.merida.market_backend_v2.persistence.entity.Producto;

import java.util.List;

public class ProductoRepository {

    private ProductoCrudRepository productoCrudRepository;

    //SELECT * FROM productos
    public List<Producto> getALL(){
        //Vamos a "castear"
        return (List<Producto>) productoCrudRepository.findAll();
    }
}
