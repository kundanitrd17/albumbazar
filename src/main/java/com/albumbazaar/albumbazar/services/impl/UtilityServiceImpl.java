package com.albumbazaar.albumbazar.services.impl;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import com.albumbazaar.albumbazar.dao.ResetPasswordCodeRepository;
import com.albumbazaar.albumbazar.dto.ResetPasswordDTO;
import com.albumbazaar.albumbazar.model.Customer;
import com.albumbazaar.albumbazar.model.Employee;
import com.albumbazaar.albumbazar.model.ResetPasswordCode;
import com.albumbazaar.albumbazar.services.CustomerService;
import com.albumbazaar.albumbazar.services.EmployeeService;
import com.albumbazaar.albumbazar.services.MailService;
import com.albumbazaar.albumbazar.services.UtilityService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Qualifier("utilityService")
public class UtilityServiceImpl implements UtilityService {

    private Logger logger = LoggerFactory.getLogger(UtilityServiceImpl.class);

    private final ResetPasswordCodeRepository resetPasswordCodeRepository;

    // Dependent services
    private CustomerService customerService;
    private EmployeeService employeeService;
    private MailService gmailService;

    @Autowired
    protected UtilityServiceImpl(@Qualifier("customerService") final CustomerService customerService,
            @Qualifier("gmailService") final MailService gmailService,
            @Qualifier("employeeService") final EmployeeService employeeService,
            final ResetPasswordCodeRepository resetPasswordCodeRepository) {

        this.customerService = customerService;
        this.resetPasswordCodeRepository = resetPasswordCodeRepository;
        this.gmailService = gmailService;
        this.employeeService = employeeService;

    }

    @Override
    @Transactional
    public void resetCustomerPassword(ResetPasswordDTO resetPasswordDTO) throws UsernameNotFoundException {

        boolean isVerified = verifyResetOTPCode(resetPasswordDTO);

        if (!isVerified) {
            throw new RuntimeException("Password did not match");
        }

        final Customer customer = customerService.loadByEmail(resetPasswordDTO.getEmail());

        if (!customer.getActive()) {
            throw new UsernameNotFoundException("User Not Found");
        }

        customer.setPassword(resetPasswordDTO.getPassword());

    }

    @Override
    @Transactional
    public void resetEmployeePassword(ResetPasswordDTO resetPasswordDTO) throws UsernameNotFoundException {

        final boolean isVerified = verifyResetOTPCode(resetPasswordDTO);

        if (!isVerified)
            throw new RuntimeException("Not Valid");

        final Employee employee = employeeService.loadByEmail(resetPasswordDTO.getEmail());

        if (!employee.getActive()) {
            throw new UsernameNotFoundException("User Not found");
        }

        employee.setPassword(resetPasswordDTO.getPassword());

    }

    @Transactional(readOnly = true)
    private boolean verifyResetOTPCode(final ResetPasswordDTO resetPasswordDTO) {
        final ResetPasswordCode resetPasswordCode = resetPasswordCodeRepository.findById(resetPasswordDTO.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        final boolean isVerified = resetPasswordCode.getOTP().equals(resetPasswordDTO.getOTP())
                && resetPasswordDTO.getPassword().equals(resetPasswordDTO.getRePassword());

        return isVerified;

    }

    @Override
    public void sendOTPToCustomer(final String userIdentificationkey) throws UsernameNotFoundException {

        final Customer customer = customerService.loadByEmail(userIdentificationkey);
        if (customer == null || !customer.getActive()) {
            throw new UsernameNotFoundException("User not found");
        }

        initiateSendingOTP(userIdentificationkey, customer.getEmail());

    }

    @Override
    public void sendOTPToEmployee(String userIdentificationkey) {

        final Employee employee = employeeService.loadByEmail(userIdentificationkey);
        if (employee == null || !employee.getActive())
            throw new UsernameNotFoundException("User Not Found");

        initiateSendingOTP(userIdentificationkey, employee.getEmail());

    }

    @Transactional
    // Initiate to send reset password OTP to the email
    private void initiateSendingOTP(final String userIdentificationkey, final String userEmail) {
        ResetPasswordCode resetPasswordCode = new ResetPasswordCode();

        Optional<ResetPasswordCode> resetCodeEntity = resetPasswordCodeRepository.findById(userIdentificationkey);
        if (resetCodeEntity.isPresent()) {
            resetPasswordCode = resetCodeEntity.get();

            if (Instant.now().isAfter(resetPasswordCode.getCreatedAt().plus(30, ChronoUnit.MINUTES))) {
                final String OTP = generateOTP();
                resetPasswordCode.setOTP(OTP);
            }

        } else {
            resetPasswordCode.setUserIdentifierKey(userIdentificationkey);
            resetPasswordCode.setCreatedAt(Instant.now());
            final String OTP = generateOTP();
            resetPasswordCode.setOTP(OTP);
        }

        sendOTPByEmail(userEmail, resetPasswordCode.getOTP());

        resetPasswordCodeRepository.save(resetPasswordCode);

    }

    // Generate Random OTP
    private String generateOTP() {
        // 6-digit

        return "9090";
    }

    // Send OTP via email
    private void sendOTPByEmail(final String email, final String OTP) {

        gmailService.sendEmail("princewillz2013@gmail.com", email, "Reset Password OTP", OTP);

    }

}
