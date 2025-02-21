package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import java.util.List;

public interface ProductService {
    Product create(Product product);
    Product edit(String productId, Product newProduct);
    void delete(String productId);
    Product findById(String productId);
    List<Product> findAll();
}