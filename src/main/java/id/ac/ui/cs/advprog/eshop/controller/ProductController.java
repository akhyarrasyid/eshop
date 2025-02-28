package id.ac.ui.cs.advprog.eshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/create")
    public String createProductPage(Model model) {
        model.addAttribute("product", new Product());
        return "createProduct";
    }

    @PostMapping("/create")
    public String createProductPost(@ModelAttribute("product") @Valid Product product, 
                                    BindingResult result, 
                                    Model model) {
        if (result.hasErrors()) {
            model.addAttribute("product", product);
            return "createProduct";
        }
        service.create(product);
        return "redirect:/product/list";
    }

    @GetMapping("/edit/{productId}")
    public String editProductPage(@PathVariable("productId") String productId, Model model) {
        Product existingProduct = service.findById(productId);
        if (existingProduct != null) {
            model.addAttribute("product", existingProduct);
            return "editProduct";
        }
        return "redirect:/product/list";
    }

    @PostMapping("/edit/{productId}")
    public String editProductPost(@PathVariable("productId") String productId,
                                  @ModelAttribute("product") @Valid Product product,
                                  BindingResult result, 
                                  Model model) {
        if (result.hasErrors()) {
            model.addAttribute("product", product);
            return "editProduct";
        }
        service.edit(productId, product);
        return "redirect:/product/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") String productId) {
        service.delete(productId);
        return "redirect:/product/list";
    }

    @GetMapping ("/list")
    public String productListPage (Model model) {
        List<Product> allProducts = service.findAll();
        model.addAttribute("products", allProducts);
        return "productList";
    }
}
