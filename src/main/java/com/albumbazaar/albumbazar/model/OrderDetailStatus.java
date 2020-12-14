package com.albumbazaar.albumbazar.model;

public enum OrderDetailStatus {

    PENDING(Code.PENDING), COMPLETED(Code.COMPLETED), PROCESSING(Code.PROCESSING), DELIVERED(Code.DELIVERED),
    DELIVER(Code.DELIVER), DELIVERY_UNDER_PROCESS(Code.DELIVERY_UNDER_PROCESS);

    private final String status;

    OrderDetailStatus(final String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return status;
    }

    public class Code {
        public static final String PENDING = "PENDING";
        public static final String PROCESSING = "PROCESSING";
        public static final String DELIVER = "DELIVER";
        public static final String DELIVERY_UNDER_PROCESS = "DELIVERY_UNDER_PROCESS";
        public static final String DELIVERED = "DELIVERED";
        public static final String COMPLETED = "COMPLETED";
    }

}
