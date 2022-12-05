package com.demo.service;

import com.demo.DeleteRequest;
import com.demo.Product;
import com.demo.ProductServiceGrpc;
import com.demo.entity.ProductEntity;
import com.demo.vo.MutationResponseVO;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProductClientService {

    @GrpcClient("product-grpc-client")
    ProductServiceGrpc.ProductServiceBlockingStub synchronousClient;

    public ProductEntity getProduct(int productId){
       Product productRequest = Product.newBuilder().setId(productId).build();
       Product productResponse = synchronousClient.getProduct(productRequest);
       log.info(productResponse.toString());
       return new ProductEntity(productResponse.getId(),
                productResponse.getName(), productResponse.getDescription(),
                productResponse.getQuantity(), productResponse.getPrice(),
                productResponse.getImage(), productResponse.getOnSale());
    }

    public MutationResponseVO addProduct(ProductEntity product){
        Product productRequest = Product.newBuilder()
                .setName(product.getName())
                .setDescription(product.getDescription())
                .setQuantity(product.getQuantity())
                .setPrice(product.getPrice())
                .setImage(product.getImage())
                .setOnSale(product.isOnSale()).build();
        String message = synchronousClient.addProduct(productRequest).getMessage();
        return new MutationResponseVO(200, message);
    }

    public MutationResponseVO updateProduct(ProductEntity product, int productId){
        Product productRequest = Product.newBuilder()
                .setId(productId)
                .setName(product.getName())
                .setDescription(product.getDescription())
                .setQuantity(product.getQuantity())
                .setPrice(product.getPrice())
                .setImage(product.getImage())
                .setOnSale(product.isOnSale()).build();
        String message = synchronousClient.updateProduct(productRequest).getMessage();
        return  new MutationResponseVO(200, message);
    }

    public MutationResponseVO deleteProduct(int productId){
        DeleteRequest deleteRequest = DeleteRequest.newBuilder().setId(productId).build();
        String message = synchronousClient.deleteProduct(deleteRequest).getMessage();
        return new MutationResponseVO(200, message);
    }
}
