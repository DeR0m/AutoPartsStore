package com.example.AutoPartsStore.repo;

import com.example.AutoPartsStore.domain.Product;
import com.example.AutoPartsStore.domain.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.Set;

public interface ProductRepo extends JpaRepository<Product, Long> {
    Set<Product> findByProductName(String productName);

}
