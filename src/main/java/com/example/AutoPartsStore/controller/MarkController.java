package com.example.AutoPartsStore.controller;

import com.example.AutoPartsStore.domain.*;
import com.example.AutoPartsStore.repo.*;
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

    @Autowired
    private ModelGenerationRepo modelGenerationRepo;

    @Autowired
    private BodyTypeRepo bodyTypeRepo;

    @Autowired
    private EngineTypeRepo engineTypeRepo;

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

    //создание модельного ряда
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

    //переход на поколения автомобиля
    @GetMapping("{modelCategoryId}/generation/{Id}")
    public String modelGeneration(
            @PathVariable(value = "Id") long id,
            Model model) {

        MarkModel markModel = markModelRepo.findById(id).orElseThrow();
        model.addAttribute("markModel", markModel);
        Set<ModelGeneration> modelGeneration = markModel.getModelGenerations();
        model.addAttribute("modelGenerations", modelGeneration);

        return "modelGeneration";
    }

    //создание поколения
    @PostMapping("{modelCategoryId}/generation/{Id}")
    public String createModelGeneration(
            @Valid MarkModel markModel,
            @Valid ModelGeneration modelGeneration,
            BindingResult bindingResult,
            Model model,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        modelGeneration.setMarkModelId(markModel);
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            model.mergeAttributes(errorsMap);
            model.addAttribute("modelGeneration", modelGeneration);
        } else {

            if (file != null && !file.getOriginalFilename().isEmpty()) {
                File uploadDir = new File(uploadPath);

                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }

                String uuidFile = UUID.randomUUID().toString();
                String resultFilename = uuidFile + "." + file.getOriginalFilename();

                file.transferTo(new File(uploadPath + "/" + resultFilename));

                modelGeneration.setFilename(resultFilename);
            }

            model.addAttribute("modelGeneration", null);

            modelGenerationRepo.save(modelGeneration);
        }


        Iterable<ModelGeneration> modelGenerations = modelGenerationRepo.findAll();


        model.addAttribute("modelGenerations", modelGeneration);

        return "redirect:/{modelCategoryId}/generation/{Id}";
    }

    //    переход на выбор тип кузова автомобиля
    @GetMapping("{modelCategoryId}/generation/bodyType/{Id}")
    public String bodyType(
            @PathVariable(value = "Id") long id,
            Model model) {

        ModelGeneration modelGeneration = modelGenerationRepo.findById(id).orElseThrow();
        model.addAttribute("modelGeneration", modelGeneration);
        Set<BodyType> bodyType = modelGeneration.getBodyTypes();
        model.addAttribute("bodyTypes", bodyType);

        return "bodyType";
    }

    //создание типа кузова
    @PostMapping("{modelCategoryId}/generation/bodyType/{Id}")
    public String createBodyType(
            @Valid ModelGeneration modelGeneration,
            @Valid BodyType bodyType,
            BindingResult bindingResult,
            Model model,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        bodyType.setModelGenerationId(modelGeneration);
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            model.mergeAttributes(errorsMap);
            model.addAttribute("bodyType", bodyType);
        } else {

            if (file != null && !file.getOriginalFilename().isEmpty()) {
                File uploadDir = new File(uploadPath);

                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }

                String uuidFile = UUID.randomUUID().toString();
                String resultFilename = uuidFile + "." + file.getOriginalFilename();

                file.transferTo(new File(uploadPath + "/" + resultFilename));

                bodyType.setFilename(resultFilename);
            }

            model.addAttribute("bodyType", null);

            bodyTypeRepo.save(bodyType);
        }


        Iterable<BodyType> bodyTypes = bodyTypeRepo.findAll();


        model.addAttribute("bodyTypes", bodyTypes);

        return "redirect:/{modelCategoryId}/generation/bodyType/{Id}";
    }

    @GetMapping("{modelCategoryId}/generation/bodyType/engineType/{Id}")
    public String engineType(
            @PathVariable(value = "Id") long id,
            Model model) {
        BodyType bodyType = bodyTypeRepo.findById(id).orElseThrow();
        model.addAttribute("bodyType", bodyType);
        Set<EngineType> engineType = bodyType.getEngineTypes();
        model.addAttribute("engineTypes", engineType);
        return "engineType";
    }
}
