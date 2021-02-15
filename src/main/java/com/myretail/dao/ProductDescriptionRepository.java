package com.myretail.dao;

import com.myretail.model.ProductDescription;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDescriptionRepository extends MongoRepository<ProductDescription, Long> {
}
