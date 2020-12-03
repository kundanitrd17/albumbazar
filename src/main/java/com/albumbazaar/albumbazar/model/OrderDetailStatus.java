package com.albumbazaar.albumbazar.model;

public enum OrderDetailStatus {

    PENDING(Code.PENDING), COMPLETED(Code.COMPLETED), PROCESSING(Code.PROCESSING), DELIVERED(Code.DELIVERED);

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
        public static final String COMPLETED = "COMPLETED";
        public static final String PROCESSING = "PROCESSING";
        public static final String DELIVERED = "DELIVERED";
    }

}
