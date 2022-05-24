package com.example.AutoPartsStore.controller;

import com.example.AutoPartsStore.domain.Category;
import com.example.AutoPartsStore.domain.MarkCategory;
import com.example.AutoPartsStore.repo.MarkCategoryRepo;
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
public class MarkCategoryController {
    @Autowired
    private MarkCategoryRepo markCategoryRepo;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/markCategories")
    public String autoPartsStore(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<MarkCategory> markCategories;

        if (filter != null && !filter.isEmpty()) {
            markCategories = markCategoryRepo.findByMarkCategoryName(filter);
        } else {
            markCategories = markCategoryRepo.findAll();
        }

        model.addAttribute("markCategories", markCategories);
        model.addAttribute("filter", filter);
        return "markCategoriesPage";
    }

    @PostMapping("/markCategories")
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

        return "markCategoriesPage";
    }

    @PostMapping("{id}/removeMarkCategories")
    public String removeCategoryMark(@PathVariable(value = "id") long id) {
        MarkCategory markCategory = markCategoryRepo.findById(id).orElseThrow();
        markCategoryRepo.delete(markCategory);
        return "redirect:/markCategories";
    }
}
