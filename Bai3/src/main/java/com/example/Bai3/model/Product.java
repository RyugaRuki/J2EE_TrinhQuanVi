package com.example.Bai3.model;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    // Thay đổi từ int sang String để khớp với Service bạn đã viết
    private String id;

    @NotBlank(message = "Tên sản phẩm không được để trống")
    private String name;

    @NotNull(message = "Giá sản phẩm không được để trống")
    @DecimalMin(value = "1.0", inclusive = true, message = "Giá sản phẩm không được nhỏ hơn 1")
    @DecimalMax(value = "9999999.0", message = "Giá sản phẩm không được vượt quá 9,999,999")
    private Double price;

    // Tên file hình ảnh - khớp với các template
    @Size(max = 200, message = "Tên hình ảnh không quá 200 kí tự")
    private String imageName;

    private String category;
}