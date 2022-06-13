package com.example.AutoPartsStore.domain;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
public class EngineType implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String engineModel;
    private String engineCapacity;
    private String powerHp; // в л.с. (или добавить новую переменную или сделать калькулятор для перевода
    private String engineName;
    private String fuelType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "body_type_id")
    private BodyType bodyTypeId;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "engineTypeId", cascade = CascadeType.ALL)
    private Set<CategoryForMark> categoryForMarks;

    public EngineType() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEngineModel() {
        return engineModel;
    }

    public void setEngineModel(String engineModel) {
        this.engineModel = engineModel;
    }

    public String getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(String engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public String getPowerHp() {
        return powerHp;
    }

    public void setPowerHp(String powerHp) {
        this.powerHp = powerHp;
    }

    public String getEngineName() {
        return engineName;
    }

    public void setEngineName(String engineName) {
        this.engineName = engineName;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public BodyType getBodyTypeId() {
        return bodyTypeId;
    }

    public void setBodyTypeId(BodyType bodyTypeId) {
        this.bodyTypeId = bodyTypeId;
    }

    public Set<CategoryForMark> getCategoryForMarks() {
        return categoryForMarks;
    }

    public void setCategoryForMarks(Set<CategoryForMark> categoryForMarks) {
        this.categoryForMarks = categoryForMarks;
    }

    @Override
    public String getAuthority() {
        return engineModel;
    }
}
