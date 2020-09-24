package com.albumbazaar.albumbazar.utilities;

import com.albumbazaar.albumbazar.model.Cover;
import com.albumbazaar.albumbazar.model.Paper;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.HashSet;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AllProducts {
    private List<String> productCategories;
    private List<Cover> covers;
    private List<Paper> papers;
    private HashSet<String> sizes;

    public HashSet<String> getSizes() {
        return sizes;
    }

    public void setSizes(HashSet<String> sizes) {
        this.sizes = sizes;
    }

    public List<String> getProductCategories() {
        return productCategories;
    }

    public void setProductCategories(List<String> productCategories) {
        this.productCategories = productCategories;
    }

    public List<Cover> getCovers() {
        return covers;
    }

    public void setCovers(List<Cover> covers) {
        this.covers = covers;
    }

    public List<Paper> getPapers() {
        return papers;
    }

    public void setPapers(List<Paper> papers) {
        this.papers = papers;
    }

    @Override
    public String toString() {
        return "AllProducts{" +
                "productCategories=" + productCategories +
                ", covers=" + covers +
                ", papers=" + papers +
                ", sizes=" + sizes +
                '}';
    }
}
