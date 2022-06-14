package com.example.AutoPartsStore.controller;

import com.example.AutoPartsStore.domain.*;
import com.example.AutoPartsStore.repo.*;
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
import java.util.Set;
import java.util.UUID;

@Controller
public class MarkController {
    @Autowired
    private StoreService storeService;

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
    @GetMapping("model/{Id}")
    public String categoryMain(@PathVariable(value = "Id") long id,
                               Model model) {
        MarkCategory markCategory = markCategoryRepo.findById(id).orElseThrow();
        model.addAttribute("markCategory", markCategory);
        Set<MarkModel> markModel = markCategory.getMarkModels();
        model.addAttribute("markModels", markModel);

        return "markModel";
    }

    //создание модельного ряда
    @PostMapping("model/{Id}")
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

            saveFileModel(markModel, file);

            model.addAttribute("markModel", null);

            markModelRepo.save(markModel);
        }


        Iterable<MarkModel> markModels = markModelRepo.findAll();


        model.addAttribute("markModels", markModel);

        return "redirect:/model/{Id}";
    }

    private void saveFileModel(MarkModel markModel, MultipartFile file) throws IOException {
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
    }

    //переход на поколения автомобиля
    @GetMapping("{modelCategoryId}/generation/{Id}")
    public String modelGeneration(
            @PathVariable(value = "Id") long id,
            Model model) {
        MarkModel markModel = markModelRepo.findById(id).orElseThrow();
        model.addAttribute("markModel", markModel);

        MarkCategory markCategory = markModel.getMarkCategoryId();
        model.addAttribute("markCategory", markCategory);

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

            saveFileModelGeneration(modelGeneration, file);

            model.addAttribute("modelGeneration", null);

            modelGenerationRepo.save(modelGeneration);
        }


        Iterable<ModelGeneration> modelGenerations = modelGenerationRepo.findAll();


        model.addAttribute("modelGenerations", modelGeneration);

        return "redirect:/{modelCategoryId}/generation/{Id}";
    }

    private void saveFileModelGeneration(ModelGeneration modelGeneration, MultipartFile file) throws IOException {
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
    }

    //    переход на выбор тип кузова автомобиля
    @GetMapping("{modelCategoryId}/generation/bodyType/{Id}")
    public String bodyType(
            @PathVariable(value = "Id") long id,
            Model model) {
        ModelGeneration modelGeneration = modelGenerationRepo.findById(id).orElseThrow();
        model.addAttribute("modelGeneration", modelGeneration);

        MarkModel markModel = modelGeneration.getMarkModelId();
        model.addAttribute("markModel", markModel);

        MarkCategory markCategory = markModel.getMarkCategoryId();
        model.addAttribute("markCategory", markCategory);

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

            saveFileBodyType(bodyType, file);

            model.addAttribute("bodyType", null);

            bodyTypeRepo.save(bodyType);
        }


        Iterable<BodyType> bodyTypes = bodyTypeRepo.findAll();


        model.addAttribute("bodyTypes", bodyTypes);

        return "redirect:/{modelCategoryId}/generation/bodyType/{Id}";
    }

    private void saveFileBodyType(BodyType bodyType, MultipartFile file) throws IOException {
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
    }

    @GetMapping("{modelCategoryId}/generation/bodyType/engineType/{Id}")
    public String engineType(
            @PathVariable(value = "Id") long id,
            Model model) {
        BodyType bodyType = bodyTypeRepo.findById(id).orElseThrow();
        model.addAttribute("bodyType", bodyType);

        ModelGeneration modelGeneration = bodyType.getModelGenerationId();
        model.addAttribute("modelGeneration", modelGeneration);

        MarkModel markModel = modelGeneration.getMarkModelId();
        model.addAttribute("markModel", markModel);

        MarkCategory markCategory = markModel.getMarkCategoryId();
        model.addAttribute("markCategory", markCategory);

        Set<EngineType> engineType = bodyType.getEngineTypes();
        model.addAttribute("engineTypes", engineType);
        return "engineType";
    }

    //метод создания типа двигателя
    @PostMapping("{modelCategoryId}/generation/bodyType/engineType/{Id}")
    public String createEngineType(
            @Valid BodyType bodyType,
            @Valid EngineType engineType,
            Model model
    ) {
        engineType.setBodyTypeId(bodyType);

        model.addAttribute("engineType", engineType);

        engineTypeRepo.save(engineType);

        Iterable<EngineType> engineTypes = engineTypeRepo.findAll();

        model.addAttribute("engineTypes", engineTypes);

        return "redirect:/{modelCategoryId}/generation/bodyType/engineType/{Id}";
    }

    @GetMapping("model/{id}/editMarkModel")
    public String updateMarkModel(@PathVariable(value = "id") long id, Model model) {
        MarkModel markModel = markModelRepo.findById(id).orElseThrow();
        model.addAttribute("markModel", markModel);
        return "markModelEdit";
    }

    @PostMapping("/editMarkModel")
    public String saveMarkModel(
            @RequestParam("markModelId") MarkModel markModel,
            @RequestParam("modelName") String name,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        markModel.setModelName(name);
        saveFileModel(markModel, file);
        markModelRepo.save(markModel);
        return "redirect:";
    }

    @GetMapping("model/generation/{id}/editModelGeneration")
    public String updateModelGeneration(@PathVariable(value = "id") long id, Model model) {
        ModelGeneration modelGeneration = modelGenerationRepo.findById(id).orElseThrow();
        model.addAttribute("modelGeneration", modelGeneration);
        return "modelGenerationEdit";
    }

    @PostMapping("/editModelGeneration")
    public String saveModelGeneration(
            @RequestParam("modelGenerationId") ModelGeneration modelGeneration,
            @RequestParam("generationModelName") String name,
            @RequestParam("graduationYear") String year,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        modelGeneration.setGenerationModelName(name);
        modelGeneration.setGraduationYear(year);
        saveFileModelGeneration(modelGeneration, file);
        modelGenerationRepo.save(modelGeneration);
        return "redirect:";
    }

    @GetMapping("model/generation/bodyType/{id}/editBodyType")
    public String updateBodyType(@PathVariable(value = "id") long id, Model model) {
        BodyType bodyType = bodyTypeRepo.findById(id).orElseThrow();
        model.addAttribute("bodyType", bodyType);
        return "bodyTypeEdit";
    }

    @PostMapping("/editBodyType")
    public String saveBodyType(
            @RequestParam("bodyTypeId") BodyType bodyType,
            @RequestParam("bodyTypeName") String name,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        bodyType.setBodyTypeName(name);
        saveFileBodyType(bodyType, file);
        bodyTypeRepo.save(bodyType);
        return "redirect:";
    }

    @GetMapping("model/generation/bodyType/engineType/{id}/editEngineType")
    public String updateEngineType(@PathVariable(value = "id") long id, Model model) {
        EngineType engineType = engineTypeRepo.findById(id).orElseThrow();
        model.addAttribute("engineType", engineType);
        return "engineTypeEdit";
    }

    @PostMapping("/editEngineType")
    public String saveEngineType(
            @RequestParam("engineTypeId") EngineType engineType,
            @RequestParam("engineModel") String name,
            @RequestParam("engineCapacity") String capacity,
            @RequestParam("powerHp") String powerHp,
            @RequestParam("engineName") String engineName,
            @RequestParam("fuelType") String fuelType
            ) {
        engineType.setEngineModel(name);
        engineType.setEngineCapacity(capacity);
        engineType.setPowerHp(powerHp);
        engineType.setEngineName(engineName);
        engineType.setFuelType(fuelType);
        engineTypeRepo.save(engineType);
        return "redirect:";
    }
}
