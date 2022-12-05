package com.demo.controller;

import com.demo.entity.ProductEntity;
import com.demo.service.ProductClientService;
import com.demo.vo.MutationResponseVO;
import com.demo.vo.ProductInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class ProductController {

    @Autowired
    ProductClientService productClientService;

    @QueryMapping("product")
    public ProductEntity getProductById(@Argument int id){
        return productClientService.getProduct(id);
    }

    @MutationMapping("addProduct")
    public MutationResponseVO saveProduct(@Argument ProductInput productInput){
        ProductEntity product = new ProductEntity();
        product.setDescription(productInput.getDescription());
        product.setImage(productInput.getImage());
        product.setName(productInput.getName());
        product.setPrice(productInput.getPrice());
        product.setQuantity(productInput.getQuantity());
        product.setOnSale(productInput.isOnSale());
        return productClientService.addProduct(product);
    }

    @MutationMapping("updateProduct")
    public MutationResponseVO updateProduct(@Argument int id, @Argument ProductInput productInput){
        ProductEntity product = new ProductEntity();
        product.setDescription(productInput.getDescription());
        product.setImage(productInput.getImage());
        product.setName(productInput.getName());
        product.setPrice(productInput.getPrice());
        product.setQuantity(productInput.getQuantity());
        product.setOnSale(productInput.isOnSale());
        return productClientService.updateProduct(product, id);
    }

    @MutationMapping("deleteProduct")
    public MutationResponseVO deleteProduct(@Argument int id){
        return productClientService.deleteProduct(id);
    }
}
