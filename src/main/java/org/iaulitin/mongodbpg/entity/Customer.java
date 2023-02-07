package org.iaulitin.mongodbpg.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "customers")
public class Customer {

    @Id
    private UUID uuid;
    private String firstName;
    private String lastName;
    private CustomerAdditionalInfo additionalInfo;
}