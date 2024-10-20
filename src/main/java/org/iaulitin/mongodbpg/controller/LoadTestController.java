package org.iaulitin.mongodbpg.controller;

import lombok.RequiredArgsConstructor;
import org.iaulitin.mongodbpg.dao.CategoryRepository;
import org.iaulitin.mongodbpg.entity.CategoryEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/load-test")
public class LoadTestController {

    private final CategoryRepository categoryRepository;

    @PostMapping
    public void loadTest() {
        List<CategoryEntity> categoryEntities = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            categoryEntities.add(CategoryEntity.builder()
                    .name("name")
                    .description("description")
                    .build()
            );
        }

        categoryRepository.saveAll(categoryEntities);
    }

}
