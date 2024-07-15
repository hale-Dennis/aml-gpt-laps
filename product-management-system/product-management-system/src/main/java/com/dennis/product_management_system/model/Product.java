package com.dennis.product_management_system.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;


@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Float price;
    private Integer quantity;
    private String brand;
    private BigDecimal weight;
    private String dimensions;
    private String imageUrl;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Product(Category category) {
        this.category = category;
    }

    public Product(Long id, String name, String description, Float price, Integer quantity, String brand, BigDecimal weight, String dimensions, String imageUrl, LocalDateTime createdDate, LocalDateTime modifiedDate, Category category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.brand = brand;
        this.weight = weight;
        this.dimensions = dimensions;
        this.imageUrl = imageUrl;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.category = category;
    }

    public Product() {

    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(name, product.name) && Objects.equals(description, product.description) && Objects.equals(price, product.price) && Objects.equals(quantity, product.quantity) && Objects.equals(brand, product.brand) && Objects.equals(weight, product.weight) && Objects.equals(dimensions, product.dimensions) && Objects.equals(imageUrl, product.imageUrl) && Objects.equals(createdDate, product.createdDate) && Objects.equals(modifiedDate, product.modifiedDate) && Objects.equals(category, product.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, price, quantity, brand, weight, dimensions, imageUrl, createdDate, modifiedDate, category);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", category='" + category + '\'' +
                ", brand='" + brand + '\'' +
                ", weight=" + weight +
                ", dimensions='" + dimensions + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", createdDate=" + createdDate +
                ", modifiedDate=" + modifiedDate +
                '}';
    }
}
