package org.iaulitin.mongodbpg.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SkuDto {
    private String id;
    private String name;
    private String description;
    private List<String> categoriesIds;
    private List<String> subCategoriesIds;
}
