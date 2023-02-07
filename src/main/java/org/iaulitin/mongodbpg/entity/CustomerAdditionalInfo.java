package org.iaulitin.mongodbpg.entity;

import lombok.Data;

@Data
public class CustomerAdditionalInfo {
    private String address;
    private String email;
    private String phone;
}
