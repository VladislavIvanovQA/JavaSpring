package ru.gb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.gb.model.Product;
import ru.gb.service.ProductService;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String getAllProducts(Model model) {
        model.addAttribute("products", productService.findAll());
        return "productList";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getProduct(Model model, @PathVariable Integer id) {
        model.addAttribute("product", productService.findById(id));
        return "product";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String showCreateProduct(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "create-product";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String processProduct(Product product) {
        if (product.getId() == null) {
            productService.save(product);
        } else {
            productService.edit(product);
        }
        return "redirect:/product/all";
    }

    @RequestMapping(path = "/edit", method = RequestMethod.GET)
    public String editById(Model model, @RequestParam Integer id) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return "create-product";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteProduct(@RequestParam Integer id) {
        productService.deleteById(id);
        return "redirect:/product/all";
    }
}
