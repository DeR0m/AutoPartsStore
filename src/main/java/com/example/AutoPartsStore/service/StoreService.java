package com.example.AutoPartsStore.service;

import com.example.AutoPartsStore.domain.Category;
import com.example.AutoPartsStore.repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class StoreService {
    @Autowired
    private CategoryRepo categoryRepo;

    private void saveCategory(Category category, String name, Model model){
        category.setCategoryName(name);
        categoryRepo.save(category);
    }
}
