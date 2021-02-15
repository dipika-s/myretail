package com.myretail.service.impl;

import com.myretail.dao.ProductPriceRepository;
import com.myretail.model.Product;
import com.myretail.model.ProductDescription;
import com.myretail.model.ProductPrice;
import com.myretail.service.MyRetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



@Service(value="com_myretail_service_myRetailService")
public class MyRetailServiceImpl implements MyRetailService {
    @Autowired
    private ProductPriceRepository productPriceRepository;

    @Value("${myretail.product.description.url}")
    private String productDescriptionUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Product getProductDetails(Long id) {
        Product product = null;
        //get product name
        ProductDescription productDescription = getProductDescription(id);
        // get price Info from DB
        ProductPrice price = productPriceRepository.findById(id).isPresent() ?
                productPriceRepository.findById(id).get() : null;
        // create Product object
        String name = productDescription != null ? productDescription.getName() : null;
        if(name != null || price != null){
            product = new Product(id, name, price);
        }
        return product;
    }

    /**
     * Calls Rest API to get product name and other details
     * @param id - Product Id to fetch product details
     * @return ProductDescription
     */
    private ProductDescription getProductDescription(Long id){
        return restTemplate.getForObject(productDescriptionUrl, ProductDescription.class, id);
    }

    @Override
    public boolean updateProductDetails(Product productDetails) {
        Long productId = productDetails.getId();
        ProductPrice priceToUpdate = productDetails.getPrice();
        //First fetch existing object from datastore
        ProductPrice price = productPriceRepository.findById(productId).isPresent() ?
                productPriceRepository.findById(productId).get() : null;
        if(price != null){
            //it means that price with this id exists in datastore and we can go ahead with update
            priceToUpdate.setId(productDetails.getId());
            productPriceRepository.save(priceToUpdate);
            return true;
        }
        return false;
    }
}
