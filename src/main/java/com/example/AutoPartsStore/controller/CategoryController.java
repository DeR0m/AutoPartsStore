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

    @GetMapping("/categories")
    public String autoPartsStore(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<Category> categories;

        if (filter != null && !filter.isEmpty()) {
            categories = categoryRepo.findByCategoryName(filter);
        } else {
            categories = categoryRepo.findAll();
        }

        model.addAttribute("categories", categories);
        model.addAttribute("filter", filter);
        return "categoryMain";
    }

    //post method для создания категорий товаров
    @PostMapping("/categories")
    public String createCategory(
            @Valid Category category,
            BindingResult bindingResult,
            Model model,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            model.mergeAttributes(errorsMap);
            model.addAttribute("category", category);
        } else {

            saveFile(category, file);


            model.addAttribute("category", null);

            categoryRepo.save(category);
        }

        Iterable<Category> categories = categoryRepo.findAll();

        model.addAttribute("categories", categories);

        return "categoryMain";
    }

    //Переход на категорию
    @GetMapping("category/{id}")
    public String categoryMain(@PathVariable(value = "id") long id,
                               Model model) {
        Category category = categoryRepo.findById(id).orElseThrow();
        model.addAttribute("category", category);
        Set<Subcategory> subcategory = category.getSubcategory();
        model.addAttribute("subcategories", subcategory);

        return "subcategoryMain";
    }

    @PostMapping("category/{id}")
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

            saveFileSubcategory(subcategory, file);

            model.addAttribute("subcategory", null);

            subcategoryRepo.save(subcategory);
        }

        Iterable<Subcategory> subcategories = subcategoryRepo.findAll();

        model.addAttribute("subcategories", subcategories);

        return "redirect:/category/{id}";
    }

    private void saveFileSubcategory(@Valid Subcategory subcategory, @RequestParam("file") MultipartFile file) throws IOException {
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
    }

    private void saveFile(@Valid Category category, @RequestParam("file") MultipartFile file) throws IOException {
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));

            category.setFilename(resultFilename);

        }
    }


    @GetMapping("{id}/editCategory")
    public String updateCategory(@PathVariable(value = "id") long id, Model model) {
        Category category = categoryRepo.findById(id).orElseThrow();
        model.addAttribute("category", category);
        return "editCategory";
    }

    @PostMapping("/editCategory")
    public String saveCategory(
            @RequestParam("categoryId") Category category,
            @RequestParam("categoryName") String name,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        category.setCategoryName(name);
        saveFile(category, file);
        categoryRepo.save(category);

        return "redirect:";
    }

    @PostMapping("{id}/remove")
    public String removeCategoryMain(@PathVariable(value = "id") long id) {
        Category category = categoryRepo.findById(id).orElseThrow();
        categoryRepo.delete(category);
        return "redirect:/";
    }

    @PostMapping("{id}/removeCategory")
    public String removeCategory(@PathVariable(value = "id") long id) {
        Category category = categoryRepo.findById(id).orElseThrow();
        categoryRepo.delete(category);
        return "redirect:/categories";
    }

    @PostMapping("category/{categoryId}/{subcategoryId}/removeSubcategory")
    public String removeSubcategory(@PathVariable(value = "subcategoryId") long id) {
        Subcategory subcategory = subcategoryRepo.findById(id).orElseThrow();
        subcategoryRepo.delete(subcategory);
        return "redirect:/category/{categoryId}";
    }

    @GetMapping("category/{id}/editSubcategory")
    public String updateSubcategory(@PathVariable(value = "id") long id, Model model) {
        Subcategory subcategory = subcategoryRepo.findById(id).orElseThrow();
        model.addAttribute("subcategory", subcategory);
        return "subcategoryEdit";
    }

    @PostMapping("/editSubcategory")
    public String saveSubcategory(
            @RequestParam("subcategoryId") Subcategory subcategory,
            @RequestParam("subcategoryName") String name,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        subcategory.setSubcategoryName(name);
        saveFileSubcategory(subcategory, file);
        subcategoryRepo.save(subcategory);
        return "redirect:";
    }
}
