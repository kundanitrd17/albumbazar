package com.albumbazaar.albumbazar.dto;

public class DeliveryOrderDTO {

    private Long id;
    private String fromAddress;
    private String toAddress;
    private String UUID_CODE;

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public String getUUID_CODE() {
        return UUID_CODE;
    }

    public void setUUID_CODE(String uUID_CODE) {
        UUID_CODE = uUID_CODE;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
