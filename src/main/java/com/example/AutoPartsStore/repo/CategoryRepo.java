package com.example.AutoPartsStore.repo;

import com.example.AutoPartsStore.domain.Category;
import com.example.AutoPartsStore.domain.MarkCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepo extends JpaRepository<Category, Long> {
    List<Category> findByCategoryName(String categoryName);
}
