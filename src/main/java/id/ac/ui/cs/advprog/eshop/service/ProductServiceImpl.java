package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductRepository productRepository;
    private int id = 0;

    @Override
    public Product create(Product product) {
        product.setProductId(Integer.toString(++id));
        productRepository.create(product);
        return product;
    }

    @Override
    public List<Product> findAll() {
        Iterator<Product> productIterator = productRepository.findAll();
        List<Product> allProduct = new ArrayList<>();
        productIterator.forEachRemaining(allProduct::add);
        return allProduct;
    }

    public Product get(String id) {
        for (Iterator<Product> it = productRepository.findAll(); it.hasNext(); ) {
            Product currentIteration = it.next();
            if (currentIteration.getProductId().equals(id)) {
                return currentIteration;
            }
        }
        return null;
    }


    @Override
    public Boolean delete(int id) {
        Product product = null;
        Iterator<Product> iterator = productRepository.findAll();
        while (iterator.hasNext()) {
            Product currentIteration = iterator.next();
            if (currentIteration.getProductId().equals(Integer.toString(id))) {
                product = currentIteration;
                break;
            }
        }
        if (product == null) {
            return false;
        }

        int newQuantity = product.getProductQuantity() - 1;
        if (newQuantity < 0) {
            return false;
        }

        product.setProductQuantity(newQuantity);
        if (newQuantity == 0) {
            productRepository.delete(product);
        }
        return true;

    }

    @Override
    public Product edit(Product product) {
        productRepository.edit(product);
        return product;
    }

}