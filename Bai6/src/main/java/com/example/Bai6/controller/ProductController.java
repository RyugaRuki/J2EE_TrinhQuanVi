package com.example.Bai6.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.Bai6.model.Product;
import com.example.Bai6.repository.CategoryRepository;
import com.example.Bai6.service.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;
    private final CategoryRepository categoryRepo;

    @Value("${upload.path}")
    private String uploadPath;

    public ProductController(ProductService service,
                             CategoryRepository categoryRepo) {
        this.service = service;
        this.categoryRepo = categoryRepo;
    }

    @GetMapping
    public String list(Model model){
        model.addAttribute("products", service.getAll());
        return "products";
    }

    @GetMapping("/create")
    public String createForm(Model model){
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryRepo.findAll());
        return "product-form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Product product,
                    @RequestParam(value = "file", required = false) MultipartFile file,
                    @RequestParam(value = "category.id", required = false) Long categoryId)
            throws IOException {

        if (categoryId != null) {
            var category = categoryRepo.findById(categoryId).orElse(null);
            product.setCategory(category);
        }

        if(file != null && !file.isEmpty()){

            File dir = new File(uploadPath);
            if(!dir.exists()) dir.mkdirs();

            String fileName = file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + fileName));

            product.setImage(fileName);

        } else {
            Product old = service.getById(product.getId());
            product.setImage(old.getImage());
        }

        service.save(product);

        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model){

        Product product = service.getById(id);
        if (product == null) {
            return "redirect:/products";
        }

        model.addAttribute("product", product);
        model.addAttribute("categories", categoryRepo.findAll());

        return "product-form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){

        Product product = service.getById(id);
        if (product != null) {
            service.delete(id);
        }

        return "redirect:/products";
    }

}