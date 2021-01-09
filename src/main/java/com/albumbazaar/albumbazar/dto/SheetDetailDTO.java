package com.albumbazaar.albumbazar.dto;

import lombok.Data;

@Data
public class SheetDetailDTO {

    private Long paperId;
    private Integer sheets;
    private String paperName;
    private String paperSize;
    private Float paperPrice;

}
