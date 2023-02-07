package org.iaulitin.mongodbpg.dto;

import lombok.Data;

@Data
public class CustomerAdditionalInfoDto {
    private String address;
    private String email;
    private String phone;
}
