package org.iaulitin.mongodbpg.controller;

import lombok.RequiredArgsConstructor;
import org.iaulitin.mongodbpg.dto.CategoryDto;
import org.iaulitin.mongodbpg.dto.CreateCategoryRequest;
import org.iaulitin.mongodbpg.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController extends BaseController {
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> listCategories() {
        List<CategoryDto> categoryDtos = categoryService.listCategories();
        return ResponseEntity.ok(categoryDtos);
    }

    @PostMapping
    public ResponseEntity<CategoryDto> create(@RequestBody CreateCategoryRequest createCategoryRequest) {
        CategoryDto categoryDto = categoryService.createCategory(createCategoryRequest);
        return ResponseEntity.ok(categoryDto);
    }

}
