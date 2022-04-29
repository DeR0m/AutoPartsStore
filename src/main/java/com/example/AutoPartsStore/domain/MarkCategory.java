package com.example.AutoPartsStore.domain;

import javax.persistence.*;

@Entity
@Table(name = "markCategory")
public class MarkCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String markCategoryName;
    private String markFilename;

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
}
