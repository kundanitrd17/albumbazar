package com.albumbazaar.albumbazar.dto;

import lombok.Data;

@Data
public class OrderBillDTO {
    
    private Long order_id;

    private Float total_amount;
    private Float discount;
    private Float wallet;

    private Float amount_to_pay;

    public OrderBillDTO() {
        this.total_amount = 0f;
        this.discount = 0f;
        this.wallet = 0f;
        this.amount_to_pay = 0f;
    }

}
