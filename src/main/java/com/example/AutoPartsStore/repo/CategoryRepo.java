package com.example.AutoPartsStore.repo;

import com.example.AutoPartsStore.domain.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepo extends CrudRepository<Category, Long> {
}
