package org.iaulitin.mongodbpg.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerAdditionalInfoDto {

    private String address;
    private String email;
    private String phone;

}
