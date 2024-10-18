package org.iaulitin.mongodbpg.service;

import lombok.RequiredArgsConstructor;
import org.iaulitin.mongodbpg.common.ResourceType;
import org.iaulitin.mongodbpg.common.exceptions.ResourceNotFoundException;
import org.iaulitin.mongodbpg.dao.CategoryRepository;
import org.iaulitin.mongodbpg.dao.SubCategoryRepository;
import org.iaulitin.mongodbpg.dto.CreateSubCategoryRequest;
import org.iaulitin.mongodbpg.dto.SubCategoryDto;
import org.iaulitin.mongodbpg.entity.CategoryEntity;
import org.iaulitin.mongodbpg.entity.SubCategoryEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubCategoryService {

    private final CategoryRepository categoryRepository;
    private final SubCategoryRepository subCategoryRepository;

    public SubCategoryDto createSubCategory(CreateSubCategoryRequest createSubCategoryRequest) throws ResourceNotFoundException {
        CategoryEntity categoryEntity = categoryRepository.findById(createSubCategoryRequest.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException(ResourceType.CATEGORY, createSubCategoryRequest.getCategoryId()));

        SubCategoryEntity subCategoryEntity = SubCategoryEntity.builder()
                .name(createSubCategoryRequest.getName())
                .description(createSubCategoryRequest.getDescription())
                .parentCategory(categoryEntity)
                .build();

        subCategoryEntity = subCategoryRepository.save(subCategoryEntity);

        return SubCategoryDto.builder()
                .id(subCategoryEntity.getId())
                .name(subCategoryEntity.getName())
                .description(subCategoryEntity.getDescription())
                .categoryId(categoryEntity.getId())
                .build();
    }
}
