package com.example.AutoPartsStore.repo;

import com.example.AutoPartsStore.domain.ModelGeneration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ModelGenerationRepo extends JpaRepository<ModelGeneration, Long> {
}
