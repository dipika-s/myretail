package com.myretail.controller;

import com.myretail.dao.ProductDescriptionRepository;
import com.myretail.model.ProductDescription;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductionDescriptionControllerTest {
    @Mock
    private ProductDescriptionRepository productDescriptionRepository;

    @InjectMocks
    ProductDescriptionController productDescriptionController;

    @Test
    public void testGetProductDescription_whenProductExists(){
        Long productId = 101l;
        ProductDescription productDescription = new ProductDescription(productId, "iPhone");
        Optional<ProductDescription> productDescriptionOptional = Optional.of(productDescription);
        when(productDescriptionRepository.findById(101l)).thenReturn(productDescriptionOptional);
        //make call to actual method
        ResponseEntity<ProductDescription> response = productDescriptionController.getProductDescription(productId);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals("iPhone", response.getBody().getName());
    }

    @Test
    public void testGetProductDescription_whenProductDoesNotExists(){
        Long productId = 101l;
        Optional<ProductDescription> productDescriptionOptional = Optional.ofNullable(null);
        when(productDescriptionRepository.findById(101l)).thenReturn(productDescriptionOptional);
        //make call to actual method
        ResponseEntity<ProductDescription> response = productDescriptionController.getProductDescription(productId);
        Assert.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void testSaveProductDescription_whenValidRequestBody(){
        Long productId = 101l;
        ProductDescription productDescription = new ProductDescription(productId, "iPhone");
        when(productDescriptionRepository.save(productDescription)).thenReturn(productDescription);
        //make call to actual method
        productDescriptionController.saveProductDescription(productDescription);
     }

    @Test
    public void testSaveProductDescription_whenNullRequestBody(){
        ProductDescription productDescription = null;
        when(productDescriptionRepository.save(productDescription)).thenReturn(productDescription);
        //make call to actual method
        productDescriptionController.saveProductDescription(productDescription);
    }
}
