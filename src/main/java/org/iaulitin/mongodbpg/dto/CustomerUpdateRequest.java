package org.iaulitin.mongodbpg.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class CustomerUpdateRequest {

    private UUID uuid;
    private String firstName;
    private String lastName;
    private CustomerAdditionalInfoDto additionalInfo;
}
