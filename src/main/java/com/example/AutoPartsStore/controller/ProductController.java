package com.example.AutoPartsStore.controller;

import com.example.AutoPartsStore.domain.Category;
import com.example.AutoPartsStore.domain.Product;
import com.example.AutoPartsStore.domain.Subcategory;
import com.example.AutoPartsStore.domain.User;
import com.example.AutoPartsStore.repo.ProductRepo;
import com.example.AutoPartsStore.repo.SubcategoryRepo;
import com.example.AutoPartsStore.repo.UserRepo;
import com.example.AutoPartsStore.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.PreRemove;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Controller
public class ProductController {
    @Autowired
    private SubcategoryRepo subcategoryRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private StoreService storeService;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("category/product/{id}")
    public String productMain(@RequestParam(required = false, defaultValue = "") String filter, @PathVariable(value = "id") long id,
                              Model model) {
        Subcategory subcategory = subcategoryRepo.findById(id).orElseThrow();
        Iterable<Product> product1 = productRepo.findAll();
        Long l = subcategory.getId();
        System.out.println(l);
        model.addAttribute("subcategory", subcategory);
        Set<Product> product = subcategory.getProducts();
        if (filter != null && !filter.isEmpty()) {
            product = productRepo.findByProductName(filter);
        } else {
            product = subcategory.getProducts();
        }


        model.addAttribute("products", product);
        model.addAttribute("filter", filter);

        return "product";
    }

    @PostMapping("category/product/{id}")
    public String createProduct(
            @Valid Subcategory subcategory,
            @Valid Product product,
            BindingResult bindingResult,
            Model model,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        product.setSubcategoryId(subcategory);
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            model.mergeAttributes(errorsMap);
            model.addAttribute("product", product);
        } else {


            saveFile(product, file);

            model.addAttribute("product", null);

            productRepo.save(product);
        }

        Iterable<Product> products = productRepo.findAll();

        model.addAttribute("products", products);

        return "redirect:/category/product/{id}";
    }

    private void saveFile(@Valid Product product, @RequestParam("file") MultipartFile file) throws IOException {
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));

            product.setFilename(resultFilename);

        }
    }


    @PostMapping("category/product/{subcategoryId}/{productId}/productRemove")
    public String productRemove(@PathVariable(value = "productId") long id) {
        Product product = productRepo.findById(id).orElseThrow();
        productRepo.delete(product);
        System.out.println("element delete");
        return "redirect:/category/product/{subcategoryId}";
    }


    @GetMapping("category/product/{id}/productEdit")
    public String updateProduct(@PathVariable(value = "id") long id, Model model) {
        Product product = productRepo.findById(id).orElseThrow();
        model.addAttribute("product", product);
        return "productEdit";
    }

    @PostMapping("/editProduct")
    public String saveProduct(
            @RequestParam("productId") Product product,
            @RequestParam("productName") String name,
            @RequestParam("productDescription") String description,
            @RequestParam("productAmount") String amount,
            @RequestParam("productPrice") String price,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        product.setProductName(name);
        product.setProductDescription(description);
        product.setProductAmount(amount);
        product.setProductPrice(price);
        saveFile(product, file);
        productRepo.save(product);
        return "redirect:";
    }
}
