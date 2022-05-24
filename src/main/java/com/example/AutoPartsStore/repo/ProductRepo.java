package com.example.AutoPartsStore.repo;

import com.example.AutoPartsStore.domain.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepo extends CrudRepository<Product, Long> {
}
