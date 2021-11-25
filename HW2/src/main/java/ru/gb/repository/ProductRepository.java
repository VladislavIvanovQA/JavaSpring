package ru.gb.repository;

import org.springframework.beans.factory.annotation.Autowired;
import ru.gb.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ProductRepository {
    private final List<Product> products;

    @Autowired
    public ProductRepository() {
        this.products = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            this.products.add(Product.builder()
                    .id(i)
                    .title("Title" + i)
                    .cost(new Random().nextDouble())
                    .build());
        }
    }

    public Product getProductById(Integer id) {
        return products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NullPointerException(String.format("Product id: %s not found!", id)));
    }

    public List<Product> getProducts() {
        return products;
    }

    public void printProducts() {
        products.forEach(product -> {
            System.out.printf("Product id: %s title: %s cost: %.2f%n",
                    product.getId(),
                    product.getTitle(),
                    product.getCost());
        });
    }
}
