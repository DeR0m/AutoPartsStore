package com.example.AutoPartsStore.controller;

import com.example.AutoPartsStore.domain.Product;
import com.example.AutoPartsStore.domain.ProductForMark;
import com.example.AutoPartsStore.domain.Subcategory;
import com.example.AutoPartsStore.domain.User;
import com.example.AutoPartsStore.repo.ProductForMarkRepo;
import com.example.AutoPartsStore.repo.ProductRepo;
import com.example.AutoPartsStore.repo.UserRepo;
import com.example.AutoPartsStore.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Set;

@Controller
public class BasketController {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private ProductForMarkRepo productForMarkRepo;

    @Autowired
    private UserRepo userRepo;

    @PostMapping("category/product/{categoryId}/{id}/addBasket")
    public String addBasket(@PathVariable(value = "id") long id, @AuthenticationPrincipal User user) {
        Product product = productRepo.findById(id).orElseThrow();
        user.getProducts().add(product);
        product.getUsr().add(user);
        userRepo.save(user);
        productRepo.save(product);
        return "redirect:/category/product/{categoryId}";
    }

    @PostMapping("model/generation/bodyType/engineType/categoryForMark/subcategoryForMark/productForMark/{categoryId}/{id}/addBasket")
    public String addBasketForMark(@PathVariable(value = "id") long id, @AuthenticationPrincipal User user) {
        ProductForMark productForMark = productForMarkRepo.findById(id).orElseThrow();
        user.getProductForMarks().add(productForMark);
        productForMark.getUsr().add(user);
        userRepo.save(user);
        productForMarkRepo.save(productForMark);
        return "redirect:/model/generation/bodyType/engineType/categoryForMark/subcategoryForMark/productForMark/{categoryId}";
    }

    @GetMapping("user/basket")
    public String productList(Model model, @AuthenticationPrincipal User user){
        model.addAttribute("user", user);
        Set<Product> product = user.getProducts();
        model.addAttribute("products", product);

    return "basket";
    }
}
