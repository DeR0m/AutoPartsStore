package com.example.AutoPartsStore.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
public class ModelGeneration {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String generationModelName;
    private String graduationYear;
    private String filename;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mark_model_id")
    private MarkModel markModelId;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "modelGenerationId", cascade = CascadeType.ALL)
    private Set<BodyType> bodyTypes;

    public ModelGeneration() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGenerationModelName() {
        return generationModelName;
    }

    public void setGenerationModelName(String generationModelName) {
        this.generationModelName = generationModelName;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public MarkModel getMarkModelId() {
        return markModelId;
    }

    public void setMarkModelId(MarkModel markModelId) {
        this.markModelId = markModelId;
    }

    public String getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(String graduationYear) {
        this.graduationYear = graduationYear;
    }
}

