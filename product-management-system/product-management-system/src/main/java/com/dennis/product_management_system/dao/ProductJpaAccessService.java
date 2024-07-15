package com.dennis.product_management_system.dao;

import com.dennis.product_management_system.exception.ResourceNotFound;
import com.dennis.product_management_system.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository("jpa")
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
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product updatedProduct) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Product not found"));

        product.setName(updatedProduct.getName());
        product.setDescription(updatedProduct.getDescription());
        product.setPrice(updatedProduct.getPrice());
        product.setQuantity(updatedProduct.getQuantity());
        product.setCategory(updatedProduct.getCategory());
        product.setBrand(updatedProduct.getBrand());
        product.setWeight(updatedProduct.getWeight());
        product.setImageUrl(updatedProduct.getImageUrl());

        productRepository.save(product);
        return productRepository.save(product);
    }

    @Override
    public boolean deleteProduct(Long id) {
        productRepository.deleteById(id);
        return true;
    }

    @Override
    public List<Product> searchProducts(String searchTerm) {
        return productRepository.searchProducts(searchTerm);
    }

    @Override
    public Page<Product> getProductsPaginated(int page, int size, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return productRepository.findAll(pageable);
    }

}
