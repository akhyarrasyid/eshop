package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {

    private ProductRepository productRepository;
    private Product product;
    private static final String PRODUCT_ID = "eb558e9f-1c39-460e-8860-71af6af63bd6";

    @BeforeEach
    void setUp() {
        productRepository = new ProductRepository();
        product = new Product();
        product.setProductId(PRODUCT_ID);
        product.setProductName("Sample Product");
        product.setProductQuantity(10);
    }

    @Test
    void testCreateProduct() {
        // Act
        Product result = productRepository.create(product);

        // Assert
        assertEquals(product, result);
        assertNotNull(productRepository.findById(PRODUCT_ID));
    }

    @Test
    void testFindAllEmpty() {
        // Act
        Iterator<Product> iterator = productRepository.findAll();

        // Assert
        assertFalse(iterator.hasNext());
    }

    @Test
    void testFindAllWithProducts() {
        // Arrange
        productRepository.create(product);
        Product secondProduct = new Product();
        secondProduct.setProductId("second-id");
        secondProduct.setProductName("Second Product");
        secondProduct.setProductQuantity(5);
        productRepository.create(secondProduct);

        // Act
        Iterator<Product> iterator = productRepository.findAll();

        // Assert
        assertTrue(iterator.hasNext());
        assertEquals(product, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(secondProduct, iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    void testEditProductSuccess() {
        // Arrange
        productRepository.create(product);
        Product updatedProduct = new Product();
        updatedProduct.setProductName("Updated Name");
        updatedProduct.setProductQuantity(20);

        // Act
        Product result = productRepository.edit(PRODUCT_ID, updatedProduct);

        // Assert
        assertNotNull(result);
        assertEquals("Updated Name", result.getProductName());
        assertEquals(20, result.getProductQuantity());
        assertEquals(PRODUCT_ID, result.getProductId());
    }

    @Test
    void testEditProductNotFound() {
        // Arrange
        Product updatedProduct = new Product();
        updatedProduct.setProductName("Updated Name");
        updatedProduct.setProductQuantity(20);

        // Act
        Product result = productRepository.edit("non-existent-id", updatedProduct);

        // Assert
        assertNull(result);
    }

    @Test
    void testDeleteProduct() {
        // Arrange
        productRepository.create(product);

        // Act
        productRepository.delete(PRODUCT_ID);

        // Assert
        assertNull(productRepository.findById(PRODUCT_ID));
    }

    @Test
    void testDeleteProductNonExistent() {
        // Arrange
        productRepository.create(product);

        // Act & Assert
        assertDoesNotThrow(() -> productRepository.delete("non-existent-id"));
        assertNotNull(productRepository.findById(PRODUCT_ID)); // Original product should still exist
    }

    @Test
    void testFindByIdFound() {
        // Arrange
        productRepository.create(product);

        // Act
        Product result = productRepository.findById(PRODUCT_ID);

        // Assert
        assertNotNull(result);
        assertEquals(PRODUCT_ID, result.getProductId());
        assertEquals("Sample Product", result.getProductName());
        assertEquals(10, result.getProductQuantity());
    }

    @Test
    void testFindByIdNotFound() {
        // Act
        Product result = productRepository.findById("non-existent-id");

        // Assert
        assertNull(result);
    }
}