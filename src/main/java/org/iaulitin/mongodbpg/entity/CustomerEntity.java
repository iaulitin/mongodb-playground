package org.iaulitin.mongodbpg.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "customers")
public class CustomerEntity {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private CustomerAdditionalInfo additionalInfo;

}