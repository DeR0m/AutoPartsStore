package com.example.AutoPartsStore.service;

import com.example.AutoPartsStore.domain.*;
import com.example.AutoPartsStore.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Map;

@Service

@Transactional
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


}
