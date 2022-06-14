package com.example.AutoPartsStore.controller;

import com.example.AutoPartsStore.domain.*;
import com.example.AutoPartsStore.repo.CategoryForMarkRepo;
import com.example.AutoPartsStore.repo.CategoryRepo;
import com.example.AutoPartsStore.repo.EngineTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Controller
public class CategoryForMarkController {
    @Autowired
    private CategoryForMarkRepo categoryForMarkRepo;

    @Autowired
    private EngineTypeRepo engineTypeRepo;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("{modelCategoryId}/generation/bodyType/engineType/categoryForMark/{Id}")
    public String subcategoryForMark(
            @PathVariable(value = "Id") long id,
            Model model) {
        EngineType engineType = engineTypeRepo.findById(id).orElseThrow();
        model.addAttribute("engineType", engineType);

        BodyType bodyType = engineType.getBodyTypeId();
        model.addAttribute("bodyType", bodyType);

        ModelGeneration modelGeneration = bodyType.getModelGenerationId();
        model.addAttribute("modelGeneration", modelGeneration);

        MarkModel markModel = modelGeneration.getMarkModelId();
        model.addAttribute("markModel", markModel);

        MarkCategory markCategory = markModel.getMarkCategoryId();
        model.addAttribute("markCategory", markCategory);

        Set<CategoryForMark> categoryForMark = engineType.getCategoryForMarks();
        model.addAttribute("categoryForMarks", categoryForMark);
        return "categoryForMark";
    }

    @PostMapping("{modelCategoryId}/generation/bodyType/engineType/categoryForMark/{Id}")
    public String createSubcategoryForModel(
            @Valid EngineType engineType,
            @Valid CategoryForMark categoryForMark,
            BindingResult bindingResult,
            Model model,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        categoryForMark.setEngineTypeId(engineType);
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            model.mergeAttributes(errorsMap);
            model.addAttribute("categoryForMark", categoryForMark);
        } else {

            saveFile(categoryForMark, file);

            model.addAttribute("categoryForMark", null);

            categoryForMarkRepo.save(categoryForMark);
        }


        Iterable<CategoryForMark> categoryForMarks = categoryForMarkRepo.findAll();


        model.addAttribute("categoryForMarks", categoryForMarks);

        return "redirect:/{modelCategoryId}/generation/bodyType/engineType/categoryForMark/{Id}";
    }

    private void saveFile(CategoryForMark categoryForMark, MultipartFile file) throws IOException {
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));

            categoryForMark.setFilename(resultFilename);
        }
    }

    @GetMapping("{modelCategoryId}/generation/bodyType/engineType/categoryForMark/{id}/editCategoryForMark")
    public String updateCategoryForMark(@PathVariable(value = "id") long id, Model model) {
        CategoryForMark categoryForMark = categoryForMarkRepo.findById(id).orElseThrow();
        model.addAttribute("categoryForMark", categoryForMark);
        return "categoryForMarkEdit";
    }

    @PostMapping("/editCategoryForMark")
    public String saveCategoryForMark(
            @RequestParam("categoryForMarkId") CategoryForMark categoryForMark,
            @RequestParam("categoryForMarkName") String name,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        categoryForMark.setCategoryForMarkName(name);
        saveFile(categoryForMark, file);
        categoryForMarkRepo.save(categoryForMark);

        return "redirect:";
    }
}
