package com.example.Bai3.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.example.Bai3.model.Product;
import com.example.Bai3.service.ProductService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("title", "Welcome");
        return "index";
    }

    // Read - Danh sách sản phẩm
    @GetMapping("products")
    public String listProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "products";
    }

    // Create - Form tạo sản phẩm mới
    @GetMapping("products/add")
    public String showAddForm(Model model) {
        model.addAttribute("product", new Product());
        return "add-product";
    }

    // Create - Lưu sản phẩm mới
    @PostMapping("products/add")
    public String addProduct(@Valid @ModelAttribute Product product, BindingResult result,
                             @org.springframework.web.bind.annotation.RequestParam("imageFile") MultipartFile imageFile) {
        if (result.hasErrors()) {
            return "add-product";
        }

        // Xử lý file nếu có
        if (imageFile != null && !imageFile.isEmpty()) {
            try {
                String filename = storeFile(imageFile);
                product.setImageName(filename);
            } catch (IOException e) {
                result.rejectValue("imageName", "upload.error", "Không thể lưu file ảnh");
                return "add-product";
            }
        }

        productService.addProduct(product);
        return "redirect:/products";
    }

    // Update - Form chỉnh sửa
    @GetMapping("products/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        Product product = productService.getProductById(id);
        if (product == null) {
            return "redirect:/products";
        }
        model.addAttribute("product", product);
        return "edit-product";
    }

    // Update - Lưu chỉnh sửa
    @PostMapping("products/edit/{id}")
    public String editProduct(@PathVariable String id, @Valid @ModelAttribute Product product, BindingResult result,
                              @org.springframework.web.bind.annotation.RequestParam("imageFile") MultipartFile imageFile) {
        if (result.hasErrors()) {
            product.setId(id);
            return "edit-product";
        }

        if (imageFile != null && !imageFile.isEmpty()) {
            try {
                String filename = storeFile(imageFile);
                product.setImageName(filename);
            } catch (IOException e) {
                result.rejectValue("imageName", "upload.error", "Không thể lưu file ảnh");
                product.setId(id);
                return "edit-product";
            }
        }

        product.setId(id);
        productService.updateProduct(product);
        return "redirect:/products";
    }

    // --- Helper to store file to local uploads folder ---
    private String storeFile(MultipartFile file) throws IOException {
        String uploadsDir = System.getProperty("user.dir") + File.separator + "uploads";
        Path uploadsPath = Paths.get(uploadsDir);
        if (!Files.exists(uploadsPath)) {
            Files.createDirectories(uploadsPath);
        }

        String original = StringUtils.cleanPath(file.getOriginalFilename());
        String ext = "";
        int dot = original.lastIndexOf('.');
        if (dot >= 0) ext = original.substring(dot);
        String filename = UUID.randomUUID().toString() + ext;
        Path target = uploadsPath.resolve(filename);
        Files.copy(file.getInputStream(), target);
        return filename;
    }

    // Delete
    @GetMapping("products/delete/{id}")
    public String deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }
}
