package com.example.AutoPartsStore.controller;

import com.example.AutoPartsStore.domain.Category;
import com.example.AutoPartsStore.domain.MarkCategory;
import com.example.AutoPartsStore.repo.CategoryRepo;
import com.example.AutoPartsStore.repo.MarkCategoryRepo;
import com.example.AutoPartsStore.service.StoreService;
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
import java.util.UUID;

@Controller
public class MainController {

    @Autowired
    private StoreService storeService;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private MarkCategoryRepo markCategoryRepo;

    @Value("${upload.path}")
    private String uploadPath;

    //вывод информации на главную
    @GetMapping("/")
    public String autoPartsStore(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<Category> categories = categoryRepo.findAll();
        Iterable<MarkCategory> markCategories;

        if (filter != null && !filter.isEmpty()) {
            markCategories = markCategoryRepo.findByMarkCategoryName(filter);
        } else {
            markCategories = markCategoryRepo.findAll();
        }

        model.addAttribute("categories", categories);
        model.addAttribute("markCategories", markCategories);
        model.addAttribute("filter", filter);
        return "autoPartsStore";
    }

    //post method для создания категорий товаров
    @PostMapping("/")
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

            model.addAttribute("category", null);

            categoryRepo.save(category);
        }

        Iterable<Category> categories = categoryRepo.findAll();

        model.addAttribute("categories", categories);

        return "autoPartsStore";
    }

    //post method для добавления категории по маркам
    @PostMapping("/markCategory")
    public String createMarkCategory(
            @Valid MarkCategory markCategory,
            BindingResult bindingResult,
            Model model,
            @RequestParam("fileForMarkCategory") MultipartFile fileForMarkCategory
    ) throws IOException {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            model.mergeAttributes(errorsMap);
            model.addAttribute("markCategory", markCategory);
        } else {

            if (fileForMarkCategory != null && !fileForMarkCategory.getOriginalFilename().isEmpty()) {
                File uploadForMarkDir = new File(uploadPath);

                if (!uploadForMarkDir.exists()) {
                    uploadForMarkDir.mkdir();
                }

                String uuidFile = UUID.randomUUID().toString();
                String resultFilenameForMark = uuidFile + "." + fileForMarkCategory.getOriginalFilename();

                fileForMarkCategory.transferTo(new File(uploadPath + "/" + resultFilenameForMark));

                markCategory.setMarkFilename(resultFilenameForMark);
            }

            model.addAttribute("markCategory", null);

            markCategoryRepo.save(markCategory);
        }

        Iterable<MarkCategory> markCategories = markCategoryRepo.findAll();

        model.addAttribute("markCategories", markCategories);

        return "redirect:/";
    }

    @PostMapping("{id}/remove")
    public String removeCategory(@PathVariable(value = "id") long id) {
        Category category = categoryRepo.findById(id).orElseThrow();
        categoryRepo.delete(category);
        return "redirect:/";
    }

    @PostMapping("{id}/edit")
    public String updateCategory(@PathVariable(value = "id") long id, Model model){
        Category category = categoryRepo.findById(id).orElseThrow();
        model.addAttribute("category", category);
        return "edit";
    }

    @PostMapping("{id}/removeMark")
    public String removeCategoryMark(@PathVariable(value = "id") long id) {
        MarkCategory markCategory = markCategoryRepo.findById(id).orElseThrow();
        markCategoryRepo.delete(markCategory);
        return "redirect:/";
    }

    @PostMapping("{id}/editMark")
    public String updateCategoryMark(@PathVariable(value = "id") long id, Model model){
        MarkCategory markCategory = markCategoryRepo.findById(id).orElseThrow();
        model.addAttribute("markCategory", markCategory);
        return "editMark";
    }

    @PostMapping("/editMark")
    public String saveCategory(
            @RequestParam String name,
            @RequestParam Map<String, String> form,
            @RequestParam("markCategoryId") MarkCategory markCategory) {
        storeService.saveCategory(markCategory, name, form);
        return "redirect:";
    }
}
