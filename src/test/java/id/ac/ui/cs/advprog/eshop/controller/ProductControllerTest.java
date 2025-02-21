package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Mock
    private ProductService productService;

    @Mock
    private Model model;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private ProductController productController;

    private Product product;
    private static final String PRODUCT_ID = "eb558e9f-1c39-460e-8860-71af6af63bd6";

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setProductId(PRODUCT_ID);
        product.setProductName("Sample Product");
        product.setProductQuantity(10);
    }

    @Test
    void testCreateProductPage() {
        // Act
        String viewName = productController.createProductPage(model);

        // Assert
        org.mockito.Mockito.verify(model).addAttribute(
                org.mockito.ArgumentMatchers.eq("product"),
                org.mockito.ArgumentMatchers.any(Product.class));
        org.junit.jupiter.api.Assertions.assertEquals("createProduct", viewName);
    }

    @Test
    void testCreateProductPostSuccess() {
        // Arrange
        org.mockito.Mockito.when(bindingResult.hasErrors()).thenReturn(false);
        org.mockito.Mockito.when(productService.create(product)).thenReturn(product);

        // Act
        String viewName = productController.createProductPost(product, bindingResult, model);

        // Assert
        org.mockito.Mockito.verify(productService).create(product);
        org.junit.jupiter.api.Assertions.assertEquals("redirect:/product/list", viewName);
    }

    @Test
    void testCreateProductPostValidationError() {
        // Arrange
        org.mockito.Mockito.when(bindingResult.hasErrors()).thenReturn(true);

        // Act
        String viewName = productController.createProductPost(product, bindingResult, model);

        // Assert
        org.mockito.Mockito.verify(model).addAttribute("product", product);
        org.junit.jupiter.api.Assertions.assertEquals("createProduct", viewName);
    }

    @Test
    void testEditProductPageProductExists() {
        // Arrange
        org.mockito.Mockito.when(productService.findById(PRODUCT_ID)).thenReturn(product);

        // Act
        String viewName = productController.editProductPage(PRODUCT_ID, model);

        // Assert
        org.mockito.Mockito.verify(model).addAttribute("product", product);
        org.junit.jupiter.api.Assertions.assertEquals("editProduct", viewName);
    }

    @Test
    void testEditProductPageProductNotFound() {
        // Arrange
        org.mockito.Mockito.when(productService.findById(PRODUCT_ID)).thenReturn(null);

        // Act
        String viewName = productController.editProductPage(PRODUCT_ID, model);

        // Assert
        org.junit.jupiter.api.Assertions.assertEquals("redirect:/product/list", viewName);
    }

    @Test
    void testEditProductPostSuccess() {
        // Arrange
        org.mockito.Mockito.when(bindingResult.hasErrors()).thenReturn(false);
        org.mockito.Mockito.when(productService.edit(PRODUCT_ID, product)).thenReturn(product);

        // Act
        String viewName = productController.editProductPost(PRODUCT_ID, product, bindingResult, model);

        // Assert
        org.mockito.Mockito.verify(productService).edit(PRODUCT_ID, product);
        org.junit.jupiter.api.Assertions.assertEquals("redirect:/product/list", viewName);
    }

    @Test
    void testEditProductPostValidationError() {
        // Arrange
        org.mockito.Mockito.when(bindingResult.hasErrors()).thenReturn(true);

        // Act
        String viewName = productController.editProductPost(PRODUCT_ID, product, bindingResult, model);

        // Assert
        org.mockito.Mockito.verify(model).addAttribute("product", product);
        org.junit.jupiter.api.Assertions.assertEquals("editProduct", viewName);
    }

    @Test
    void testDeleteProduct() {
        // Act
        String viewName = productController.deleteProduct(PRODUCT_ID);

        // Assert
        org.mockito.Mockito.verify(productService).delete(PRODUCT_ID);
        org.junit.jupiter.api.Assertions.assertEquals("redirect:/product/list", viewName);
    }

    @Test
    void testProductListPage() {
        // Arrange
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        org.mockito.Mockito.when(productService.findAll()).thenReturn(productList);

        // Act
        String viewName = productController.productListPage(model);

        // Assert
        org.mockito.Mockito.verify(model).addAttribute("products", productList);
        org.junit.jupiter.api.Assertions.assertEquals("productList", viewName);
    }
}
