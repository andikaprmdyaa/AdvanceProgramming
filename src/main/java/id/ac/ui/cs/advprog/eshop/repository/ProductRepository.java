package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

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
        Optional<Product> existingProduct = productData.stream()
                .filter(p -> p.getProductId().equals(editedProduct.getProductId()))
                .findFirst();

        existingProduct.ifPresent(product -> productData.set(productData.indexOf(product), editedProduct));

        return existingProduct.orElse(null);
    }

}