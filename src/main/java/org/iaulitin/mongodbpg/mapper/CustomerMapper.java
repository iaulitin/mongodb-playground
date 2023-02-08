package org.iaulitin.mongodbpg.mapper;

import org.iaulitin.mongodbpg.dto.CustomerCreateRequest;
import org.iaulitin.mongodbpg.dto.CustomerResponse;
import org.iaulitin.mongodbpg.dto.CustomerUpdateRequest;
import org.iaulitin.mongodbpg.entity.Customer;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import static org.iaulitin.mongodbpg.common.Constants.SPRING_COMPONENT_MODEL;

// https://www.baeldung.com/mapstruct-ignore-unmapped-properties
@Mapper(componentModel = SPRING_COMPONENT_MODEL, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerMapper {

    Customer toCreateEntity(CustomerCreateRequest customerCreateRequest);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "additionalInfo", source = "additionalInfo")
    Customer toUpdateEntity(CustomerUpdateRequest customerUpdateRequest);

    CustomerResponse toDto(Customer customer);
}

