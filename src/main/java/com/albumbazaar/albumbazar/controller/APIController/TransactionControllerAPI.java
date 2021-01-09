package com.albumbazaar.albumbazar.controller.APIController;

import java.util.HashMap;

import com.albumbazaar.albumbazar.dto.ErrorDTO;
import com.albumbazaar.albumbazar.model.Customer;
import com.albumbazaar.albumbazar.model.OrderDetail;
import com.albumbazaar.albumbazar.model.RazorPayEntity;
import com.albumbazaar.albumbazar.services.OrderService;
import com.albumbazaar.albumbazar.services.RazorPayPaymentService;
import com.albumbazaar.albumbazar.services.TransactionService;
import com.razorpay.RazorpayException;
import com.razorpay.Utils;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api")
public class TransactionControllerAPI {

    private final Logger logger = LoggerFactory.getLogger(TransactionControllerAPI.class);

    private final OrderService orderService;
    private final RazorPayPaymentService razorPayPaymentService;
    private final TransactionService transactionService;

    @Autowired(required = true)
    protected TransactionControllerAPI(@Qualifier("orderService") final OrderService orderService,
            @Qualifier("razorPayPaymentService") final RazorPayPaymentService razorPayPaymentService, 
            @Qualifier("transactionService")final TransactionService transactionService) {
        this.orderService = orderService;
        this.razorPayPaymentService = razorPayPaymentService;
        this.transactionService = transactionService;
    }

    @PostMapping(value = "secured/bill/paid")
    public ResponseEntity<?> paidMoney(@RequestBody final HashMap<String, String> model) {

        System.out.println(model);

        // Extracting information from the razorpay server
        final String razorpaySignature = model.get("razorpay_signature");
        final String razorpayPaymentId = model.get("razorpay_payment_id");
        final String razorpayOrderId = model.get("razorpay_order_id");

        JSONObject options = new JSONObject();

        System.out.println(razorpayPaymentId);
        System.out.println(razorpaySignature);
        System.out.println(razorpayOrderId);

        if (razorpayPaymentId != null && razorpaySignature != null && razorpayOrderId != null
                && !razorpayPaymentId.isBlank() && !razorpaySignature.isBlank() && !razorpayOrderId.isBlank()) {

            try {
                options.put("razorpay_payment_id", razorpayPaymentId);
                options.put("razorpay_order_id", razorpayOrderId);
                options.put("razorpay_signature", razorpaySignature);

                boolean isEqual = Utils.verifyPaymentSignature(options, "aDCCVMEUiVoDkZXhdpE8cA75");
                if (isEqual) {
                    razorPayPaymentService.saveCredentialOnPaymentSuccess(razorpayOrderId, razorpayPaymentId,
                            razorpaySignature);
                    return ResponseEntity.ok().build();
                }

            } catch (RazorpayException e) {
                logger.error(e.getMessage());
            }

        }

        logger.error("Unable to confirm payment. orderId: " + razorpayOrderId + ", paymentId: " + razorpayPaymentId
                + ", signature: " + razorpayPaymentId);

        return ResponseEntity.badRequest().build();
    }

    @PostMapping(value = "secured/bill/generate")
    public ResponseEntity<?> getTotalAmount(@RequestBody final Long orderId) {

        try {
            System.out.println(orderId);

            // Getting razorPay order
            final OrderDetail orderDetail = razorPayPaymentService.createRazorPayOrder(orderId);
            System.out.println("Order : " + orderDetail.toString());

            // Get customer
            // After adding security gona get this from securitycontext as a principal
            final Customer customer = new Customer();
            customer.setName("harsh");
            customer.setEmail("email@gmail.com");
            customer.setContactNo("9832145254");
            // Get Razorpay entity to be sent to the client
            final RazorPayEntity razorPayEntity = razorPayPaymentService.getRazorPayEntityForNewOrder(orderDetail,
                    customer);

            return ResponseEntity.ok(razorPayEntity);

        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        final ErrorDTO error = new ErrorDTO();
        error.setMessage("unable to process bill try again");
        return ResponseEntity.badRequest().body(error);
    }


}
