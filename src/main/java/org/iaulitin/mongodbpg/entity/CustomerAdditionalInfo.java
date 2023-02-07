package org.iaulitin.mongodbpg.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerAdditionalInfo {
    private String address;
    private String email;
    private String phone;
}
