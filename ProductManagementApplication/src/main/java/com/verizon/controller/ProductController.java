package com.verizon.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.verizon.exception.ProductNotFoundException;
import com.verizon.model.Product;
import com.verizon.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer id) {
        try {
            Product product = productService.getProductById(id);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (ProductNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/price/{low}/{high}")
    public List<Product> getProductBetweenPriceRange(@PathVariable Integer low, @PathVariable Integer high) {
        return productService.getAllProductsBetweenPrice(low, high);
    }

    @PostMapping
    public ResponseEntity<String> addProductDetails(@RequestBody Product product) {
        productService.addProduct(product);
        return new ResponseEntity<>("Product Added", HttpStatus.CREATED);
    }

    @PutMapping("/{pid}")
    public ResponseEntity<Product> updateProductDetails(@PathVariable("pid") Integer pid, @RequestBody Product product) {
        try {
            Product updatedProduct = productService.updateProduct(pid, product);
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        } catch (ProductNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{pid}")
    public ResponseEntity<String> deleteProductDetails(@PathVariable("pid") Integer pid) {
        try {
            productService.deleteProduct(pid);
            return new ResponseEntity<>("Product Deleted", HttpStatus.OK);
        } catch (ProductNotFoundException e) {
            return new ResponseEntity<>("Product Not Found", HttpStatus.NOT_FOUND);
        }
    }
}
