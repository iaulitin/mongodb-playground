package org.iaulitin.mongodbpg.service;

import lombok.RequiredArgsConstructor;
import org.iaulitin.mongodbpg.common.exceptions.ResourceNotFoundException;
import org.iaulitin.mongodbpg.dao.CustomerRepository;
import org.iaulitin.mongodbpg.dto.CustomerCreateRequest;
import org.iaulitin.mongodbpg.dto.CustomerResponse;
import org.iaulitin.mongodbpg.dto.CustomerUpdateRequest;
import org.iaulitin.mongodbpg.entity.CustomerEntity;
import org.iaulitin.mongodbpg.mapper.CustomerMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Customer service is used to provide operations over Customer DTO and Entity representations.
 */
@Service
@RequiredArgsConstructor
public class CustomerService {

    private static final String FIRST_NAME_PROPERTY_NAME = "firstName";
    private static final String LAST_NAME_PROPERTY_NAME = "lastName";

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerResponse getCustomer(String id) {
        return customerRepository
                .findById(id)
                .map(customerMapper::toDto)
                .orElseThrow(ResourceNotFoundException::new);
    }

    public List<CustomerResponse> getCustomers(int page, int size) {
        Pageable paging = PageRequest.of(page, size,
                Sort.by(Sort.DEFAULT_DIRECTION, LAST_NAME_PROPERTY_NAME, FIRST_NAME_PROPERTY_NAME));

        return customerRepository.findAll(paging).stream()
                .map(customerMapper::toDto)
                .toList();
    }

    /**
     * Provides a list of customers with pagination applying a filter by first name.
     * If a customer's full name is strictly equal to the requested string, it will be returned.
     *
     * @param page page number, starting from 0
     * @param size size of a single page
     * @return List of customers stored in the DB
     */
    public List<CustomerResponse> findCustomers(String firstName, int page, int size) {
        Pageable paging = PageRequest.of(page, size,
                Sort.by(Sort.DEFAULT_DIRECTION, LAST_NAME_PROPERTY_NAME, FIRST_NAME_PROPERTY_NAME));

        return customerRepository.findByFirstName(firstName, paging).stream()
                .map(customerMapper::toDto)
                .toList();
    }

    public CustomerResponse createCustomer(CustomerCreateRequest customerCreateRequest) {
        CustomerEntity customerEntity = customerMapper.toCreateEntity(customerCreateRequest);
        customerRepository.save(customerEntity);
        return customerMapper.toDto(customerEntity);
    }

    public CustomerResponse updateCustomer(String id, CustomerUpdateRequest customerUpdateRequest) {
        CustomerEntity customerEntity = customerRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);

        customerEntity.setAdditionalInfo(customerMapper.toUpdateEntity(customerUpdateRequest).getAdditionalInfo());
        customerRepository.save(customerEntity);

        return customerMapper.toDto(customerEntity);
    }
}