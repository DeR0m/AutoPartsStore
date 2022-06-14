package com.example.AutoPartsStore.repo;

import com.example.AutoPartsStore.domain.BodyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface BodyTypeRepo extends JpaRepository<BodyType, Long> {
}
