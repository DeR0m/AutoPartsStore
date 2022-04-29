package com.example.AutoPartsStore.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
public class MarkModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String modelName;
    private String filename;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mark_category_id")
    private MarkCategory markCategoryId;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "markModelId", cascade = CascadeType.ALL)
    private Set<ModelGeneration> modelGenerations;

    public MarkModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setMarkCategoryId(MarkCategory markCategoryId) {
        this.markCategoryId = markCategoryId;
    }

    public MarkCategory getMarkCategoryId() {
        return markCategoryId;
    }

    public Set<ModelGeneration> getModelGenerations() {
        return modelGenerations;
    }

    public void setModelGenerations(Set<ModelGeneration> modelGenerations) {
        this.modelGenerations = modelGenerations;
    }
}
