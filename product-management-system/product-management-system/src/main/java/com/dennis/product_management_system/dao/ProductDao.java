package com.dennis.product_management_system.dao;

import com.dennis.product_management_system.model.Product;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface ProductDao {
    List<Product> getAllProducts();
    Optional<Product> getProductById(Long id);
    Product addProduct(Product product);
    Product updateProduct(Long id, Product UpdatedProduct);
    boolean deleteProduct(Long id);
    List<Product> searchProducts(String searchTerm);
    Page<Product> getProductsPaginated(int page, int size, String sortField, String sortDirection);
}
