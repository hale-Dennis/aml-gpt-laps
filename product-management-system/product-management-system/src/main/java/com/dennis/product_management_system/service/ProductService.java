package com.dennis.product_management_system.service;


import com.dennis.product_management_system.dao.ProductDao;
import com.dennis.product_management_system.exception.ResourceNotFound;
import com.dennis.product_management_system.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    private final ProductDao productDao;
    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public List<Product> getAllProducts() {
        return productDao.getAllProducts();
    }

    public Product getProductById(UUID id) {
        return productDao.getProductById(id)
                .orElseThrow(() -> new ResourceNotFound("customer not found"));
    }

    public Product addProduct(Product product) {
        return productDao.addProduct(product);
    }

    public Product updateProduct(UUID id, Product product) {
        return productDao.updateProduct(id, product);
    }

    public boolean deleteProduct(UUID id) {
        return productDao.deleteProduct(id);
    }
}
