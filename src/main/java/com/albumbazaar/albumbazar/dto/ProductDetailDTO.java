package com.albumbazaar.albumbazar.dto;

import java.util.List;

import com.albumbazaar.albumbazar.model.Association;

import lombok.Data;

@Data
public class ProductDetailDTO {

    private List<SheetDetailDTO> sheet_detail_list;

    private CoverDTO cover;

    private String association_name;

    private String product_name;

    private String product_size;
    
}
