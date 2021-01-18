package com.albumbazaar.albumbazar.services;

import java.util.List;

import com.albumbazaar.albumbazar.dto.ResetPasswordDTO;
import com.albumbazaar.albumbazar.dto.SampleAlbumDTO;
import com.albumbazaar.albumbazar.model.CarasoulEntity;
import com.albumbazaar.albumbazar.model.FrequentQuestionEntity;
import com.albumbazaar.albumbazar.model.SampleAlbumEntity;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.multipart.MultipartFile;

public interface UtilityService {

    void resetCustomerPassword(ResetPasswordDTO resetPasswordDTO) throws UsernameNotFoundException;

    void sendOTPToCustomer(String userIdentificationkey);

    void sendOTPToEmployee(String userIdentificationkey);

    public void resetEmployeePassword(ResetPasswordDTO resetPasswordDTO) throws UsernameNotFoundException;

	void createCarasoul(MultipartFile carasoul);

    void deleteCarasoul(Long id);
    
    List<CarasoulEntity> getAllCarasoul();

    List<SampleAlbumEntity> getAllSampleAlbum();

	void uploadSampleAlbum(SampleAlbumDTO sampleAlbumDTO);

	void deleteSampleAlbum(Long id);

    List<FrequentQuestionEntity> getAllFrequentQuestions();

	void createQuestion(FrequentQuestionEntity frequentQuestion);


     void deleteFrequentQuestion(final Long id);


}
