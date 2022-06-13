package com.example.AutoPartsStore.repo;

import com.example.AutoPartsStore.domain.Product;
import com.example.AutoPartsStore.domain.Subcategory;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface ProductRepo extends CrudRepository<Product, Long> {
    Set<Product> findByProductName(String productName);
}
