package org.iaulitin.mongodbpg.service;

import lombok.RequiredArgsConstructor;
import org.iaulitin.mongodbpg.common.ResourceType;
import org.iaulitin.mongodbpg.common.exceptions.ResourceNotFoundException;
import org.iaulitin.mongodbpg.common.exceptions.ServiceException;
import org.iaulitin.mongodbpg.dao.CategoryRepository;
import org.iaulitin.mongodbpg.dao.SkuRepository;
import org.iaulitin.mongodbpg.dao.SubCategoryRepository;
import org.iaulitin.mongodbpg.dto.CreateSkuBulkRequest;
import org.iaulitin.mongodbpg.dto.CreateSkuRequest;
import org.iaulitin.mongodbpg.dto.SkuDto;
import org.iaulitin.mongodbpg.entity.CategoryEntity;
import org.iaulitin.mongodbpg.entity.SkuEntity;
import org.iaulitin.mongodbpg.entity.SubCategoryEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SkuService {

    private final CategoryRepository categoryRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final SkuRepository skuRepository;

    public SkuDto createSku(CreateSkuRequest createSkuRequest) throws ServiceException {
        List<CategoryEntity> categoryEntities = categoryRepository.findAllById(createSkuRequest.getCategoriesIds());
        if (categoryEntities.size() < createSkuRequest.getCategoriesIds().size()) {
            throw new ResourceNotFoundException(ResourceType.CATEGORY, "TBD"); // TODO improve
        }
        List<SubCategoryEntity> subCategoryEntities = subCategoryRepository.findAllById(createSkuRequest.getSubCategoriesIds());
        if (subCategoryEntities.size() < createSkuRequest.getSubCategoriesIds().size()) {
            throw new ResourceNotFoundException(ResourceType.SUB_CATEGORY, "TBD"); // TODO improve
        }

        SkuEntity skuEntity = SkuEntity.builder()
                .name(createSkuRequest.getName())
                .description(createSkuRequest.getDescription())
                .categories(categoryEntities)
                .subCategories(subCategoryEntities)
                .build();

        skuEntity = skuRepository.save(skuEntity);

        return SkuDto.builder()
                .id(skuEntity.getId())
                .name(skuEntity.getId())
                .description(skuEntity.getDescription())
                .categoriesIds(skuEntity.getCategories().stream().map(CategoryEntity::getId).toList())
                .subCategoriesIds(skuEntity.getSubCategories().stream().map(SubCategoryEntity::getId).toList())
                .build();
    }

    // see https://docs.spring.io/spring-data/mongodb/reference/mongodb/client-session-transactions.html
    @Transactional(rollbackFor = Throwable.class, label = "mongo:writeConcern=MAJORITY")
    public List<SkuDto> createSkuBulk(CreateSkuBulkRequest createSkuBulkRequest) throws ServiceException {
        List<SkuDto> list = new ArrayList<>();
        for (CreateSkuRequest createSkuRequest : createSkuBulkRequest.getSkus()) {
            // TODO bad way to do it
            SkuDto sku = createSku(createSkuRequest);
            list.add(sku);
        }
        return list;
    }
}
