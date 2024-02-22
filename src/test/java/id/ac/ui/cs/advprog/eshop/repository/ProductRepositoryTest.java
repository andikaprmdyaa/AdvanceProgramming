package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;
    @BeforeEach
    void setUp() {
    }
    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }
    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }
    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de47-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testEditProduct() {

        Product originalProduct = new Product();
        originalProduct.setProductId("18b7e61f-8bfa-4723-b535-7a1c88c9201d");
        originalProduct.setProductName("Ipad Pro X");
        originalProduct.setProductQuantity(10);
        productRepository.create(originalProduct);

        String updatedName = "Iphone 15 Pro";
        int updatedQuantity = 5;

        Product updatedProduct = new Product();
        updatedProduct.setProductId(originalProduct.getProductId());
        updatedProduct.setProductName(updatedName);
        updatedProduct.setProductQuantity(updatedQuantity);
        productRepository.edit(updatedProduct);


        // Assert
        assertEquals(updatedName, productRepository.findProductById(originalProduct.getProductId()).getProductName());
        assertEquals(updatedQuantity, productRepository.findProductById(originalProduct.getProductId()).getProductQuantity());


    }

    @Test
    void testEditProductToZeroQuantity() {

        Product originalProduct = new Product();
        originalProduct.setProductId("e61f8bfa-7a1c-4723-b535-18b788c9201d");
        originalProduct.setProductName("Ipad Air 4");
        originalProduct.setProductQuantity(10);
        productRepository.create(originalProduct);

        String updatedName = "Iphone 14 Max";
        int updatedQuantity = 0;

        Product updatedProduct = new Product();
        updatedProduct.setProductId(originalProduct.getProductId());
        updatedProduct.setProductName(updatedName);
        updatedProduct.setProductQuantity(updatedQuantity);

        try {
            productRepository.edit(updatedProduct);
            fail("Expected IllegalArgumentException was not thrown.");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("Zero quantities are not allowed."));
        }
    }

    @Test
    void testDeleteProduct() {

        Product productToDelete = new Product();
        productToDelete.setProductId("fb2871a9-40c6-4e4d-a4c2-102f9d8b3d39");
        productToDelete.setProductName("Mummy Corpse");
        productToDelete.setProductQuantity(100);
        productRepository.create(productToDelete);

        boolean deletionResult = productRepository.delete(productToDelete);

        assertTrue(deletionResult);
    }

    @Test
    void testDeleteProductWithNoID() {
        // Create a product with no ID (non-existent product)
        Product productWithNoID = new Product();
        productWithNoID.setProductId("product-id-missing");
        productWithNoID.setProductName("Product with No ID");
        productWithNoID.setProductQuantity(0);

        boolean deletionResult = productRepository.delete(productWithNoID);

        assertFalse(deletionResult);
    }

    @Test
    void testEditProductWithNonMatchingId() {
        // Creating an original product
        Product originalProduct = new Product();
        originalProduct.setProductId("a712-nsj33-2112");
        originalProduct.setProductName("Ipad Air 5");
        originalProduct.setProductQuantity(10);
        productRepository.create(originalProduct);

        // Attempting to update with a product having a different ID
        String updatedName = "Iphone 15";
        int updatedQuantity = 5;

        Product updatedProduct = new Product();
        updatedProduct.setProductId("differentId"); // Different ID
        updatedProduct.setProductName(updatedName);
        updatedProduct.setProductQuantity(updatedQuantity);

        // Attempt to edit the product
        productRepository.edit(updatedProduct);

        // Verify that the repository state remains unchanged
        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext()); // Repository should not be empty
        Product savedProduct = productIterator.next();
        assertEquals(originalProduct.getProductId(), savedProduct.getProductId());
        assertEquals(originalProduct.getProductName(), savedProduct.getProductName());
        assertEquals(originalProduct.getProductQuantity(), savedProduct.getProductQuantity());
        assertFalse(productIterator.hasNext()); // Only one product should be present in the repository

        Product retrievedProduct = productRepository.findProductById("a712-nsj33-2112");
        assertEquals("Ipad Air 5", retrievedProduct.getProductName());
        assertEquals(10, retrievedProduct.getProductQuantity());
    }
    @Test
    void FindProductByIDTest() {
        // Arrange
        Product product = new Product();
        product.setProductId("8a1c18b7-e61f-4723-b535-8bfa88c9201d");
        product.setProductName("ipad air 4");
        product.setProductQuantity(20);
        productRepository.create(product);

        // Act
        Product foundProduct = productRepository.findProductById(product.getProductId());

        // Assert
        assertEquals(product, foundProduct);

        // Act & Assert
        Product nonExistingProduct = productRepository.findProductById("non-existing-id");
        assertNull(nonExistingProduct);
    }
}