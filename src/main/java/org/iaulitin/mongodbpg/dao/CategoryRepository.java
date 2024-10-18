package org.iaulitin.mongodbpg.dao;

import org.iaulitin.mongodbpg.entity.CategoryEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends MongoRepository<CategoryEntity, String> {

}

