package com.demo.mapper;

import com.demo.Product;
import com.demo.entity.ProductEntity;

import org.springframework.stereotype.Service;

@Service
public class ProductMapper {

        public ProductEntity convertToProductEntity(Product product){
            ProductEntity productEntity=new ProductEntity();
            productEntity.setId(product.getId());
            productEntity.setName(product.getName());
            productEntity.setDescription(product.getDescription());
            productEntity.setQuantity(product.getQuantity());
            productEntity.setPrice(product.getPrice());
            productEntity.setImage(product.getImage());
            productEntity.setOnSale(product.getOnSale());
            return productEntity;
        }

        public Product convertToProduct(ProductEntity productEntity) {
            return Product.newBuilder().setId(productEntity.getId())
                    .setName(productEntity.getName())
                    .setDescription(productEntity.getDescription())
                    .setQuantity(productEntity.getQuantity())
                    .setPrice(productEntity.getPrice())
                    .setImage(productEntity.getImage())
                    .setOnSale(productEntity.isOnSale()).build();
        }
}

