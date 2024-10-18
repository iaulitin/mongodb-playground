package org.iaulitin.mongodbpg.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class CreateSkuRequest {
    private String name;
    private String description;
    private List<String> categoriesIds;
    private List<String> subCategoriesIds;
}
