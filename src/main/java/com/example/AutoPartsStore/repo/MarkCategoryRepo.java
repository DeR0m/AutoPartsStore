package com.example.AutoPartsStore.repo;

import com.example.AutoPartsStore.domain.MarkCategory;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MarkCategoryRepo extends CrudRepository<MarkCategory, Long> {
    List<MarkCategory> findByMarkCategoryName(String markCategoryName);
}
