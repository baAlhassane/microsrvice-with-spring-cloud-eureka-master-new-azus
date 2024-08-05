package com.alhas.ecommerce.product;


import com.alhas.ecommerce.category.Category;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {
    public Product toProduct(ProductRequest requestProduct) {

        return Product.builder()
                .id(requestProduct.id())
                .name(requestProduct.name())
                .description(requestProduct.description())
                .price(requestProduct.price())
                .availableQuantity(requestProduct.availableQuantity())
                .category(Category.builder().id(requestProduct.categoryId()).build())
                .build();
    }

    public ProductResponse toproductResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getAvailableQuantity(),
                product.getPrice(),
                product.getCategory().getId(),
                product.getCategory().getName(),
                product.getCategory().getDescription()
        );
    }

    public ProductPurchaseResponse toProductPurchaseResponse(Product product, double quantity) {
        return new ProductPurchaseResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                quantity
        );
    }
}
