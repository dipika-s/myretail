# myretail

This project exposes webservices to retreive and update product details and pricing info from the system.
It uses Mongo DB as datastore. 


1. Get api to fetch product details 
URL : http://localhost:8080/myretail/1.0/products/{productId} 
Response : {
    "id": <productid>,
    "name": <product_name>,
    "current_price": {
        "value": <price>,
        "currency_code": <currency_code>
    }
}
  
2. Put api to update product price information
URL : http://localhost:8080/myretail/1.0/products/{productId}
Request Body : {
    "id": <productid>,
    "name": <product_name>,
    "current_price": {
        "value": <price>,
        "currency_code": <currency_code>
    }
}
Response : Http Status 200 if update is successful
           Http Status 400 if the id given in request is not found in the system
