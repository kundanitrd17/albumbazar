package com.albumbazaar.albumbazar.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Builder
@Getter
public class DeliveryOrderDTO {

    private Long order_id;
    private String UUID_CODE;
    private String name;
    private String contact;
    private Long pickup_address;
    private Long delivery_address;

}
