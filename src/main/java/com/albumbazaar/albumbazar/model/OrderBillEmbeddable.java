package com.albumbazaar.albumbazar.model;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Embeddable
@Data
public class OrderBillEmbeddable {

    @NotNull
    private Float totalAmount;
    private Float discount;
    private Float wallet;

    @NotNull
    private Float amountToPay;

    public OrderBillEmbeddable() {
        this.totalAmount = 0f;
        this.discount = 0f;
        this.wallet = 0f;
        this.amountToPay = 0f;
    }

}
