package org.iaulitin.mongodbpg.service;

import lombok.RequiredArgsConstructor;
import org.iaulitin.mongodbpg.dao.CategoryRepository;
import org.iaulitin.mongodbpg.dto.CategoryDto;
import org.iaulitin.mongodbpg.dto.CreateCategoryRequest;
import org.iaulitin.mongodbpg.entity.CategoryEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<CategoryDto> listCategories() {
        // TODO entity to DTO conversion code duplication
        return categoryRepository.findAll()
                .stream()
                .map(categoryEntity -> CategoryDto.builder()
                        .id(categoryEntity.getId())
                        .name(categoryEntity.getName())
                        .description(categoryEntity.getDescription())
                        .build())
                .toList();
    }

    public CategoryDto createCategory(CreateCategoryRequest createCategoryRequest) {
        CategoryEntity categoryEntity = CategoryEntity.builder()
                .name(createCategoryRequest.getName())
                .description(createCategoryRequest.getDescription())
                .build();

        categoryEntity = categoryRepository.save(categoryEntity);

        return CategoryDto.builder()
                .id(categoryEntity.getId())
                .name(categoryEntity.getName())
                .description(categoryEntity.getDescription())
                .build();
    }
}
