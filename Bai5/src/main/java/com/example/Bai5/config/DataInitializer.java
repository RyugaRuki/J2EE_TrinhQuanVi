package com.example.Bai5.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.Bai5.model.Category;
import com.example.Bai5.repository.CategoryRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    private final CategoryRepository categoryRepo;

    public DataInitializer(CategoryRepository categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        // Initialize default categories if they don't exist
        if (categoryRepo.count() == 0) {
            categoryRepo.save(new Category(null, "Electronics"));
            categoryRepo.save(new Category(null, "Books"));
            categoryRepo.save(new Category(null, "Clothing"));
            categoryRepo.save(new Category(null, "Food"));
            categoryRepo.save(new Category(null, "Sports"));
        }
    }
}
