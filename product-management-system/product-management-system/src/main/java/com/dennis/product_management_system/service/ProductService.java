package com.dennis.product_management_system.service;


import com.dennis.product_management_system.dao.ProductDao;
import com.dennis.product_management_system.dao.ProductRepository;
import com.dennis.product_management_system.exception.ResourceNotFound;
import com.dennis.product_management_system.model.Product;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    private final ProductDao productDao;
    private final ProductRepository productRepository;

    public ProductService(@Qualifier("jpa") ProductDao productDao, ProductRepository productRepository) {
        this.productDao = productDao;
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productDao.getAllProducts();
    }

    public Product getProductById(Long id) {
        return productDao.getProductById(id)
                .orElseThrow(() -> new ResourceNotFound("customer not found"));
    }

    public Product addProduct(Product product) {
        return productDao.addProduct(product);
    }

    public Product updateProduct(Long id, Product product) {
        return productDao.updateProduct(id, product);
    }

    public boolean deleteProduct(Long id) {
        return productDao.deleteProduct(id);
    }

    public List<Product> searchProducts(String searchTerm){
        return productDao.searchProducts(searchTerm);
    }

    public Page<Product> getProductsPaginated(int page, int size, String sortField, String sortDirection){
        return productDao.getProductsPaginated(page, size, sortField, sortDirection);
    }
}
