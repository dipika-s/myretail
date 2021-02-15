package com.myretail.controller;

import com.myretail.model.Product;
import com.myretail.model.ProductPrice;
import com.myretail.service.MyRetailService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RunWith(MockitoJUnitRunner.class)
public class MyRetailControllerTest {
    @Mock
    MyRetailService myRetailService;

    @InjectMocks
    MyRetailController myRetailController;

    @Test
    public void testGetProduct_whenProductDetailsFound(){
        Long productId = 101l;
        Product productDetails = new Product(productId, "test-pro",
                new ProductPrice(productId, 10.0, "USD"));
        when(myRetailService.getProductDetails(productId)).thenReturn(productDetails);
        ResponseEntity<Product> result = myRetailController.getProduct(productId);
        Assert.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assert.assertEquals("test-pro", result.getBody().getName());
    }

    @Test
    public void testGetProduct_WhenProductNotFound(){
        Long productId = 101l;
        when(myRetailService.getProductDetails(productId)).thenReturn(null);
        ResponseEntity<Product> result = myRetailController.getProduct(productId);
        Assert.assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
    }

    @Test
    public void testUpdateProduct_whenProductIdMatches(){
        Long productId = 101l;
        Product productToUpdate = new Product(productId, "test-pro",
                new ProductPrice(productId, 20.0, "USD"));
        when(myRetailService.updateProductDetails(any())).thenReturn(true);
        //make call
        myRetailController.updateProduct(productId, productToUpdate);
        // verify that service method is called
        verify(myRetailService, times(1)).updateProductDetails(productToUpdate);
    }

    @Test
    public void testUpdateProduct_whenProductIdNotMatches(){
        Long productId = 101l;
        Product productToUpdate = new Product(102l, "test-pro",
                new ProductPrice(productId, 20.0, "USD"));
        //make call
        myRetailController.updateProduct(productId, productToUpdate);
        // verify that service method is not called
        verify(myRetailService, times(0)).updateProductDetails(productToUpdate);
    }

}
