package org.iaulitin.mongodbpg.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class CustomerResponse {

    private UUID uuid;
    private String firstName;
    private String lastName;
    private CustomerAdditionalInfoDto additionalInfo;
}
