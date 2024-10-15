package org.iaulitin.mongodbpg.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerResponse {

    private String id;
    private String firstName;
    private String lastName;
    private CustomerAdditionalInfoDto additionalInfo;
}
