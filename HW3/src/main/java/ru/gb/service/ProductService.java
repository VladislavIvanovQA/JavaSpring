package ru.gb.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.model.Product;
import ru.gb.repository.ProductRepository;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Product save(Product product) {
        return productRepository.addProduct(product);
    }

    public Product edit(Product product) {
        return productRepository.editProduct(product);
    }

    public Product findById(int id) {
        return productRepository.getProductById(id).orElse(new Product());
    }

    public Iterable<Product> findAll() {
        return productRepository.getProducts();
    }

    public Integer count() {
        return productRepository.count();
    }

    public void deleteById(int id) {
        productRepository.deleteProductById(id);
    }
}
