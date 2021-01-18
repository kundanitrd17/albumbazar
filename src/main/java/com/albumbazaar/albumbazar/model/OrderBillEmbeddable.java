package com.albumbazaar.albumbazar.model;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Embeddable
@Data
public class OrderBillEmbeddable {

    @NotNull
    private Double totalAmount;
    private Double discount;
    private Double wallet;

    @NotNull
    private Double amountToPay;

    public OrderBillEmbeddable() {
        this.totalAmount = 0.0;
        this.discount = 0.0;
        this.wallet = 0.0;
        this.amountToPay = 0.0;
    }

}
