package ru.gb.repository;

import org.springframework.stereotype.Component;
import ru.gb.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ProductRepository {
    private final List<Product> products = new ArrayList<>();

    public Optional<Product> getProductById(int id) {
        if (id < products.size()) {
            return Optional.ofNullable(products.get(id));
        } else {
            return Optional.empty();
        }
    }

    public Iterable<Product> getProducts() {
        return new ArrayList<>(products);
    }

    public Product addProduct(Product product) {
        products.add(product);
        product.setId(products.size() - 1);
        return Product.builder()
                .id(product.getId())
                .title(product.getTitle())
                .cost(product.getCost())
                .build();
    }

    public Product editProduct(Product editProduct) {
        return products.set(editProduct.getId(), editProduct);
    }

    public Integer count() {
        return products.size();
    }

    public void deleteProductById(int id) {
        if (id < products.size()) {
            products.remove(id);
        }
    }
}
