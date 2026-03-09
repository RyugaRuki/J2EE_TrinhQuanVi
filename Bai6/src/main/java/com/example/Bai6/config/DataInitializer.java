package com.example.Bai6.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.Bai6.model.Category;
import com.example.Bai6.model.User;
import com.example.Bai6.repository.CategoryRepository;
import com.example.Bai6.repository.UserRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    private final CategoryRepository categoryRepo;
    private final UserRepository userRepo;
    private final BCryptPasswordEncoder passwordEncoder;

    public DataInitializer(CategoryRepository categoryRepo, UserRepository userRepo, BCryptPasswordEncoder passwordEncoder) {
        this.categoryRepo = categoryRepo;
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
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

        // Initialize default users if they don't exist
        if (userRepo.count() == 0) {
            User admin = new User("admin", passwordEncoder.encode("admin"), "ADMIN");
            User user = new User("user", passwordEncoder.encode("user"), "USER");
            userRepo.save(admin);
            userRepo.save(user);
        }
    }
}
