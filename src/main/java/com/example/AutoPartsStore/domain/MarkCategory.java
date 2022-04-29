package com.example.AutoPartsStore.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
public class MarkCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String markCategoryName;
    private String markFilename;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "markCategoryId", cascade = CascadeType.ALL)
    private Set<MarkModel> markModels;

    public MarkCategory() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarkCategoryName() {
        return markCategoryName;
    }

    public void setMarkCategoryName(String markCategoryName) {
        this.markCategoryName = markCategoryName;
    }

    public String getMarkFilename() {
        return markFilename;
    }

    public void setMarkFilename(String markFilename) {
        this.markFilename = markFilename;
    }

    public Set<MarkModel> getMarkModels() {
        return markModels;
    }

    public void setMarkModels(Set<MarkModel> markModels) {
        this.markModels = markModels;
    }
}
