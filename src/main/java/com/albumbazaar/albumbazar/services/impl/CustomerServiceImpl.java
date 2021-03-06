package com.albumbazaar.albumbazar.services.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import com.albumbazaar.albumbazar.Mapper.AddressMapper;
import com.albumbazaar.albumbazar.Mapper.CustomerMapper;
import com.albumbazaar.albumbazar.dao.AddressRepository;
import com.albumbazaar.albumbazar.dao.CustomerRepository;
import com.albumbazaar.albumbazar.dao.WebsiteGeneralInfoRepository;
import com.albumbazaar.albumbazar.dto.AddressDTO;
import com.albumbazaar.albumbazar.dto.CustomerDTO;
import com.albumbazaar.albumbazar.model.AddressEntity;
import com.albumbazaar.albumbazar.model.Customer;
import com.albumbazaar.albumbazar.model.OrderDetail;
import com.albumbazaar.albumbazar.model.WebsiteGeneralInfoEntity;
import com.albumbazaar.albumbazar.principals.CustomerPrincipal;
import com.albumbazaar.albumbazar.services.CustomerService;
import com.albumbazaar.albumbazar.services.OrderService;
import com.albumbazaar.albumbazar.services.storage.ImageStorageService;
import com.albumbazaar.albumbazar.services.storage.StorageService;

import org.apache.catalina.core.ApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Qualifier("customerService")
public class CustomerServiceImpl implements CustomerService, UserDetailsService {
    private final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    private final CustomerRepository customerRepository;
    private final OrderService orderService;
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;
    private final CustomerMapper customerMapper;
    private final WebsiteGeneralInfoRepository websiteGeneralInfoRepository;

    private final StorageService imageStorageService;

    @Autowired
    public CustomerServiceImpl(final CustomerRepository customerRepository,
            @Qualifier("imageStorageService") final StorageService imageStorageService,
            @Qualifier("orderService") final OrderService orderService, final AddressRepository addressRepository,
            final WebsiteGeneralInfoRepository websiteGeneralInfoRepository, final AddressMapper addressMapper,
            final CustomerMapper customerMapper) {
        this.addressRepository = addressRepository;
        this.orderService = orderService;
        this.customerRepository = customerRepository;
        this.addressMapper = addressMapper;
        this.customerMapper = customerMapper;
        this.websiteGeneralInfoRepository = websiteGeneralInfoRepository;
        this.imageStorageService = imageStorageService;
    }

    @Override
    @Transactional(readOnly = true)
    public Customer getCustomer(final Long id) {

        return customerRepository.findById(id).orElseThrow();

    }

    @Override
    @Transactional
    public Customer registerCustomer(final CustomerDTO customerDTO) {

        if (!this.isPasswordValid(customerDTO)) {
            throw new RuntimeException("Invalid Password");
        }

        final Customer newCustomerEntity = customerMapper.customerDTOToCustomerEntity(customerDTO);

        if (newCustomerEntity.getId() != null) {
            throw new RuntimeException("Action not allowed");
        }

        try {

            newCustomerEntity.setName(customerDTO.getFirstName().trim() + " " + customerDTO.getLastName().trim());

            final String referredByCode = customerDTO.getReferralCode();
            // If the referral code is available then do the rewarding and all that stuff
            if (referredByCode != null && !referredByCode.isBlank()) {
                final Long referredById = Long.parseLong(referredByCode.split("@")[0]);
                final Customer referredByCustomer = this.getCustomer(referredById);

                final WebsiteGeneralInfoEntity websiteGeneralInfoEntity = websiteGeneralInfoRepository.findById(1l)
                        .orElseThrow();

                referredByCustomer
                        .setWallet(referredByCustomer.getWallet() + websiteGeneralInfoEntity.getREFERALL_AMOUNT());
                newCustomerEntity.setWallet(websiteGeneralInfoEntity.getREFERALL_AMOUNT());
            }

        } catch (Exception e) {
            throw new RuntimeException("Invalid Referral code");
        }

        final Customer savedCustomerEntity = customerRepository.save(newCustomerEntity);

        savedCustomerEntity.setReferralCode(this.generateReferralCode(savedCustomerEntity.getId()));

        return savedCustomerEntity;

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

        return customerRepository.findByDiscountGreaterThan(0.0);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Customer> getBlockeList() {

        // Find all deactivated customers
        return customerRepository.findByActive(false);
    }

    @Override
    @Transactional(readOnly = true)
    public Double getWalletAmount(final Long id) {

        return customerRepository.getWalletAmount(id);
    }

    @Override
    @Transactional
    public Double updateWallet(final Long id, final double amount) {
        final Customer customer = customerRepository.findById(id).orElseThrow();

        if (customer.getWallet() + amount < 0) {
            throw new RuntimeException("wallet cannot get updated to negative value");
        }

        customer.setWallet(amount + customer.getWallet());

        return customer.getWallet();
    }

    @Override
    @Transactional(readOnly = true)
    public Double getDiscount(final Long id) {
        return customerRepository.getDiscount(id);
    }

    @Override
    @Transactional
    public Double setDiscount(final Long id, final double amount) {
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
    public Page<OrderDetail> getAllOrderDetails(final Long customerId, int page, int size) {
        if (customerId == null) {
            throw new RuntimeException("Invalid Customer");
        }

        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());

        return orderService.getOrdersOfCustomer(this.getCustomer(customerId), pageable);

    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderDetail> getAllOrderDetails(final Long customerId) {

        if (customerId == null) {
            throw new RuntimeException("Invalid Customer");
        }

        return orderService.getOrdersOfCustomer(customerId);

    }

    @Override
    public Customer loadByEmail(final String email) throws UsernameNotFoundException {

        return customerRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid Credentials"));
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
        logger.info("Updating address");
        addressRepository.save(addressMapper.addressDTOToAddressEntity(addressDTO));
    }

    @Transactional
    void addNewAddressOfCustomer(final AddressDTO addressDTO, final Long customerId) {
        final Customer customer = this.getCustomer(customerId);
        Set<AddressEntity> addresses = customer.getAddress();

        System.out.println(addressDTO);
        System.out.println(addressMapper.addressDTOToAddressEntity(addressDTO));

        if (addresses == null) {
            addresses = new HashSet<>(5);
        }
        final AddressEntity address = addressRepository.save(addressMapper.addressDTOToAddressEntity(addressDTO));
        addresses.add(address);

        customer.setAddress(addresses);

        customerRepository.save(customer);

    }

    @Override
    @Transactional
    public void setRewardForCustomer(final String customerIdentifier, Double discount) {
        final Customer customer = this.loadByEmail(customerIdentifier);
        customer.setDiscount(discount);

    }

    @Override
    public Long getCountOfAllCustomers() {
        return customerRepository.count();
    }

    @Override
    @Transactional
    public Customer updateProfileImage(String username, MultipartFile image) {

        final Customer customer = this.loadByEmail(username);

        final String imageName = imageStorageService.store(image,
                String.format("%sProfileImage%s", username, image.getOriginalFilename()));

        customer.setProfilePhoto(imageName);

        return customer;

    }

}
