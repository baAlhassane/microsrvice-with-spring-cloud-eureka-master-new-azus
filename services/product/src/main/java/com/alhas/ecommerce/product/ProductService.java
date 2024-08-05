package com.alhas.ecommerce.product;


import com.alhas.ecommerce.exception.ProductPurchaseException;
import jakarta.persistence.EntityNotFoundException;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class ProductService {


        private final ProductRepository productRepository;
        private final ProductMapper productMapper;



    public Integer createProduct(ProductRequest requestProduct) {
            var product=productMapper.toProduct(requestProduct);
                return productRepository.save(product).getId();
        }

        public List<ProductPurchaseResponse> purchaseProduct(List<ProductPurchaseRequest> requestProductPurchases) {
        var productIds=requestProductPurchases.stream()
                .map(ProductPurchaseRequest::productId)
                .toList();
            var storeProducts=productRepository.finAllByIdInOrderById(productIds);
            if(productIds.size()!=storeProducts.size()){
                throw new ProductPurchaseException("One or more products does not exists ");
            }
            var storesRequest=requestProductPurchases
                    .stream()
                    .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
                    .toList();
            var purchasedProducts=new ArrayList<ProductPurchaseResponse>();
            for(int i=0; i<storesRequest.size(); i++){
                var product=storeProducts.get(i);
                var productRequest=storesRequest.get(i);
                if(product.getAvailableQuantity()<productRequest.quantity()){
                    throw new ProductPurchaseException("Insufficient stock quantity for product "+productRequest.quantity() );

                }
                var newAvailableQuantity=product.getAvailableQuantity()-productRequest.quantity();
                  product.setAvailableQuantity(newAvailableQuantity);
                 productRepository.save(product);
                purchasedProducts.add(productMapper.toProductPurchaseResponse(product, productRequest.quantity()));
            }

                return purchasedProducts;
        }

        public ProductResponse findProductById(Integer productId) {
                return productRepository.findById(productId)
                                .map(productMapper::toproductResponse)
                        .orElseThrow(
                        ()-> new EntityNotFoundException("Product with id " + productId + " not found")
                );
        }


        public List<ProductResponse> findAllProduct() {
                return productRepository.findAll()
                        .stream()
                        .map(productMapper::toproductResponse)
                        .collect(Collectors.toList());

        }
}
