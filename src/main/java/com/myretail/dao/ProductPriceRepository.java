package com.myretail.dao;
import com.myretail.model.ProductPrice;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPriceRepository extends MongoRepository<ProductPrice,Long> {
}
