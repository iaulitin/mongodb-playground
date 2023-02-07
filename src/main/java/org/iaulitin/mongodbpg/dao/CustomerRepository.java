package org.iaulitin.mongodbpg.dao;

import org.iaulitin.mongodbpg.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface CustomerRepository extends MongoRepository<Customer, UUID> {

    Page<Customer> findAll(Pageable pageable);

    List<Customer> findByFirstName(String firstName, Pageable pageable);

}