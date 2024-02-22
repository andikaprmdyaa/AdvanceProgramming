package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product){
        productData.add(product);
        return product;
    }

    public Iterator<Product> findAll() {
        return productData.iterator();

    }


    public boolean delete(Product product) {
        return productData.remove(product);
    }

    public Product edit(Product editedProduct) {
        if (editedProduct.getProductQuantity() == 0) {
            // Return an error or throw an exception indicating that zero quantities are not allowed
            throw new IllegalArgumentException("Zero quantities are not allowed.");
        }
        for (Product curProduct : productData) {
            if (curProduct.getProductId().equals(editedProduct.getProductId())) {
                int index = productData.indexOf(curProduct);
                if (index != -1) {
                    productData.set(index, editedProduct);
                    return editedProduct;
                }
            }
        }
        // If the product is not found, return null
        return null;
    }
    public Product findProductById(String productId) {
            for (Product currentProduct : productData) {
            if (currentProduct.getProductId().equals(productId)) {
                return currentProduct;
            }
        }
        return null; // If no product with the specified ID is found
    }

}