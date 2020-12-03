package com.albumbazaar.albumbazar.utilities;

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
