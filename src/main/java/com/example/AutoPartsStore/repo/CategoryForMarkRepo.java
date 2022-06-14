package com.example.AutoPartsStore.repo;

import com.example.AutoPartsStore.domain.CategoryForMark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CategoryForMarkRepo extends JpaRepository<CategoryForMark, Long> {
}
