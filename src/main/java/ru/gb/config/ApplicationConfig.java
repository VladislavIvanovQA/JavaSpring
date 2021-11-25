package ru.gb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.gb.model.Cart;
import ru.gb.repository.ProductRepository;
import ru.gb.service.ConsoleService;

@Configuration
@ComponentScan("ru.gb")
public class ApplicationConfig {
    @Bean
    public ProductRepository productRepository() {
        return new ProductRepository();
    }

    @Bean
    @Scope("prototype")
    public Cart cart(ProductRepository productRepository) {
        return new Cart(productRepository);
    }

    @Bean
    public ConsoleService consoleService(Cart cart) {
        return new ConsoleService(cart);
    }
}
