package com.myretail.service.impl;

import com.myretail.dao.ProductPriceRepository;
import com.myretail.model.Product;
import com.myretail.model.ProductDescription;
import com.myretail.model.ProductPrice;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MyRetailServiceTest {
    @Mock
    ProductPriceRepository productPriceRepository;

    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    private MyRetailServiceImpl myRetailService;

    @Before
    public void setup(){
        ReflectionTestUtils.setField(myRetailService, "productDescriptionUrl", "product-desc-mock-url");
    }

    @Test
    public void testGetProductDetails_whenProductExists(){
        Long productId = 101l;
        ProductDescription productDescription = new ProductDescription(productId, "iPhone");
        ProductPrice productPrice = new ProductPrice(productId, 120.00, "USD");
        Optional<ProductPrice> productPriceOptional = Optional.of(productPrice);
        //mock calls
        when(restTemplate.getForObject(any(String.class), any(), any(Long.class))).thenReturn(productDescription);
        when(productPriceRepository.findById(productId)).thenReturn(productPriceOptional);
        //call method
        Product product = myRetailService.getProductDetails(productId);
        Assert.assertEquals("iPhone", product.getName());
    }

    @Test
    public void testGetProductDetails_whenProductDoesNotExists(){
        Long productId = 105l;
        Optional<ProductPrice> productPriceOptional = Optional.ofNullable(null);
        //mock calls
        when(restTemplate.getForObject(any(String.class), any(), any(Long.class))).thenReturn(null);
        when(productPriceRepository.findById(productId)).thenReturn(productPriceOptional);
        //call method
        Product product = myRetailService.getProductDetails(productId);
        Assert.assertNull(product);
    }

    @Test
    public void testGetProductDetails_whenProductDescritionNullButPriceExists(){
        Long productId = 105l;
        ProductPrice productPrice = new ProductPrice(productId, 120.00, "USD");
        Optional<ProductPrice> productPriceOptional = Optional.of(productPrice);
        //mock calls
        when(restTemplate.getForObject(any(String.class), any(), any(Long.class))).thenReturn(null);
        when(productPriceRepository.findById(productId)).thenReturn(productPriceOptional);
        //call method
        Product product = myRetailService.getProductDetails(productId);
        Assert.assertNull(product.getName());
        Assert.assertEquals(productPrice, product.getPrice());
    }

    @Test
    public void testGetProductDetails_whenProductDescritionExistsButPriceNotExists(){
        Long productId = 105l;
        ProductDescription productDescription = new ProductDescription(productId, "iPhone");
        Optional<ProductPrice> productPriceOptional = Optional.ofNullable(null);
        //mock calls
        when(restTemplate.getForObject(any(String.class), any(), any(Long.class))).thenReturn(productDescription);
        when(productPriceRepository.findById(productId)).thenReturn(productPriceOptional);
        //call method
        Product product = myRetailService.getProductDetails(productId);
        Assert.assertNull(product.getPrice());
        Assert.assertEquals("iPhone", product.getName());
    }

    @Test
    public void testUpdateProduct_whenProductIdExists(){
        Long productId = 105l;
        ProductPrice newProductPrice = new ProductPrice(105l, 90.23, "USD");
        Product newProduct = new Product(productId, "Laptop HP", newProductPrice);
        ProductPrice existingProductPrice = new ProductPrice(productId, 120.00, "USD");
        Optional<ProductPrice> productPriceOptional = Optional.of(existingProductPrice);
        when(productPriceRepository.findById(productId)).thenReturn(productPriceOptional);
        when(productPriceRepository.save(newProductPrice)).thenReturn(newProductPrice);
        //make api call
        myRetailService.updateProductDetails(newProduct);
        verify(productPriceRepository, times(1)).save(newProductPrice);
    }

    @Test
    public void testUpdateProduct_whenProductIdDoesNotExists(){
        Long productId = 102l;
        ProductPrice newProductPrice = new ProductPrice(productId, 90.23, "USD");
        Product newProduct = new Product(productId, "Laptop HP", newProductPrice);

        Optional<ProductPrice> productPriceOptional = Optional.ofNullable(null);
        when(productPriceRepository.findById(productId)).thenReturn(productPriceOptional);
        //make api call
        myRetailService.updateProductDetails(newProduct);
        verify(productPriceRepository, times(0)).save(newProductPrice);
    }

}
