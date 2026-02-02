package com.example.Bai3.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Bai3.model.Category;

@Service
public class CategoryService {
    private List<Category> categories = new ArrayList<>();
    private int idCounter = 1;

    public CategoryService() {
        // Khởi tạo dữ liệu mẫu
        Category c1 = new Category();
        c1.setId("1");
        c1.setName("Điện tử");
        c1.setDescription("Các sản phẩm điện tử");
        categories.add(c1);

        Category c2 = new Category();
        c2.setId("2");
        c2.setName("Thời trang");
        c2.setDescription("Quần áo, giày dép");
        categories.add(c2);

        Category c3 = new Category();
        c3.setId("3");
        c3.setName("Sách");
        c3.setDescription("Sách và tạp chí");
        categories.add(c3);
        
        idCounter = 4;
    }

    // Read all
    public List<Category> getAllCategories() {
        return new ArrayList<>(categories);
    }

    // Read by id
    public Category getCategoryById(String id) {
        return categories.stream()
            .filter(c -> c.getId().equals(id))
            .findFirst()
            .orElse(null);
    }

    // Create
    public void addCategory(Category category) {
        if (category.getId() == null || category.getId().isEmpty()) {
            category.setId(String.valueOf(idCounter++));
        }
        categories.add(category);
    }

    // Update
    public void updateCategory(Category category) {
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).getId().equals(category.getId())) {
                categories.set(i, category);
                return;
            }
        }
    }

    // Delete
    public void deleteCategory(String id) {
        categories.removeIf(c -> c.getId().equals(id));
    }
}
