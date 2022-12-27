package org.example.services;


import org.example.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {
    
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    
    @Transactional
    public void addProduct(String name){
        productRepository.addProject(name);
        throw new RuntimeException(";(");
    }
    
}
