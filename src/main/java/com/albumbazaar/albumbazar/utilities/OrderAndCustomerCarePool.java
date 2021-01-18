package com.albumbazaar.albumbazar.utilities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderAndCustomerCarePool {
    private Long orderId;
    private Long customerCareId;

}
