package com.example.AutoPartsStore.service;

import com.example.AutoPartsStore.domain.MarkCategory;
import com.example.AutoPartsStore.repo.MarkCategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class StoreService {
//    @Autowired
//    private CategoryRepo categoryRepo;

    @Autowired
    MarkCategoryRepo markCategoryRepo;

    public void saveCategory(MarkCategory markCategory, String name, Map<String, String> model){
        markCategory.setMarkCategoryName(name);
        markCategoryRepo.save(markCategory);
    }
}
