package com.albumbazaar.albumbazar.services.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import com.albumbazaar.albumbazar.Mapper.AddressMapper;
import com.albumbazaar.albumbazar.Mapper.CustomerMapper;
import com.albumbazaar.albumbazar.Mapper.OrderDetailMapper;
import com.albumbazaar.albumbazar.dao.AddressRepository;
import com.albumbazaar.albumbazar.dao.CustomerRepository;
import com.albumbazaar.albumbazar.dto.AddressDTO;
import com.albumbazaar.albumbazar.dto.CustomerDTO;
import com.albumbazaar.albumbazar.dto.OrderDetailDTO;
import com.albumbazaar.albumbazar.model.AddressEntity;
import com.albumbazaar.albumbazar.model.Customer;
import com.albumbazaar.albumbazar.model.OrderDetailStatus;
import com.albumbazaar.albumbazar.principals.CustomerPrincipal;
import com.albumbazaar.albumbazar.services.CustomerService;
import com.albumbazaar.albumbazar.services.OrderService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Qualifier("customerService")
public class CustomerServiceImpl implements CustomerService, UserDetailsService {
    private final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    private final CustomerRepository customerRepository;
    private final OrderService orderService;
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;
    private final CustomerMapper customerMapper;
    private final OrderDetailMapper orderDetailMapper;

    @Autowired
    public CustomerServiceImpl(final CustomerRepository customerRepository,
            @Qualifier("orderService") final OrderService orderService, final AddressRepository addressRepository,
            final AddressMapper addressMapper, final CustomerMapper customerMapper,
            final OrderDetailMapper orderDetailMapper) {
        this.addressRepository = addressRepository;
        this.orderService = orderService;
        this.customerRepository = customerRepository;
        this.addressMapper = addressMapper;
        this.customerMapper = customerMapper;
        this.orderDetailMapper = orderDetailMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public Customer getCustomer(final Long id) {

        return customerRepository.findById(id).orElseThrow();

    }

    @Override
    @Transactional
    public CustomerDTO registerCustomer(final CustomerDTO customerDTO) {

        if (!this.isPasswordValid(customerDTO)) {
            throw new RuntimeException("Invalid Password");
        }

        final Customer newCustomerEntity = customerMapper.customerDTOToCustomerEntity(customerDTO);

        if (newCustomerEntity.getId() != null) {
            throw new RuntimeException("Action not allowed");
        }

        try {
            final String referredByCode = customerDTO.getReferralCode();
            // If the referral code is available then do the rewarding and all that stuff
            if (referredByCode != null && !referredByCode.isBlank()) {
                final Long referredById = Long.parseLong(referredByCode.split("@")[0]);
                final Customer referredByCustomer = this.getCustomer(referredById);
                referredByCustomer.setWallet(referredByCustomer.getWallet() + 100f);
                newCustomerEntity.setWallet(100f);
            }

        } catch (Exception e) {
            throw new RuntimeException("Invalid Referral code");
        }

        final Customer savedCustomerEntity = customerRepository.save(newCustomerEntity);

        savedCustomerEntity.setReferralCode(this.generateReferralCode(savedCustomerEntity.getId()));

        return customerMapper.customerEntityToCustomerDTO(savedCustomerEntity);

    }

    private boolean isPasswordValid(final CustomerDTO customerDTO) {

        if (customerDTO.getPassword() == null || customerDTO.getRePassword() == null) {
            return false;
        }
        if (!customerDTO.getPassword().equals(customerDTO.getRePassword())) {
            return false;
        }

        return true;
        // return customerDTO.getPassword().length() > 7;

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

                OrderDetailDTO eachOrderDTO = orderDetailMapper.orderDetailToOrderDetailDTO(eachOrder);
                System.out.println("DTO: " + eachOrderDTO);

                return eachOrderDTO;
            } catch (Exception e) {
                logger.info(e.getMessage());
                return null;
            }
        }).filter(eachOrderDTO -> eachOrderDTO != null).collect(Collectors.toList());

        // return orderDetailDTOs;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Customer customer = customerRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid Credentials"));

        return new CustomerPrincipal(customer);
    }

    @Override
    public void deleteAddress(long customerId, long addressId) {

        customerRepository.deleteByAddressId(customerId, addressId);
        addressRepository.deleteById(addressId);

    }

    @Override
    public void updateOrAddAddress(AddressDTO addressDTO, Long customerId) {

        if (addressDTO.getId() == null) {
            this.addNewAddressOfCustomer(addressDTO, customerId);
        } else {
            this.updateAddressInfo(addressDTO, customerId);
        }

    }

    @Transactional
    private void updateAddressInfo(AddressDTO addressDTO, Long customerId) {
        addressRepository.save(addressMapper.addressDTOToAddressEntity(addressDTO));
    }

    @Transactional
    void addNewAddressOfCustomer(final AddressDTO addressDTO, final Long customerId) {
        final Customer customer = this.getCustomer(customerId);
        Set<AddressEntity> addresses = customer.getAddress();

        if (addresses == null) {
            addresses = new HashSet<>(5);
        }
        final AddressEntity address = addressRepository.save(addressMapper.addressDTOToAddressEntity(addressDTO));
        addresses.add(address);

        customer.setAddress(addresses);

        customerRepository.save(customer);

    }

}
