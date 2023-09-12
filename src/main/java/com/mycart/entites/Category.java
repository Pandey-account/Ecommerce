
package com.mycart.entites;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int catId;
    private String catTitle;
    private String catDescription;
    @OneToMany(mappedBy = "category")
    private List<Product>products = new ArrayList<>();

    public Category(int catId, String catTitle, String catDescription, List<Product> products) {
        this.catId = catId;
        this.catTitle = catTitle;
        this.catDescription = catDescription;
        this.products = products;
    }

    public Category(String catTitle, String catDescription, List<Product> products) {
        this.catTitle = catTitle;
        this.catDescription = catDescription;
        this.products = products;
    }

    public Category() {
    }

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public String getCatTitle() {
        return catTitle;
    }

    public void setCatTitle(String catTitle) {
        this.catTitle = catTitle;
    }

    public String getCatDescription() {
        return catDescription;
    }

    public void setCatDescription(String catDescription) {
        this.catDescription = catDescription;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Category{" + "catId=" + catId + ", catTitle=" + catTitle + ", catDescription=" + catDescription + ", products=" + products + '}';
    }
    
}

