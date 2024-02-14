package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductServiceImplTest {

    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productService = new ProductServiceImpl();
        productService.productRepository = productRepository;
    }

    @Test
    void testCreateProduct() {
        // Setup
        Product product = createProduct("987654321", "Ipad Air 4", 100);
        when(productRepository.create(any(Product.class))).thenReturn(product);

        // Exercise
        Product createdProduct = productService.create(product);

        // Verify
        verify(productRepository, times(1)).create(product);
        assertProductEquality(product, createdProduct);
    }

    @Test
    void testFindAllProducts() {
        // Setup
        List<Product> productList = createProductList();
        when(productRepository.findAll()).thenReturn(productList.iterator());

        // Exercise
        List<Product> foundProducts = productService.findAll();

        // Verify
        verify(productRepository, times(1)).findAll();
        assertIterableEquals(productList, foundProducts);
    }

    @Test
    void testEditProduct() {
        // Setup
        Product product = createProduct("987654321", "Iphone 15 Pro", 100);
        when(productRepository.edit(any(Product.class))).thenReturn(product);

        // Exercise
        Product editedProduct = productService.edit(product);

        // Verify
        verify(productRepository, times(1)).edit(product);
        assertProductEquality(product, editedProduct);
    }

    @Test
    void testGetProductById() {
        // Setup
        List<Product> productList = createProductList();
        when(productRepository.findAll()).thenReturn(productList.iterator());

        // Exercise
        Product retrievedProduct = productService.get("2");

        // Verify
        assertNotNull(retrievedProduct);
        assertEquals("2", retrievedProduct.getProductId());
        assertEquals("Product 2", retrievedProduct.getProductName());
    }

    @Test
    void testDeleteNonExistingProduct() {
        // Setup
        when(productRepository.findAll()).thenReturn(new ArrayList<Product>().iterator());

        // Exercise & Verify
        assertFalse(productService.delete(1));
        verify(productRepository, never()).delete(any(Product.class));
    }

    @Test
    void testDeleteProductWithZeroQuantityShouldInvokeRepositoryDelete() {
        // Setup
        Product product = createProduct("1", "Product 1", 1);
        when(productRepository.findAll()).thenReturn(List.of(product).iterator());

        // Exercise
        assertTrue(productService.delete(1));

        // Verify
        assertEquals(0, product.getProductQuantity());
        verify(productRepository, times(1)).delete(product);
    }

    private Product createProduct(String id, String name, int quantity) {
        Product product = new Product();
        product.setProductId(id);
        product.setProductName(name);
        product.setProductQuantity(quantity);
        return product;
    }

    private List<Product> createProductList() {
        List<Product> productList = new ArrayList<>();
        productList.add(createProduct("1", "Product 1", 10));
        productList.add(createProduct("2", "Product 2", 20));
        return productList;
    }

    private void assertProductEquality(Product expected, Product actual) {
        assertNotNull(actual);
        assertEquals(expected.getProductId(), actual.getProductId());
        assertEquals(expected.getProductName(), actual.getProductName());
        assertEquals(expected.getProductQuantity(), actual.getProductQuantity());
    }
}
