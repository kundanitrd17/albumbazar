package com.albumbazaar.albumbazar.form.order;

import java.util.Arrays;

public class OrderDetailForm {

    private String companyName;
    private String albumType;
    private String albumSize;
    private String coverQuality;
    private String coverType;
    private String coverPrice;
    private String sheetType[];
    private String photoPerSheet[];
    private String sheetRange[];
    private String sheetPrice[];
    private String description;
    private String branchId;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAlbumType() {
        return albumType;
    }

    public void setAlbumType(String albumType) {
        this.albumType = albumType;
    }

    public String getAlbumSize() {
        return albumSize;
    }

    public void setAlbumSize(String albumSize) {
        this.albumSize = albumSize;
    }

    public String getCoverQuality() {
        return coverQuality;
    }

    public void setCoverQuality(String coverQuality) {
        this.coverQuality = coverQuality;
    }

    public String getCoverType() {
        return coverType;
    }

    public void setCoverType(String coverType) {
        this.coverType = coverType;
    }

    public String getCoverPrice() {
        return coverPrice;
    }

    public void setCoverPrice(String coverPrice) {
        this.coverPrice = coverPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getSheetType() {
        return sheetType;
    }

    public void setSheetType(String[] sheetType) {
        this.sheetType = sheetType;
    }

    public String[] getPhotoPerSheet() {
        return photoPerSheet;
    }

    public void setPhotoPerSheet(String[] photoPerSheet) {
        this.photoPerSheet = photoPerSheet;
    }

    public String[] getSheetRange() {
        return sheetRange;
    }

    public void setSheetRange(String[] sheetRange) {
        this.sheetRange = sheetRange;
    }

    public String[] getSheetPrice() {
        return sheetPrice;
    }

    public void setSheetPrice(String[] sheetPrice) {
        this.sheetPrice = sheetPrice;
    }

    @Override
    public String toString() {
        return "OrderDetailForm [albumSize=" + albumSize + ", albumType=" + albumType + ", branchId=" + branchId
                + ", companyName=" + companyName + ", coverPrice=" + coverPrice + ", coverQuality=" + coverQuality
                + ", coverType=" + coverType + ", description=" + description + ", photoPerSheet="
                + Arrays.toString(photoPerSheet) + ", sheetPrice=" + Arrays.toString(sheetPrice) + ", sheetRange="
                + Arrays.toString(sheetRange) + ", sheetType=" + Arrays.toString(sheetType) + "]";
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

}
