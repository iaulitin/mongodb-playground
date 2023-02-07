package org.iaulitin.mongodbpg.mapper;

import org.iaulitin.mongodbpg.dto.CustomerCreateRequest;
import org.iaulitin.mongodbpg.dto.CustomerResponse;
import org.iaulitin.mongodbpg.dto.CustomerUpdateRequest;
import org.iaulitin.mongodbpg.entity.Customer;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    Customer toEntityCreate(CustomerCreateRequest customerCreateRequest);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "additionalInfo", source = "additionalInfo")
    Customer toEntityUpdate(CustomerUpdateRequest customerUpdateRequest);

    CustomerResponse toDto(Customer customer);
}

