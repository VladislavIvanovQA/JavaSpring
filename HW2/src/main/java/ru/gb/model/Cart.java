package ru.gb.model;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import ru.gb.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    @Getter
    private final ProductRepository productRepository;
    private final List<Product> products;

    public Cart(ProductRepository productRepository) {
        this.productRepository = productRepository;
        this.products = new ArrayList<>();
    }

    public void addProduct(Integer productId) {
        try {
            this.products.add(productRepository.getProductById(productId));
        } catch (NullPointerException e) {
            System.err.println(e.getMessage());
        }
    }

    public void deleteProduct(Integer productId) {
        try {
            Product deleteProduct = this.products.stream()
                    .filter(product -> product.getId().equals(productId))
                    .findFirst()
                    .orElseThrow(() -> new NullPointerException(String.format("Product id: %s not contains to cart!", productId)));
            this.products.remove(deleteProduct);
        } catch (NullPointerException e) {
            System.err.println(e.getMessage());
        }
    }

    public void removeAllProductInCart() {
        products.clear();
    }

    public void printCart() {
        if (!products.isEmpty()) {
            products.forEach(product -> {
                System.out.printf("Product id: %s title: %s cost: %.2f%n",
                        product.getId(),
                        product.getTitle(),
                        product.getCost());
            });
            System.out.printf("Total price: %.2f\n", products.stream()
                    .mapToDouble(Product::getCost)
                    .sum());
        } else {
            System.out.println("Cart empty!");
        }
    }
}
