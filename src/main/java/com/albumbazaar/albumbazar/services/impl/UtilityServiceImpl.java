package com.albumbazaar.albumbazar.services.impl;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.persistence.EntityNotFoundException;

import com.albumbazaar.albumbazar.dao.CarasoulRepository;
import com.albumbazaar.albumbazar.dao.FrequentQuestionRespository;
import com.albumbazaar.albumbazar.dao.ResetPasswordCodeRepository;
import com.albumbazaar.albumbazar.dao.SampleAlbumRespository;
import com.albumbazaar.albumbazar.dto.ResetPasswordDTO;
import com.albumbazaar.albumbazar.dto.SampleAlbumDTO;
import com.albumbazaar.albumbazar.model.CarasoulEntity;
import com.albumbazaar.albumbazar.model.Customer;
import com.albumbazaar.albumbazar.model.Employee;
import com.albumbazaar.albumbazar.model.FrequentQuestionEntity;
import com.albumbazaar.albumbazar.model.ResetPasswordCode;
import com.albumbazaar.albumbazar.model.SampleAlbumEntity;
import com.albumbazaar.albumbazar.services.CustomerService;
import com.albumbazaar.albumbazar.services.EmployeeService;
import com.albumbazaar.albumbazar.services.MailService;
import com.albumbazaar.albumbazar.services.UtilityService;
import com.albumbazaar.albumbazar.services.storage.ImageStorageService;
import com.albumbazaar.albumbazar.services.storage.StorageService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Qualifier("utilityService")
public class UtilityServiceImpl implements UtilityService {

    private Logger logger = LoggerFactory.getLogger(UtilityServiceImpl.class);

    private final ResetPasswordCodeRepository resetPasswordCodeRepository;
    private final CarasoulRepository carasoulRepository;

    // Dependent services
    private final CustomerService customerService;
    private final EmployeeService employeeService;
    private final MailService gmailService;
    private final StorageService imageStorageService;
    private final SampleAlbumRespository sampleAlbumRespository;
    private final FrequentQuestionRespository frequentQuestionRespository;

    @Autowired
    protected UtilityServiceImpl(@Qualifier("customerService") final CustomerService customerService,
            @Qualifier("gmailService") final MailService gmailService,
            @Qualifier("imageStorageService") final StorageService imageStorageService,
            @Qualifier("employeeService") final EmployeeService employeeService,
            final CarasoulRepository carasoulRepository, final SampleAlbumRespository sampleAlbumRespository,
            final FrequentQuestionRespository frequentQuestionRespository,
            final ResetPasswordCodeRepository resetPasswordCodeRepository) {

        this.customerService = customerService;
        this.resetPasswordCodeRepository = resetPasswordCodeRepository;
        this.gmailService = gmailService;
        this.employeeService = employeeService;
        this.imageStorageService = imageStorageService;
        this.carasoulRepository = carasoulRepository;

        this.frequentQuestionRespository = frequentQuestionRespository;
        this.sampleAlbumRespository = sampleAlbumRespository;

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

    @Override
    @Transactional
    public void createCarasoul(final MultipartFile carasoul) {

        final CarasoulEntity carasoulEntity = new CarasoulEntity();

        final Random random = new Random();

        String imageName = imageStorageService.store(carasoul,
                "carasoul" + random.nextInt(10000) + carasoul.getOriginalFilename());

        carasoulEntity.setImage(imageName);

        carasoulRepository.save(carasoulEntity);

    }

    @Override
    @Transactional
    public void updateCarasoul(final Long carasoulId, final MultipartFile carasoul) {
        final CarasoulEntity carasoulEntity = carasoulRepository.findById(carasoulId).orElseThrow();

        final String imageFileName = imageStorageService.store(carasoul,
                "carasoul" + new Random().nextInt(10000) + carasoul.getOriginalFilename());

        carasoulEntity.setImage(imageFileName);

    }

    @Override
    public void deleteCarasoul(final Long id) {

        carasoulRepository.deleteById(id);

    }

    @Override
    public List<CarasoulEntity> getAllCarasoul() {

        return carasoulRepository.findAll();

    }

    @Override
    @Transactional
    public void uploadSampleAlbum(final SampleAlbumDTO sampleAlbumDTO) {

        SampleAlbumEntity sampleAlbumEntity;

        if (sampleAlbumDTO.getId() != null) {
            sampleAlbumEntity = sampleAlbumRespository.findById(sampleAlbumDTO.getId()).orElseThrow();
            sampleAlbumEntity.setTitle(sampleAlbumDTO.getTitle());
            sampleAlbumEntity.setDescription(sampleAlbumDTO.getDescription());

        } else {
            sampleAlbumEntity = new SampleAlbumEntity();
            sampleAlbumEntity.setTitle(sampleAlbumDTO.getTitle());
            sampleAlbumEntity.setDescription(sampleAlbumDTO.getDescription());
            final String imageFileName = imageStorageService.store(sampleAlbumDTO.getImage(),
                    "sample_album" + new Random().nextInt(10000) + sampleAlbumDTO.getImage().getOriginalFilename());

            sampleAlbumEntity.setImage(imageFileName);

            sampleAlbumRespository.save(sampleAlbumEntity);
        }

    }

    @Override
    public void deleteSampleAlbum(final Long id) {
        sampleAlbumRespository.deleteById(id);
    }

    @Override
    public void createQuestion(FrequentQuestionEntity frequentQuestion) {
        frequentQuestionRespository.save(frequentQuestion);
    }

    @Override
    public void deleteFrequentQuestion(final Long id) {
        frequentQuestionRespository.deleteById(id);
    }

    @Override
    public List<SampleAlbumEntity> getAllSampleAlbum() {
        return sampleAlbumRespository.findAll();
    }

    @Override
    public List<FrequentQuestionEntity> getAllFrequentQuestions() {

        return frequentQuestionRespository.findAll();
    }

}
