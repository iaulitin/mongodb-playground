package org.iaulitin.mongodbpg.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubCategoryDto {
    private String id;
    private String name;
    private String description;
    private String categoryId;
}
