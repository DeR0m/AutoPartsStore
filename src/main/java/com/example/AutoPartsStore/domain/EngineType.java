package com.example.AutoPartsStore.domain;

import javax.persistence.*;

@Entity
@Table
public class EngineType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String engineModel;
    private String engineCapacity;
    private int powerHp; // в л.с. (или добавить новую переменную или сделать калькулятор для перевода
    private String engineName;
    private String fuelType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "body_type_id")
    private BodyType bodyTypeId;

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

    public int getPowerHp() {
        return powerHp;
    }

    public void setPowerHp(int powerHp) {
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
}
