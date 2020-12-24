package com.albumbazaar.albumbazar.services.impl;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import com.albumbazaar.albumbazar.dao.CustomerRepository;
import com.albumbazaar.albumbazar.dao.OrderRepository;
import com.albumbazaar.albumbazar.dto.CustomerDTO;
import com.albumbazaar.albumbazar.dto.OrderDetailDTO;
import com.albumbazaar.albumbazar.form.LocationForm;
import com.albumbazaar.albumbazar.form.customer.BasicCustomerDetailForm;
import com.albumbazaar.albumbazar.model.Customer;
import com.albumbazaar.albumbazar.model.OrderDetail;
import com.albumbazaar.albumbazar.model.OrderDetailStatus;
import com.albumbazaar.albumbazar.services.CustomerService;
import com.albumbazaar.albumbazar.services.LocationService;
import com.albumbazaar.albumbazar.services.OrderService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Qualifier("customerService")
public class CustomerServiceImpl implements CustomerService {
    private final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    private final CustomerRepository customerRepository;
    private final LocationService locationService;
    private final OrderService orderService;

    @Autowired
    public CustomerServiceImpl(final CustomerRepository customerRepository, final LocationService locationService,
            @Qualifier("orderService") final OrderService orderService) {

        this.orderService = orderService;
        this.locationService = locationService;
        this.customerRepository = customerRepository;
    }

    @Transactional(readOnly = true)
    public Customer getCustomer(final Long id) {

        return customerRepository.findById(id).orElseThrow();

    }

    @Override
    @Transactional
    public Customer registerCustomer(final BasicCustomerDetailForm customerDetail, final LocationForm addressDetail) {
        Customer customer = new Customer(customerDetail);

        if (customerDetail.getReferralCode() != null && !customerDetail.getReferralCode().isBlank()) {
            final String id = customerDetail.getReferralCode().split("@")[0];
            final Customer referredCustomer = customerRepository.findById(Long.parseLong(id)).orElseThrow();

            if (referredCustomer.getReferralCode().equals(customerDetail.getReferralCode())) {
                // If the referral code matches then some operation
                customer.setWallet(100F);
            } else {
                throw new RuntimeException("Referral code did not match to the referred customer");
            }

        }

        if (addressDetail != null) {
            // Save the address using location service and than link it to the customer
            customer.setAddress(locationService.addNewAddress(addressDetail));
        }

        // Save customer to get the id of customer
        customer = customerRepository.save(customer);

        // Get the generated Referral Code
        final String referralCode = generateReferralCode(customer.getId());

        // Store the generated referral code
        customer.setReferralCode(referralCode);

        // If both the customer info and the referral code is saved successfully then it
        // return customer entity or else rollback changes
        return customer;

    }

    private String generateReferralCode(final Long id) {
        final StringBuffer referralCode = new StringBuffer();

        // All characters that will be used to generate the referral code
        final char[] allPossibleCharacters = new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'm', 'n',
                'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '2', '3', '4', '5', '6', '7', '8', '9' };

        final Random random = new Random();

        referralCode.append(id);
        referralCode.append("@");
        // Generating referral code of length 6
        for (int lengthOfReferralCode = 6; lengthOfReferralCode > 0; --lengthOfReferralCode) {
            // Getting random index from 0 -> length of all possible characters
            int index = random.nextInt(allPossibleCharacters.length);
            // Appending new characters to referral code
            referralCode.append(allPossibleCharacters[index]);
        }

        return referralCode.toString();
    }

    @Override
    @Transactional
    public Customer updateCustomerInfo(final CustomerDTO customerDTO) {

        final Customer customer = customerRepository.findById(customerDTO.getId()).orElseThrow();

        logger.info(customerDTO.getId() + ": About to update Customer Info");

        if (customerDTO.getName() != null && !customerDTO.getName().isBlank()) {
            customer.setName(customerDTO.getName().trim());
        }

        if (customerDTO.getEmail() != null && !customerDTO.getEmail().isBlank()) {
            customer.setEmail(customerDTO.getEmail());
        }

        return customer;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Customer> getDiscountedCustomer() {

        return customerRepository.findByDiscountGreaterThan(0f);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Customer> getBlockeList() {

        // Find all deactivated customers
        return customerRepository.findByActive(false);
    }

    @Override
    @Transactional(readOnly = true)
    public Float getWalletAmount(final Long id) {

        return customerRepository.getWalletAmount(id);
    }

    @Override
    @Transactional
    public Float updateWallet(final Long id, final float amount) {
        final Customer customer = customerRepository.findById(id).orElseThrow();

        if (customer.getWallet() + amount < 0) {
            throw new RuntimeException("wallet cannot get updated to negative value");
        }

        customer.setWallet(amount + customer.getWallet());

        return customer.getWallet();
    }

    @Override
    @Transactional(readOnly = true)
    public Float getDiscount(final Long id) {
        return customerRepository.getDiscount(id);
    }

    @Override
    @Transactional
    public Float setDiscount(final Long id, final float amount) {
        if (amount < 0) {
            throw new RuntimeException("Cannot be set to negative");
        }

        final Customer customer = customerRepository.findById(id).orElseThrow();

        customer.setDiscount(amount);

        return customer.getDiscount();
    }

    @Override
    public Customer deleteCustomer(Long id) {
        if (id == null) {
            throw new RuntimeException("Unable to find customer");
        }
        return updateCustomerStatus(id, false);
    }

    @Override
    public Customer restoreCustomer(Long id) {
        if (id == null) {
            throw new RuntimeException("Unable to find customer");
        }
        return updateCustomerStatus(id, true);
    }

    @Transactional
    private final Customer updateCustomerStatus(final Long customerId, final Boolean status) {
        try {
            final Customer customer = customerRepository.findById(customerId).orElseThrow();
            customer.setActive(status);
            return customerRepository.save(customer);

        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException("Unable to make change to the Customer");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderDetailDTO> getAllOrderDetails(Long customerId) {

        if (customerId == null) {
            throw new RuntimeException("Invalid Customer");
        }

        return orderService.getOrdersOfCustomer(customerId).stream().map(eachOrder -> {
            try {
                OrderDetailDTO eachOrderDTO = new OrderDetailDTO();

                eachOrderDTO.setId(eachOrder.getId());

                eachOrderDTO.setAssociationName(eachOrder.getAssociationName());
                eachOrderDTO.setCoverName(eachOrder.getCoverName());
                eachOrderDTO.setProductName(eachOrder.getProductName());
                eachOrderDTO.setProductSize(eachOrder.getProductSize());
                eachOrderDTO.setOrientation(eachOrder.getOrientation());

                eachOrderDTO.setPaymentStatus(eachOrder.getPaymentStatus());

                eachOrderDTO.setTotalAmount(eachOrder.getTotalAmount().toString());
                eachOrderDTO.setDiscount(eachOrder.getDiscount().toString());

                eachOrderDTO.setStatus(OrderDetailStatus.valueOf(eachOrder.getOrderStatus().toUpperCase()));

                eachOrderDTO.setOrderCreationTime(eachOrder.getOrderTime().toString());
                eachOrderDTO.setDeliveryDate(eachOrder.getDeliveryDate());

                return eachOrderDTO;
            } catch (Exception e) {
                return null;
            }
        }).filter(eachOrderDTO -> eachOrderDTO != null).collect(Collectors.toList());

        // return orderDetailDTOs;
    }

}
