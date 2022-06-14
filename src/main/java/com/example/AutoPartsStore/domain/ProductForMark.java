package com.example.AutoPartsStore.domain;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class ProductForMark implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String productName;
    private String productDescription;
    private String productAmount;
    private String productPrice;
    private String filename;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subcategory_id")
    private Subcategory subcategoryId;

    @ManyToMany(mappedBy = "productForMarks", cascade = CascadeType.ALL)
    private List<User> usr;

    public ProductForMark() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(String productAmount) {
        this.productAmount = productAmount;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Subcategory getSubcategoryId() {
        return subcategoryId;
    }

    public void setSubcategoryId(Subcategory subcategoryId) {
        this.subcategoryId = subcategoryId;
    }

    public List<User> getUsr() {
        return usr;
    }

    public void setUsr(List<User> usr) {
        this.usr = usr;
    }

    @Override
    public String getAuthority() {
        return productName;
    }
}
