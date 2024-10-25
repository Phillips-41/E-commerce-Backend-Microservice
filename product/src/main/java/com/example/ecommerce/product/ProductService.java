package com.example.ecommerce.product;

import com.example.ecommerce.exception.ProductPurchaseException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;
    private final ProductMapper mapper;
    public Integer createProduct(@Valid ProductRequest request) {
        var product = mapper.toProduct(request);
        return repository.save(product).getId();
    }

    public List<ProductPurchaseResponse> purchaseProduct(@Valid List<ProductPurchaseRequest> request) {
        var productIds = request.stream()
                .map(ProductPurchaseRequest::productId)
                .toList();
        var storedProducts= repository.findAllByIdInOrderById(productIds);
        if(productIds.size()!= storedProducts.size()){
            throw new ProductPurchaseException("one or more products doesnt exists");
        }
        var storedRequest = request.stream().sorted(Comparator.comparing(ProductPurchaseRequest::productId)).toList();
        List<ProductPurchaseResponse> purchaseResponses= new ArrayList<>();
        for(int i=0;i<storedRequest.size();i++){
            var product = storedProducts.get(i);
            var productRequest=storedRequest.get(i);
            if(product.getAvailableQuantity()<productRequest.quantity()){
                throw new ProductPurchaseException("Insufficient stock quantity for id:: "+product.getId());
            }
            var newAvailableQuantity=product.getAvailableQuantity()-productRequest.quantity();
            product.setAvailableQuantity(newAvailableQuantity);
            repository.save(product);
            purchaseResponses.add(mapper.toProductPurchaseResponse(product,productRequest.quantity()));

        }
        return purchaseResponses;
    }

    public ProductResponse findById(Integer productId) {
        return repository.findById(productId).
                map(mapper:: fromProduct)
                .orElseThrow(()-> new EntityNotFoundException("Product with id " + productId + " not found"));
    }

    public List<ProductResponse> findAll() {
        return repository.findAll().stream().map(mapper:: fromProduct).collect(Collectors.toList());
    }
}
