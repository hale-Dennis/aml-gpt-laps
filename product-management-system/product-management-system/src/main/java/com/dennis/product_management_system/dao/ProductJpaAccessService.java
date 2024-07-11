package com.dennis.product_management_system.dao;

import com.dennis.product_management_system.model.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ProductJpaAccessService implements ProductDao {

    private final ProductRepository productRepository;

    public ProductJpaAccessService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getProductById(UUID id) {
        return Optional.empty();
    }

    @Override
    public Product addProduct(Product product) {
        return null;
    }

    @Override
    public Product updateProduct(UUID id, Product UpdatedProduct) {
        return null;
    }

    @Override
    public boolean deleteProduct(UUID id) {
        return false;
    }
}
