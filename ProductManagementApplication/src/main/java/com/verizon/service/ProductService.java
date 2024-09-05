package com.verizon.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.verizon.dao.ProductDao;
import com.verizon.exception.ProductNotFoundException;
import com.verizon.model.Product;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductDao productDao;

    public String addProduct(Product product) {
        productDao.save(product);
        return "Product Added";
    }

    public List<Product> getAllProducts() {
        return productDao.findAll();
    }

    public Product getProductById(Integer id) {
        return productDao.findById(id)
                         .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
    }

    public List<Product> getAllProductsBetweenPrice(Integer low, Integer high) {
        return productDao.findProductsBetweenPriceRange(low, high);
    }

    public Product updateProduct(Integer id, Product product) {
        if (!productDao.existsById(id)) {
            throw new ProductNotFoundException("Product not found with id: " + id);
        }
        product.setPid(id);  // Ensure the id is set in case it's missing
        return productDao.save(product);
    }

    public void deleteProduct(Integer id) {
        if (!productDao.existsById(id)) {
            throw new ProductNotFoundException("Product not found with id: " + id);
        }
        productDao.deleteById(id);
    }
}
