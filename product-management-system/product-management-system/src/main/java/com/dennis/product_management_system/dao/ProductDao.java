package com.dennis.product_management_system.dao;

import com.dennis.product_management_system.model.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface ProductDao {
    List<Product> getAllProducts();
    Optional<Product> getProductById(UUID id);

    Product addProduct(Product product);
    Product updateProduct(UUID id, Product UpdatedProduct);
    boolean deleteProduct(UUID id);
}
