package com.albumbazaar.albumbazar.dto;

import lombok.Data;

@Data
public class OrderBillDTO {

    private Long order_id;

    private Double total_amount;
    private Double discount;
    private Double wallet;

    private Double amount_to_pay;

    public OrderBillDTO() {
        this.total_amount = 0.0;
        this.discount = 0.0;
        this.wallet = 0.0;
        this.amount_to_pay = 0.0;
    }

}
