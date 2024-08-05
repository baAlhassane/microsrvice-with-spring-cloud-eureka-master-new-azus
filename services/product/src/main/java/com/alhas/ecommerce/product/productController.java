package com.alhas.ecommerce.product;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor

public class productController {
  private final ProductService productService ;

@PostMapping
    public ResponseEntity<Integer> createProduct(
            @RequestBody @Valid ProductRequest requestProduct)
 {
    return ResponseEntity.ok(productService.createProduct(requestProduct));
}

@PostMapping("/purchases")
    public  ResponseEntity<List<ProductPurchaseResponse>> purchaseProducts(
            @RequestBody List<ProductPurchaseRequest> requestProductPurchases
){
    return  ResponseEntity.ok(productService.purchaseProduct(requestProductPurchases));
}


@GetMapping("/{product-id}")
    public ResponseEntity<ProductResponse> findById(
            @PathVariable("product-id" ) Integer productId
            ){

    return ResponseEntity.ok(productService.findProductById(productId));

}

@GetMapping
    public  ResponseEntity<List<ProductResponse> > findAllProduct(){
    return ResponseEntity.ok(productService.findAllProduct());
}
}
