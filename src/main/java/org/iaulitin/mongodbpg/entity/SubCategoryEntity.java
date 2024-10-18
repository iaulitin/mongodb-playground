package org.iaulitin.mongodbpg.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "sub-categories")
public class SubCategoryEntity {

    @Id
    private String id;
    @NonNull
    private String name;
    @NonNull
    private String description;
    @DBRef(lazy = true)
    private CategoryEntity parentCategory;

}
