package com.example.AutoPartsStore.controller;

import com.example.AutoPartsStore.domain.Category;
import com.example.AutoPartsStore.domain.Subcategory;
import com.example.AutoPartsStore.repo.CategoryRepo;
import com.example.AutoPartsStore.repo.SubcategoryRepo;
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
public class CategoryController {
    @Autowired
    private SubcategoryRepo subcategoryRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Value("${upload.path}")
    private String uploadPath;

    //Переход на категорию
    @GetMapping("{Id}")
    public String categoryMain(@PathVariable(value = "Id") long id,
                               Model model) {
        Category category = categoryRepo.findById(id).orElseThrow();
        model.addAttribute("category", category);
        Set<Subcategory> subcategory = category.getSubcategory();
        model.addAttribute("subcategories", subcategory);

        return "categoryMain";
    }

    @PostMapping("{Id}")
    public String createSubcategory(
            @Valid Category category,
            @Valid Subcategory subcategory,
            BindingResult bindingResult,
            Model model,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
      subcategory.setCategoryId(category);
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            model.mergeAttributes(errorsMap);
            model.addAttribute("subcategory", subcategory);
        } else {

            if (file != null && !file.getOriginalFilename().isEmpty()) {
                File uploadDir = new File(uploadPath);

                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }

                String uuidFile = UUID.randomUUID().toString();
                String resultFilename = uuidFile + "." + file.getOriginalFilename();

                file.transferTo(new File(uploadPath + "/" + resultFilename));

                subcategory.setFilename(resultFilename);
            }

            model.addAttribute("subcategory", null);

            subcategoryRepo.save(subcategory);
        }


        Iterable<Subcategory> subcategories = subcategoryRepo.findAll();


        model.addAttribute("subcategories", subcategories);

        return "redirect:/{Id}";
    }
}
