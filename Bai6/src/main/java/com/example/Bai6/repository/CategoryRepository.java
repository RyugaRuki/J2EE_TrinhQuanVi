package com.example.Bai6.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Bai6.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
}