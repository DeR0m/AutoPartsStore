package com.example.AutoPartsStore.repo;

import com.example.AutoPartsStore.domain.MarkModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface MarkModelRepo extends JpaRepository<MarkModel, Long> {
}
