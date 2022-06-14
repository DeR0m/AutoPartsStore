package com.example.AutoPartsStore.repo;

import com.example.AutoPartsStore.domain.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface SubcategoryRepo extends JpaRepository<Subcategory, Long> {

}
