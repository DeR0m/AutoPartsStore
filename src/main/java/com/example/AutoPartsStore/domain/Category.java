package com.example.AutoPartsStore.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String categoryName;
    private String filename;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "categoryId", cascade = CascadeType.ALL)
    private Set<Subcategory> subcategory;

    public Category() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Set<Subcategory> getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(Set<Subcategory> subcategory) {
        this.subcategory = subcategory;
    }
}
