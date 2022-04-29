package com.example.AutoPartsStore.controller;

import com.example.AutoPartsStore.domain.Category;
import com.example.AutoPartsStore.domain.MarkCategory;
import com.example.AutoPartsStore.domain.MarkModel;
import com.example.AutoPartsStore.domain.Subcategory;
import com.example.AutoPartsStore.repo.CategoryRepo;
import com.example.AutoPartsStore.repo.MarkCategoryRepo;
import com.example.AutoPartsStore.repo.MarkModelRepo;
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
public class MarkController {
    @Autowired
    private MarkCategoryRepo markCategoryRepo;

    @Autowired
    private MarkModelRepo markModelRepo;

    @Value("${upload.path}")
    private String uploadPath;

    //Переход на марку автомобиля
    @GetMapping("{Id}/model")
    public String categoryMain(@PathVariable(value = "Id") long id,
                               Model model) {
        MarkCategory markCategory = markCategoryRepo.findById(id).orElseThrow();
        model.addAttribute("markCategory", markCategory);
        Set<MarkModel> markModel = markCategory.getMarkModels();
        model.addAttribute("markModels", markModel);

        return "markModel";
    }

    @PostMapping("{Id}/model")
    public String createModel(
            @Valid MarkCategory markCategory,
            @Valid MarkModel markModel,
            BindingResult bindingResult,
            Model model,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        markModel.setMarkCategoryId(markCategory);
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            model.mergeAttributes(errorsMap);
            model.addAttribute("markModel", markModel);
        } else {

            if (file != null && !file.getOriginalFilename().isEmpty()) {
                File uploadDir = new File(uploadPath);

                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }

                String uuidFile = UUID.randomUUID().toString();
                String resultFilename = uuidFile + "." + file.getOriginalFilename();

                file.transferTo(new File(uploadPath + "/" + resultFilename));

                markModel.setFilename(resultFilename);
            }

            model.addAttribute("markModel", null);

            markModelRepo.save(markModel);
        }


        Iterable<MarkModel> markModels = markModelRepo.findAll();


        model.addAttribute("markModels", markModel);

        return "redirect:/{Id}/model";
    }
}
