package com.example.AutoPartsStore.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
public class Subcategory {
    @Id
    @GeneratedValue
    private Long id;
    private String subcategoryName;
    private String filename;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category categoryId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_for_mark_id")
    private CategoryForMark categoryForMarkId;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "subcategoryId", cascade = CascadeType.ALL)
    private Set<Product> products;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "subcategoryId", cascade = CascadeType.ALL)
    private Set<ProductForMark> productForMarks;



    public Subcategory() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubcategoryName() {
        return subcategoryName;
    }

    public void setSubcategoryName(String subcategoryName) {
        this.subcategoryName = subcategoryName;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Category getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Category categoryId) {
        this.categoryId = categoryId;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public CategoryForMark getCategoryForMarkId() {
        return categoryForMarkId;
    }

    public void setCategoryForMarkId(CategoryForMark categoryForMarkId) {
        this.categoryForMarkId = categoryForMarkId;
    }

    public Set<ProductForMark> getProductForMarks() {
        return productForMarks;
    }

    public void setProductForMarks(Set<ProductForMark> productForMarks) {
        this.productForMarks = productForMarks;
    }
}
