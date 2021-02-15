package com.myretail.service;

import com.myretail.model.Product;

public interface MyRetailService {
    /**
     * Gets product details along with pricing information
     * @param id
     * @return
     */
    Product getProductDetails(Long id);

    /**
     * Updates product price details
     * @param productDetails
     * @return boolean - true in case update is successful
     *                 - false in case update is not successful
     */
    boolean  updateProductDetails(Product productDetails);
}
