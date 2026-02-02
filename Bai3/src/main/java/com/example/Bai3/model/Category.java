package com.example.Bai3.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    private String id;

    @NotBlank(message = "Tên danh mục không được để trống")
    private String name;

    private String description;
}
