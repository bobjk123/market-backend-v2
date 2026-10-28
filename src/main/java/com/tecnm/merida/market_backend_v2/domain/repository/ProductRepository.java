package com.tecnm.merida.market_backend_v2.domain.repository;

import com.tecnm.merida.market_backend_v2.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    List<Product> getAll();
    Optional<List<Product>> getByCategory(int categoryId);
    Optional<List<Product>> getScarceProducts (int quantity);
    Optional<Product> getProduct (int productId);
    Product saves(Product product);
    void delete(int productId);
}
