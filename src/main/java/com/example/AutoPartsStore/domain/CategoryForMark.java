package com.example.AutoPartsStore.domain;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
public class CategoryForMark implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String categoryForMarkName;
    private String filename;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "categoryForMarkId", cascade = CascadeType.ALL)
    private Set<Subcategory> subcategory;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "engine_type_id")
    private EngineType engineTypeId;

    public CategoryForMark() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryForMarkName() {
        return categoryForMarkName;
    }

    public void setCategoryForMarkName(String categoryForMarkName) {
        this.categoryForMarkName = categoryForMarkName;
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

    public EngineType getEngineTypeId() {
        return engineTypeId;
    }

    public void setEngineTypeId(EngineType engineTypeId) {
        this.engineTypeId = engineTypeId;
    }

    @Override
    public String getAuthority() {
        return categoryForMarkName;
    }
}
