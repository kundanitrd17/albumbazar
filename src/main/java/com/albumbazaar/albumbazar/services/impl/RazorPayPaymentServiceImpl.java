package com.albumbazaar.albumbazar.services.impl;

import javax.annotation.PostConstruct;

import com.albumbazaar.albumbazar.model.Customer;
import com.albumbazaar.albumbazar.model.OrderDetail;
import com.albumbazaar.albumbazar.model.RazorPayEntity;
import com.albumbazaar.albumbazar.services.OrderService;
import com.albumbazaar.albumbazar.services.RazorPayPaymentService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@Qualifier("razorPayPaymentService")
public class RazorPayPaymentServiceImpl implements RazorPayPaymentService {
    private final Logger logger = LoggerFactory.getLogger(RazorPayPaymentServiceImpl.class);

    private RazorpayClient razorpayClient;

    private final String RAZORPAY_API_KEY = "rzp_test_oO5Nlz03qtxInq";
    private final String RAZORPAY_SECRET_KEY = "aDCCVMEUiVoDkZXhdpE8cA75";

    // Other services required by this service

    private final OrderService orderService;

    @Autowired(required = true)
    protected RazorPayPaymentServiceImpl(@Qualifier("orderService") final OrderService orderService) {
        this.orderService = orderService;
    }

    @PostConstruct
    void postConstruct() {
        try {
            this.razorpayClient = new RazorpayClient(this.RAZORPAY_API_KEY, this.RAZORPAY_SECRET_KEY);
        } catch (RazorpayException e) {
            logger.error(e.getMessage());

        }
    }

    // Form to make payment using razorpay. Get total amount from the database and
    // then make a request to create a order from the from the razorpay server
    @Override
    @Transactional
    public OrderDetail createRazorPayOrder(final Long orderId) throws RazorpayException {

        final OrderDetail order = orderService.getOrder(orderId);

        // If payment already done
        if (order.getPaymentStatus()) {
            return order;
        }

        JSONObject options = new JSONObject();
        options.put("amount", order.getTotalAmount() * 100);
        options.put("currency", "INR");
        options.put("receipt", "txn_123456");
        options.put("payment_capture", 1);

        final Order rzOrder = razorpayClient.Orders.create(options);

        order.setRazorpayOrderId(rzOrder.get("id"));

        return order;
    }

    @Override
    public RazorPayEntity getRazorPayEntityForNewOrder(final OrderDetail orderDetail, final Customer customer) {

        RazorPayEntity razorPay = new RazorPayEntity();

        razorPay.setApplicationFee(String.valueOf(orderDetail.getTotalAmount() * 100));
        razorPay.setCustomerName(customer.getName());
        razorPay.setCustomerEmail(customer.getEmail());
        razorPay.setMerchantName("Merchant Name");
        razorPay.setPurchaseDescription("TEST PURCHASES");
        razorPay.setRazorpayOrderId(orderDetail.getRazorpayOrderId());
        razorPay.setSecretKey(RAZORPAY_API_KEY);
        // razorPay.setImageURL("/logo")
        razorPay.setCustomerContact(customer.getContactNo());

        razorPay.setTheme("#F37254");
        razorPay.setNotes("notes" + orderDetail.getRazorpayOrderId());

        return razorPay;
    }

    @Transactional
    @Override
    public OrderDetail saveCredentialOnPaymentSuccess(String razorpayOrderId, String razorpayPaymentId,
            String razorpaySignature) {

        final OrderDetail orderDetail = orderService.getOrderWithRazorpayOrderId(razorpayOrderId);

        orderDetail.setRazorpayOrderId(razorpayOrderId);
        orderDetail.setRazorpayPaymentId(razorpayPaymentId);
        orderDetail.setRazorPaySignature(razorpaySignature);
        orderDetail.setPaymentStatus(true);

        return orderDetail;
    }

}
