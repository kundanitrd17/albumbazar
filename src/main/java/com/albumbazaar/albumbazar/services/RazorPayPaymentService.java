package com.albumbazaar.albumbazar.services;

import com.albumbazaar.albumbazar.model.Customer;
import com.albumbazaar.albumbazar.model.OrderDetail;
import com.albumbazaar.albumbazar.model.RazorPayEntity;
import com.razorpay.Order;
import com.razorpay.RazorpayException;

public interface RazorPayPaymentService {

    OrderDetail createRazorPayOrder(final Long orderId) throws RazorpayException;

    RazorPayEntity getRazorPayEntityForNewOrder(OrderDetail orderDetail, Customer customer);

    OrderDetail saveCredentialOnPaymentSuccess(String razorpayOrderId, String razorpayPaymentId,
            String razorpaySignature);

}
