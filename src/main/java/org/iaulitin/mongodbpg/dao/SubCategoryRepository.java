package org.iaulitin.mongodbpg.dao;

import org.iaulitin.mongodbpg.entity.SubCategoryEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubCategoryRepository extends MongoRepository<SubCategoryEntity, String> {

}

