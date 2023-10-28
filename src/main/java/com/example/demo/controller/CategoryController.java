package com.example.demo.controller;

import com.example.demo.model.book.Category;
import com.example.demo.response.ApiResponse;
import com.example.demo.response.Code;
import com.example.demo.response.Status;
import com.example.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/categories")

public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ApiResponse<UUID> addNewCategory(@RequestBody Category category) {
        return new ApiResponse<>(Code.OK, Status.OK, categoryService.addNewCategory(category));
    }

    @GetMapping
    public ApiResponse<List<Category>> getAllCategory() {
        return new ApiResponse<>(Code.OK, Status.OK, categoryService.getAllCategories());
    }

    @GetMapping("/{id}")
    public ApiResponse<Optional<Category>> getCategoryById(@PathVariable UUID id) {
        return new ApiResponse<>(Code.OK, Status.OK, categoryService.getCategoryById(id));
    }
}
