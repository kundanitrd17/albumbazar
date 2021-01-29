package com.albumbazaar.albumbazar.services.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import com.albumbazaar.albumbazar.Mapper.AddressMapper;
import com.albumbazaar.albumbazar.dao.AddressRepository;
import com.albumbazaar.albumbazar.dao.BranchRepository;
import com.albumbazaar.albumbazar.dao.EmployeeRepository;
import com.albumbazaar.albumbazar.dto.AddressDTO;
import com.albumbazaar.albumbazar.dto.BranchDTO;
import com.albumbazaar.albumbazar.model.AddressEntity;
import com.albumbazaar.albumbazar.model.Branch;
import com.albumbazaar.albumbazar.services.BranchService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Qualifier("branchService")
public class BranchServiceImpl implements BranchService {

    private final Logger logger = LoggerFactory.getLogger(BranchServiceImpl.class);

    final BranchRepository branchRepository;
    final EmployeeRepository employeeRepository;

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    @Autowired
    public BranchServiceImpl(final AddressRepository addressRepository, final AddressMapper addressMapper,
            final BranchRepository branchRepository, final EmployeeRepository employeeRepository) {

        this.addressMapper = addressMapper;
        this.addressRepository = addressRepository;
        this.branchRepository = branchRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    @Transactional
    public boolean addBranch(final BranchDTO branchDTO, final AddressDTO locationDetails) {
        try {

            // Working on the branch creation
            final Branch branch = new Branch();
            branch.setActive(true);
            branch.setBranchCode(branchDTO.getBranchCode());
            branch.setContactNo(branchDTO.getContactNo());
            branch.setEmail(branchDTO.getEmail());
            branch.setName(branchDTO.getName());

            final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
            final Date inaugrationDate = dateFormat.parse(branchDTO.getDate());
            branch.setDate(inaugrationDate);

            final AddressEntity addressEntity = addressMapper.addressDTOToAddressEntity(locationDetails);

            branch.setAddress(addressRepository.save(addressEntity));

            branchRepository.save(branch); // saving the branch

        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    @Transactional
    public Branch updateBranch(final BranchDTO updatedBranchInfo) {

        final Branch branch = branchRepository.findById(updatedBranchInfo.getId()).orElseThrow();

        if (updatedBranchInfo.getName() != null && !updatedBranchInfo.getName().isBlank()) {
            branch.setName(updatedBranchInfo.getName().trim());
        }

        if (updatedBranchInfo.getContactNo() != null && !updatedBranchInfo.getContactNo().isBlank()) {
            branch.setContactNo(updatedBranchInfo.getContactNo().trim());
        }

        /**
         * Change other parameters for updates
         */

        return branch;
    }

    @Override
    public Optional<List<Branch>> getAllBranch() {
        final List<Branch> allBranch = branchRepository.findAll().stream().collect(Collectors.toList());

        return Optional.of(allBranch);
    }

    @Override
    public Branch getbranch(final Long branchId) throws NoSuchElementException {
        return branchRepository.findById(branchId).get();
    }

    @Override
    public Branch deletebranch(final Long branchId) {

        return updateBranchStatus(branchId, false);
    }

    @Override
    public Branch restoreBranch(Long branchId) {
        return updateBranchStatus(branchId, true);
    }

    private final Branch updateBranchStatus(final Long branchId, final Boolean status) {
        try {
            final Branch branch = branchRepository.findById(branchId).orElseThrow();
            branch.setActive(status);
            return branchRepository.save(branch);

        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException("Unable to make change to the branch");
        }
    }

    @Override
    public List<BranchDTO> getAllActiveBranchName() {
        final List<BranchDTO> listOfBranchDTO = new ArrayList<>();

        branchRepository.getNameOfAllActiveBranches().ifPresentOrElse(branches -> branches.stream().forEach(branch -> {
            try {
                BranchDTO branchDTO = new BranchDTO();
                branchDTO.setId(Long.parseLong(branch[0].toString()));
                branchDTO.setName(branch[1].toString());

                listOfBranchDTO.add(branchDTO);
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }), () -> {
            throw new NoSuchElementException("No value");
        });

        return listOfBranchDTO;

    }

    @Override
    @Transactional
    public void updateAddressInfo(final AddressDTO addressDTO, final Long branchId) {

        final Branch branch = this.getbranch(branchId);

        if (branch.getAddress() != null)
            addressRepository.delete(branch.getAddress());

        final AddressEntity address = addressMapper.addressDTOToAddressEntity(addressDTO);

        final AddressEntity newAddress = addressRepository.save(address);
        branch.setAddress(newAddress);

    }

}
