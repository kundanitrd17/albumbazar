package com.albumbazaar.albumbazar.utilities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderAndCustomerCarePool {
    private Long orderId;
    private Long customerCareId;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getCustomerCareId() {
        return customerCareId;
    }

    public void setCustomerCareId(Long customerCareId) {
        this.customerCareId = customerCareId;
    }

    @Override
    public String toString() {
        return "OrderAndCustomerCarePool [customerCareId=" + customerCareId + ", orderId=" + orderId + "]";
    }

}
