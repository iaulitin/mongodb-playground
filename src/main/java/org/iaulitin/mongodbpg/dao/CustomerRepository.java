package org.iaulitin.mongodbpg.dao;

import org.iaulitin.mongodbpg.entity.CustomerEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CustomerRepository extends MongoRepository<CustomerEntity, String> {

    List<CustomerEntity> findByFirstName(String firstName, Pageable pageable);

}