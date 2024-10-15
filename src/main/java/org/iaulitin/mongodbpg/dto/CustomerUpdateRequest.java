package org.iaulitin.mongodbpg.dto;

import lombok.Data;

@Data
public class CustomerUpdateRequest {

    private String firstName;
    private String lastName;
    private CustomerAdditionalInfoDto additionalInfo;

}
