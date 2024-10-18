package org.iaulitin.mongodbpg.controller;

import lombok.RequiredArgsConstructor;
import org.iaulitin.mongodbpg.dto.CategoryDto;
import org.iaulitin.mongodbpg.dto.CreateCategoryRequest;
import org.iaulitin.mongodbpg.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController extends BaseController {
    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryDto> create(@RequestBody CreateCategoryRequest createCategoryRequest) {
        CategoryDto categoryDto = categoryService.createCategory(createCategoryRequest);
        return ResponseEntity.ok(categoryDto);
    }

}
