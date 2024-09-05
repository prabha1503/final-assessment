package com.verizon.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.verizon.model.Product;

@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {

    @Query("SELECT p FROM Product p WHERE p.price BETWEEN :low AND :high")
    List<Product> findProductsBetweenPriceRange(@Param("low") Integer low, @Param("high") Integer high);
}
