package org.iaulitin.mongodbpg.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.iaulitin.mongodbpg.common.exceptions.ResourceNotFoundException;
import org.iaulitin.mongodbpg.dto.CreateSubCategoryRequest;
import org.iaulitin.mongodbpg.dto.SubCategoryDto;
import org.iaulitin.mongodbpg.service.SubCategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/sub-categories")
public class SubCategoryController extends BaseController {
    private final SubCategoryService subCategoryService;

    @PostMapping
    public ResponseEntity<SubCategoryDto> create(@RequestBody CreateSubCategoryRequest createSubCategoryRequest) throws ResourceNotFoundException {
        SubCategoryDto subCategoryDto = subCategoryService.createSubCategory(createSubCategoryRequest);
        return ResponseEntity.ok(subCategoryDto);
    }
}
