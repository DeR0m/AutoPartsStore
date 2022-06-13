package com.example.AutoPartsStore.domain;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
public class BodyType implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String bodyTypeName;
    private String filename;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "model_generation_id")
    private ModelGeneration modelGenerationId;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "bodyTypeId", cascade = CascadeType.ALL)
    private Set<EngineType> engineTypes;

    public BodyType() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBodyTypeName() {
        return bodyTypeName;
    }

    public void setBodyTypeName(String bodyTypeName) {
        this.bodyTypeName = bodyTypeName;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public ModelGeneration getModelGenerationId() {
        return modelGenerationId;
    }

    public void setModelGenerationId(ModelGeneration modelGenerationId) {
        this.modelGenerationId = modelGenerationId;
    }

    public Set<EngineType> getEngineTypes() {
        return engineTypes;
    }

    public void setEngineTypes(Set<EngineType> engineTypes) {
        this.engineTypes = engineTypes;
    }

    @Override
    public String getAuthority() {
        return bodyTypeName;
    }
}
