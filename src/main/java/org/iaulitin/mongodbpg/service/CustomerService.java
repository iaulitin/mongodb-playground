package org.iaulitin.mongodbpg.service;

import lombok.RequiredArgsConstructor;
import org.iaulitin.mongodbpg.common.exceptions.ResourceNotFoundException;
import org.iaulitin.mongodbpg.dao.CustomerRepository;
import org.iaulitin.mongodbpg.dto.CustomerCreateRequest;
import org.iaulitin.mongodbpg.dto.CustomerResponse;
import org.iaulitin.mongodbpg.dto.CustomerUpdateRequest;
import org.iaulitin.mongodbpg.entity.Customer;
import org.iaulitin.mongodbpg.mapper.CustomerMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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

    public CustomerResponse getCustomer(UUID uuid) {
        return customerRepository
                .findById(uuid)
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
        Customer customer = customerMapper.toCreateEntity(customerCreateRequest);
        customerRepository.save(customer);
        return customerMapper.toDto(customer);
    }

    public CustomerResponse updateCustomer(UUID uuid, CustomerUpdateRequest customerUpdateRequest) {
        Customer customer = customerRepository.findById(uuid)
                .orElseThrow(ResourceNotFoundException::new);

        customer.setAdditionalInfo(customerMapper.toUpdateEntity(customerUpdateRequest).getAdditionalInfo());
        customerRepository.save(customer);

        return customerMapper.toDto(customer);
    }
}