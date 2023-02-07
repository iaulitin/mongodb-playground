package org.iaulitin.mongodbpg.dto;

import lombok.Data;

@Data
public class CustomerCreateRequest {

    private String firstName;
    private String lastName;
    private CustomerAdditionalInfoDto additionalInfo;
}
