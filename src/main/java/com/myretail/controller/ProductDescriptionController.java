package com.myretail.controller;

import com.myretail.dao.ProductDescriptionRepository;
import com.myretail.model.ProductDescription;
import com.myretail.model.ProductPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("myretail/1.0/")
public class ProductDescriptionController {
    @Autowired
    private ProductDescriptionRepository productDescriptionRepository;

    @GetMapping("products/{id}/details")
    public ResponseEntity<ProductDescription> getProductDescription(@PathVariable("id") Long productId){
        Optional<ProductDescription> productDescription = productDescriptionRepository.findById(productId);
        if(productDescription.isPresent()){
            return ResponseEntity.ok().body(productDescription.get());
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping("products/details")
    public void saveProductDescription(@RequestBody ProductDescription productDescription){
        productDescriptionRepository.save(productDescription);
    }
}
