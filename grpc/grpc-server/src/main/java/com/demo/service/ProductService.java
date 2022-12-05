package com.demo.service;

import com.demo.DeleteRequest;
import com.demo.MutationResponse;
import com.demo.Product;
import com.demo.ProductServiceGrpc;
import com.demo.entity.ProductEntity;
import com.demo.mapper.ProductMapper;
import com.demo.repository.ProductRepository;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@GrpcService
@Slf4j
public class ProductService extends ProductServiceGrpc.ProductServiceImplBase {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductMapper productMapper;

    @Override
    public void getProduct(Product request, StreamObserver<Product> responseObserver) {
        log.info("request received at getProduct");
        Optional<ProductEntity> result = productRepository.findById(request.getId());
        if(result.isPresent()){
            log.info(result.get().toString());
            responseObserver.onNext(productMapper.convertToProduct(result.get()));
            responseObserver.onCompleted();
        }
        else{
            throw new RuntimeException(String.format("product not found with id %s", request.getId()));
        }
    }

    @Override
    public void addProduct(Product request, StreamObserver<MutationResponse> responseObserver) {
        log.info("request received at addProduct");
        productRepository.save(productMapper.convertToProductEntity(request));
        responseObserver.onNext(MutationResponse.newBuilder().setMessage("product created successfully").build());
        responseObserver.onCompleted();
    }

    @Override
    public void updateProduct(Product request, StreamObserver<MutationResponse> responseObserver) {
        log.info("request received at updateProduct");
        Optional<ProductEntity> result = productRepository.findById(request.getId());
        if(result.isPresent()){
            request.newBuilderForType().setId(request.getId());
            productRepository.save(productMapper.convertToProductEntity(request));
            responseObserver.onNext(MutationResponse.newBuilder().setMessage("product updated successfully").build());
            responseObserver.onCompleted();
        }
        else{
            throw new RuntimeException(String.format("product not found with id %s", request.getId()));
        }
    }

    @Override
    public void deleteProduct(DeleteRequest request, StreamObserver<MutationResponse> responseObserver) {
        log.info("request received at updateProduct");
        Optional<ProductEntity> result = productRepository.findById(request.getId());
        if(result.isPresent()){
            productRepository.delete(result.get());
            responseObserver.onNext(MutationResponse.newBuilder().setMessage("product deleted successfully").build());
            responseObserver.onCompleted();
        }
        else{
            throw new RuntimeException(String.format("product not found with id %s", request.getId()));
        }
    }
}
