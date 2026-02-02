package com.example.Bai3.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Bai3.model.Product;

@Service
public class ProductService {
    private List<Product> products = new ArrayList<>();
    private int idCounter = 1;

    public ProductService() {
        // Khởi tạo dữ liệu mẫu
        Product p1 = new Product();
        p1.setId("1");
        p1.setName("Laptop Dell");
        p1.setPrice(15000000.0);
        p1.setCategory("Điện tử");
        products.add(p1);

        Product p2 = new Product();
        p2.setId("2");
        p2.setName("iPhone 15");
        p2.setPrice(25000000.0);
        p2.setCategory("Điện tử");
        products.add(p2);
        
        idCounter = 3;
    }

    // Read all
    public List<Product> getAllProducts() {
        return new ArrayList<>(products);
    }

    // Read by id
    public Product getProductById(String id) {
        return products.stream()
            .filter(p -> p.getId().equals(id))
            .findFirst()
            .orElse(null);
    }

    // Create
    public void addProduct(Product product) {
        if (product.getId() == null || product.getId().isEmpty()) {
            product.setId(String.valueOf(idCounter++));
        }
        products.add(product);
    }

    // Update
    public void updateProduct(Product product) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId().equals(product.getId())) {
                products.set(i, product);
                return;
            }
        }
    }

    // Delete
    public void deleteProduct(String id) {
        products.removeIf(p -> p.getId().equals(id));
    }
}
