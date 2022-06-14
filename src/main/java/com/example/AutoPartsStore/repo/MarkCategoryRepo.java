package com.example.AutoPartsStore.repo;

import com.example.AutoPartsStore.domain.MarkCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MarkCategoryRepo extends JpaRepository<MarkCategory, Long> {
    List<MarkCategory> findByMarkCategoryName(String markCategoryName);
}
