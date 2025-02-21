package id.ac.ui.cs.advprog.eshop.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

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
    void testCreateWithoutProductId() {
        // Arrange
        Product newProduct = new Product();
        newProduct.setProductName("New Product");
        newProduct.setProductQuantity(5);
        
        when(productRepository.create(any(Product.class))).thenReturn(newProduct);

        // Act
        Product created = productService.create(newProduct);

        // Assert
        assertNotNull(created.getProductId());
        verify(productRepository).create(newProduct);
    }

    @Test
    void testCreateWithExistingProductId() {
        // Arrange
        when(productRepository.create(product)).thenReturn(product);

        // Act
        Product created = productService.create(product);

        // Assert
        assertEquals(PRODUCT_ID, created.getProductId());
        verify(productRepository).create(product);
    }

    @Test
    void testFindAll() {
        // Arrange
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        Iterator<Product> iterator = productList.iterator();
        
        when(productRepository.findAll()).thenReturn(iterator);

        // Act
        List<Product> result = productService.findAll();

        // Assert
        assertEquals(1, result.size());
        assertEquals(product, result.get(0));
        verify(productRepository).findAll();
    }

    @Test
    void testEdit() {
        // Arrange
        Product updatedProduct = new Product();
        updatedProduct.setProductName("Updated Product");
        updatedProduct.setProductQuantity(15);
        
        when(productRepository.edit(PRODUCT_ID, updatedProduct)).thenReturn(updatedProduct);

        // Act
        Product result = productService.edit(PRODUCT_ID, updatedProduct);

        // Assert
        assertEquals(updatedProduct, result);
        verify(productRepository).edit(PRODUCT_ID, updatedProduct);
    }

    @Test
    void testDelete() {
        // Arrange
        doNothing().when(productRepository).delete(PRODUCT_ID);

        // Act
        productService.delete(PRODUCT_ID);

        // Assert
        verify(productRepository).delete(PRODUCT_ID);
    }

    @Test
    void testFindById() {
        // Arrange
        when(productRepository.findById(PRODUCT_ID)).thenReturn(product);

        // Act
        Product result = productService.findById(PRODUCT_ID);

        // Assert
        assertEquals(product, result);
        verify(productRepository).findById(PRODUCT_ID);
    }

    @Test
    void testFindByIdNotFound() {
        // Arrange
        String nonExistentId = "non-existent-id";
        when(productRepository.findById(nonExistentId)).thenReturn(null);

        // Act
        Product result = productService.findById(nonExistentId);

        // Assert
        assertNull(result);
        verify(productRepository).findById(nonExistentId);
    }

    @Test
    void testCreateWithNullProductId() {
        // Arrange
        Product newProduct = new Product();
        newProduct.setProductId(null); // Null product ID
        newProduct.setProductName("Test Product");
        newProduct.setProductQuantity(5);

        when(productRepository.create(any(Product.class))).thenAnswer(invocation -> {
            Product p = invocation.getArgument(0);
            p.setProductId(UUID.randomUUID().toString());
            return p;
        });

        // Act
        Product created = productService.create(newProduct);

        // Assert
        assertNotNull(created.getProductId());
        verify(productRepository).create(any(Product.class));
    }

    @Test
    void testCreateWithEmptyProductId() {
        // Arrange
        Product newProduct = new Product();
        newProduct.setProductId(""); // Empty product ID
        newProduct.setProductName("Test Product");
        newProduct.setProductQuantity(5);

        when(productRepository.create(any(Product.class))).thenAnswer(invocation -> {
            Product p = invocation.getArgument(0);
            p.setProductId(UUID.randomUUID().toString());
            return p;
        });

        // Act
        Product created = productService.create(newProduct);

        // Assert
        assertNotNull(created.getProductId());
        verify(productRepository).create(any(Product.class));
    }
}
