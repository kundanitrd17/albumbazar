package com.albumbazaar.albumbazar.model;

public enum OrderDetailStatus {

    PENDING(Code.PENDING), COMPLETED(Code.COMPLETED), PROCESSING(Code.PROCESSING),
    UNDER_PROCESS_BY_ASSOCIATION(Code.UNDER_PROCESS_BY_ASSOCIATION),
    READY_TO_DELIVER(Code.READY_TO_DELIVER), SENT_TO_DELIVERY_PARTNER(Code.SENT_TO_DELIVERY_PARTNER),
    DELIVERED(Code.DELIVERED), DELIVER(Code.DELIVER), DELIVERY_UNDER_PROCESS(Code.DELIVERY_UNDER_PROCESS);

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
        public static final String UNDER_PROCESS_BY_ASSOCIATION = "UNDER_PROCESS_BY_ASSOCIATION";
        public static final String READY_TO_DELIVER = "READY_TO_DELIVER";
        public static final String DELIVER = "DELIVER";
        public static final String SENT_TO_DELIVERY_PARTNER = "SENT_TO_DELIVERY_PARTNER";
        public static final String DELIVERY_UNDER_PROCESS = "DELIVERY_UNDER_PROCESS";
        public static final String DELIVERED = "DELIVERED";
        public static final String COMPLETED = "COMPLETED";
    }

}
