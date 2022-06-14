package com.example.AutoPartsStore.repo;

import com.example.AutoPartsStore.domain.EngineType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface EngineTypeRepo extends JpaRepository<EngineType, Long> {
}
