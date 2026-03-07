package com.example.Bai5.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Bai5.model.Product;
import com.example.Bai5.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    public List<Product> getAll(){
        return repo.findAll();
    }

    public Product getById(Long id){
        return repo.findById(id).orElse(null);
    }

    public Product save(Product p){
        return repo.save(p);
    }

    public void delete(Long id){
        repo.deleteById(id);
    }
}