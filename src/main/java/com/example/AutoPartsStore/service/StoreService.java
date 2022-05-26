package com.example.AutoPartsStore.service;

import com.example.AutoPartsStore.domain.*;
import com.example.AutoPartsStore.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Service
public class StoreService {
    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private MarkCategoryRepo markCategoryRepo;

    @Autowired
    private SubcategoryRepo subcategoryRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private MarkModelRepo markModelRepo;

    @Autowired
    private ModelGenerationRepo modelGenerationRepo;

    @Autowired
    private EngineTypeRepo engineTypeRepo;

    @Autowired
    private ProductForMarkRepo productForMarkRepo;

    @Autowired
    private BodyTypeRepo bodyTypeRepo;

    public void saveCategory(Category category, String name, Map<String, String> model){
        category.setCategoryName(name);
        categoryRepo.save(category);
    }

    public void saveCategoryMark(MarkCategory markCategory, String name, Map<String, String> model){
        markCategory.setMarkCategoryName(name);
        markCategoryRepo.save(markCategory);
    }

    public void saveSubcategory(Subcategory subcategory, String name, Map<String, String> model){
        subcategory.setSubcategoryName(name);
        subcategoryRepo.save(subcategory);
    }

    public void saveProduct(Product product, String name, String description, String amount, String price, Map<String, String> model){
        product.setProductName(name);
        product.setProductDescription(description);
        product.setProductAmount(amount);
        product.setProductPrice(price);
        productRepo.save(product);
    }

    public void saveMarkModel(MarkModel markModel, String name, Map<String, String> model){
        markModel.setModelName(name);
        markModelRepo.save(markModel);
    }

    public void saveModelGeneration(ModelGeneration modelGeneration, String name, Map<String, String> model){
        modelGeneration.setGenerationModelName(name);
        modelGenerationRepo.save(modelGeneration);
    }

    public void saveBodyType(BodyType bodyType, String name, Map<String, String> model){
        bodyType.setBodyTypeName(name);
        bodyTypeRepo.save(bodyType);
    }

    public void saveEngineType(EngineType engineType, String name,String capacity,
                               String powerHp,
                               String engineName,
                               String fuelType,
                               Map<String, String> model){
        engineType.setEngineModel(name);
        engineType.setEngineCapacity(capacity);
        engineType.setPowerHp(powerHp);
        engineType.setEngineName(engineName);
        engineType.setFuelType(fuelType);
        engineTypeRepo.save(engineType);
    }

    public void saveProductForMark(ProductForMark productForMark, String name, String description, String amount, String price, Map<String, String> model){
        productForMark.setProductName(name);
        productForMark.setProductDescription(description);
        productForMark.setProductAmount(amount);
        productForMark.setProductPrice(price);
        productForMarkRepo.save(productForMark);
    }
}
