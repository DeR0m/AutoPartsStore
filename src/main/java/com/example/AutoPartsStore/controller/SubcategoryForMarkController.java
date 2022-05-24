package com.example.AutoPartsStore.controller;

import com.example.AutoPartsStore.domain.*;
import com.example.AutoPartsStore.repo.CategoryForMarkRepo;
import com.example.AutoPartsStore.repo.CategoryRepo;
import com.example.AutoPartsStore.repo.SubcategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Set;

@Controller
public class SubcategoryForMarkController {

    @Autowired
    private SubcategoryRepo subcategoryRepo;

    @Autowired
    private CategoryForMarkRepo categoryForMarkRepo;

    @GetMapping("{modelCategoryId}/generation/bodyType/engineType/categoryForMark/subcategoryForMark/{Id}")
    public String subcategoryForMark(
            @PathVariable(value = "Id") long id,
            Model model) {
        CategoryForMark categoryForMark = categoryForMarkRepo.findById(id).orElseThrow();
        model.addAttribute("categoryForMark", categoryForMark);

        EngineType engineType = categoryForMark.getEngineTypeId();
        model.addAttribute("engineType", engineType);

        BodyType bodyType = engineType.getBodyTypeId();
        model.addAttribute("bodyType", bodyType);

        ModelGeneration modelGeneration = bodyType.getModelGenerationId();
        model.addAttribute("modelGeneration", modelGeneration);

        MarkModel markModel = modelGeneration.getMarkModelId();
        model.addAttribute("markModel", markModel);

        MarkCategory markCategory = markModel.getMarkCategoryId();
        model.addAttribute("markCategory", markCategory);

        Set<Subcategory> subcategory = categoryForMark.getSubcategory();
        model.addAttribute("subcategories", subcategory);
        return "subcategoryForMark";
    }

    @PostMapping("{modelCategoryId}/generation/bodyType/engineType/categoryForMark/subcategoryForMark/{Id}")
    public String createSubcategoryForMark(
            @Valid CategoryForMark categoryForMark,
            @Valid Subcategory subcategory,
            Model model
    ) {
        subcategory.setCategoryForMarkId(categoryForMark);

        model.addAttribute("subcategory", subcategory);

        subcategoryRepo.save(subcategory);

        Iterable<Subcategory> subcategories = subcategoryRepo.findAll();

        model.addAttribute("subcategories", subcategories);

        return "redirect:/{modelCategoryId}/generation/bodyType/engineType/categoryForMark/subcategoryForMark/{Id}";
    }
}
