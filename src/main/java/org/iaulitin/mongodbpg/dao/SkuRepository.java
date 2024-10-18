package org.iaulitin.mongodbpg.dao;

import org.iaulitin.mongodbpg.entity.SkuEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkuRepository extends MongoRepository<SkuEntity, String> {

}

