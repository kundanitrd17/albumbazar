package com.albumbazaar.albumbazar.services;

import java.util.List;

import com.albumbazaar.albumbazar.model.Customer;
import com.albumbazaar.albumbazar.model.OrderDetail;
import com.albumbazaar.albumbazar.model.RazorPayEntity;
import com.albumbazaar.albumbazar.utilities.PaymentDTORazorpay;
import com.razorpay.Payment;
import com.razorpay.RazorpayException;

public interface RazorPayPaymentService {

    OrderDetail createRazorPayOrder(final Long orderId) throws RazorpayException;

    RazorPayEntity getRazorPayEntityForNewOrder(OrderDetail orderDetail, Customer customer);

    void saveCredentialOnPaymentSuccess(String razorpayOrderId, String razorpayPaymentId, String razorpaySignature);

    List<PaymentDTORazorpay> getAllPayments() throws RazorpayException;

    List<PaymentDTORazorpay> getAllPaidPayments() throws RazorpayException;

}
