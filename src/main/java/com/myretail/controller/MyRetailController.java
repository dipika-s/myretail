package com.myretail.controller;

import com.myretail.model.Product;
import com.myretail.service.MyRetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("myretail/1.0/")
public class MyRetailController {

    @Resource(name="com_myretail_service_myRetailService")
    MyRetailService myRetailService;

    @GetMapping("products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long productId)
    {
        Product product = myRetailService.getProductDetails(productId);
        if(product != null){
            return ResponseEntity.ok().body(product);
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping("products/{id}")
    public ResponseEntity updateProduct(@PathVariable("id") Long productId, @RequestBody Product productInfo)
    {
        if(productId.equals(productInfo.getId())) {
            boolean isUpdated = myRetailService.updateProductDetails(productInfo);
            if(!isUpdated){
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.ok().build();
    }
}
