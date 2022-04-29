package com.example.AutoPartsStore.domain;

import javax.persistence.*;

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
}
