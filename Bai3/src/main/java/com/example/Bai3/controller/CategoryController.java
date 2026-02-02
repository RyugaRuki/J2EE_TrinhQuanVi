package com.example.Bai3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.Bai3.model.Category;
import com.example.Bai3.service.CategoryService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // Read - Danh sách danh mục
    @GetMapping
    public String listCategories(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "category-list";
    }

    // Create - Form tạo danh mục mới
    @GetMapping("add")
    public String showAddForm(Model model) {
        model.addAttribute("category", new Category());
        return "add-category";
    }

    // Create - Lưu danh mục mới
    @PostMapping("add")
    public String addCategory(@Valid @ModelAttribute Category category, BindingResult result) {
        if (result.hasErrors()) {
            return "add-category";
        }
        categoryService.addCategory(category);
        return "redirect:/categories";
    }

    // Update - Form chỉnh sửa
    @GetMapping("edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        Category category = categoryService.getCategoryById(id);
        if (category == null) {
            return "redirect:/categories";
        }
        model.addAttribute("category", category);
        return "edit-category";
    }

    // Update - Lưu chỉnh sửa
    @PostMapping("edit/{id}")
    public String editCategory(@PathVariable String id, @Valid @ModelAttribute Category category, BindingResult result) {
        if (result.hasErrors()) {
            category.setId(id);
            return "edit-category";
        }
        category.setId(id);
        categoryService.updateCategory(category);
        return "redirect:/categories";
    }

    // Delete
    @GetMapping("delete/{id}")
    public String deleteCategory(@PathVariable String id) {
        categoryService.deleteCategory(id);
        return "redirect:/categories";
    }
}
