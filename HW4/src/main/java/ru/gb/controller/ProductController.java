package ru.gb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gb.model.Product;
import ru.gb.service.ProductService;

@Controller
//@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public String main() {
        return "redirect:/product/all";
    }

    @GetMapping(value = "/all")
    public String getAllProducts(Model model) {
        model.addAttribute("products", productService.findAll());
        return "productList";
    }

//    @GetMapping(value = "/{id}")
//    public String getProduct(Model model, @PathVariable Integer id) {
//        model.addAttribute("product", productService.findById(id));
//        return "product";
//    }

    @GetMapping(value = "/create")
    public String showCreateProduct(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "create-product";
    }

    @PostMapping(value = "/create")
    public String processProduct(Product product) {
        if (product.getId() == null) {
            productService.save(product);
        } else {
            productService.edit(product);
        }
        return "redirect:/product/all";
    }

    @GetMapping(path = "/edit")
    public String editById(Model model, @RequestParam Integer id) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return "create-product";
    }

    @GetMapping(value = "/delete")
    public String deleteProduct(@RequestParam Integer id) {
        productService.deleteById(id);
        return "redirect:/product/all";
    }
}
