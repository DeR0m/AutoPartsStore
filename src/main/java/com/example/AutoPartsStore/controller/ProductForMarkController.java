package com.example.AutoPartsStore.controller;

import com.example.AutoPartsStore.domain.*;
import com.example.AutoPartsStore.repo.ProductForMarkRepo;
import com.example.AutoPartsStore.repo.ProductRepo;
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
public class ProductForMarkController {
    @Autowired
    private SubcategoryRepo subcategoryRepo;

    @Autowired
    private ProductForMarkRepo productForMarkRepo;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("{modelCategoryId}/generation/bodyType/engineType/categoryForMark/subcategoryForMark/productForMark/{Id}")
    public String categoryMain(@PathVariable(value = "Id") long id,
                               Model model) {
        Subcategory subcategory = subcategoryRepo.findById(id).orElseThrow();
        model.addAttribute("subcategory", subcategory);

        CategoryForMark categoryForMark = subcategory.getCategoryForMarkId();
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


        Set<ProductForMark> productForMark = subcategory.getProductForMarks();
        model.addAttribute("productForMarks", productForMark);

        return "productForMark";
    }

    @PostMapping("{modelCategoryId}/generation/bodyType/engineType/categoryForMark/subcategoryForMark/productForMark/{Id}")
    public String createSubcategory(
            @Valid Subcategory subcategory,
            @Valid ProductForMark productForMark,
            BindingResult bindingResult,
            Model model,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        productForMark.setSubcategoryId(subcategory);
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            model.mergeAttributes(errorsMap);
            model.addAttribute("productForMark", productForMark);
        } else {

            if (file != null && !file.getOriginalFilename().isEmpty()) {
                File uploadDir = new File(uploadPath);

                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }

                String uuidFile = UUID.randomUUID().toString();
                String resultFilename = uuidFile + "." + file.getOriginalFilename();

                file.transferTo(new File(uploadPath + "/" + resultFilename));

                productForMark.setFilename(resultFilename);
            }

            model.addAttribute("productForMark", null);

            productForMarkRepo.save(productForMark);
        }

        Iterable<ProductForMark> productForMarks = productForMarkRepo.findAll();

        model.addAttribute("productForMarks", productForMarks);

        return "redirect:/{modelCategoryId}/generation/bodyType/engineType/categoryForMark/subcategoryForMark/productForMark/{Id}";
    }
}
