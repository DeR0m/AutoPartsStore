package com.example.AutoPartsStore.repo;

import com.example.AutoPartsStore.domain.ProductForMark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ProductForMarkRepo extends JpaRepository<ProductForMark, Long> {
}
