package com.dennis.product_management_system.dao;

import com.dennis.product_management_system.model.Product;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ProductDaoAccessService implements ProductDao{
    private static List<Product> products;

    static {
        products = new ArrayList<Product>();
        for (int i = 1; i <= 10; i++) {
            Product product = new Product(
                    UUID.randomUUID(),  // Unique ID
                    "Product " + i,     // Name
                    "Description for product " + i,  // Description
                    BigDecimal.valueOf(i * 10.99),  // Price
                    100 + i,           // Quantity
                    "Category " + i,   // Category
                    "Brand " + i,      // Brand
                    BigDecimal.valueOf(i * 0.5),  // Weight
                    "10x20x30",        // Dimensions
                    "http://example.com/product" + i + ".jpg",  // Image URL
                    LocalDateTime.now(), // Created Date
                    LocalDateTime.now()  // Modified Date
            );
            products.add(product);
        }

    }
    @Override
    public List<Product> getAllProducts() {
        return products;
    }

    @Override
    public Optional<Product> getProductById(UUID id) {
        return products.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();
    }

    @Override
    public Product addProduct(Product product) {
        products.add(product);
        return product;
    }

    @Override
    public Product updateProduct(UUID id, Product updatedProduct) {
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            if (product.getId().equals(id)) {
                updatedProduct.setId(id);
                products.set(i, updatedProduct);
                return updatedProduct;
            }
        }
        return null;
    }

    @Override
    public boolean deleteProduct(UUID id) {
        return products.removeIf(product -> product.getId().equals(id));
    }
}
