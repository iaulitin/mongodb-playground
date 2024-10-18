package org.iaulitin.mongodbpg.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "categories")
public class CategoryEntity {

    @Id
    private String id;
    @NonNull
    private String name;
    @NonNull
    private String description;

}
