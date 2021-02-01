package com.albumbazaar.albumbazar.services.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.albumbazaar.albumbazar.Mapper.AddressMapper;
import com.albumbazaar.albumbazar.dao.AddressRepository;
import com.albumbazaar.albumbazar.dao.EmployeeRepository;
import com.albumbazaar.albumbazar.dto.AddressDTO;
import com.albumbazaar.albumbazar.dto.EmployeeDTO;
import com.albumbazaar.albumbazar.form.LocationForm;
import com.albumbazaar.albumbazar.form.employee.BasicEmployeeDetailForm;
import com.albumbazaar.albumbazar.model.AddressEntity;
import com.albumbazaar.albumbazar.model.AvailableRoles;
import com.albumbazaar.albumbazar.model.Branch;
import com.albumbazaar.albumbazar.model.Employee;
import com.albumbazaar.albumbazar.principals.EmployeePrincipal;
import com.albumbazaar.albumbazar.services.AddressService;
import com.albumbazaar.albumbazar.services.BranchService;
import com.albumbazaar.albumbazar.services.EmployeeService;

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
@Qualifier("employeeService")
public class EmployeeServiceImpl implements UserDetailsService, EmployeeService {

    private Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    private final EmployeeRepository employeeRepository;

    private final AddressService addressService;
    private final BranchService branchService;

    private final AddressMapper addressMapper;

    @Autowired
    public EmployeeServiceImpl(final EmployeeRepository employeeRepository,
            @Qualifier("addressService") final AddressService addressService, final AddressMapper addressMapper,
            final BranchService branchService) {
        this.addressService = addressService;
        this.employeeRepository = employeeRepository;
        this.branchService = branchService;
        this.addressMapper = addressMapper;
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        System.out.println("In user service");
        Employee user = employeeRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User 404");
        }

        return new EmployeePrincipal(user);
    }

    @Override
    public Employee loadByEmail(final String email) throws UsernameNotFoundException {

        return employeeRepository.findByEmail(email);
    }

    @Override
    public boolean addEmployee(final BasicEmployeeDetailForm employeeDetail, final LocationForm addressDetail) {
        try {

            // Created employee model object
            final Employee employee = new Employee(employeeDetail); // create a proper object

            // save a new employee
            employeeRepository.save(employee);

        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return false;
        }

        return true;
    }

    @Transactional
    @Override
    public Employee createEmployee(final EmployeeDTO employeeDTO, final LocationForm locationForm) {

        final Employee employee = new Employee();
        employee.setName(employeeDTO.getName());
        employee.setEmail(employeeDTO.getEmail());

        try {
            final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
            final Date joiningdate = dateFormat.parse(employeeDTO.getJoining_date());
            employee.setJoining_date(joiningdate);

        } catch (ParseException e) {
            logger.error(e.getMessage());
            throw new RuntimeException("Joining Date Not found");
        }

        employee.setPersonal_contact(employeeDTO.getPersonal_contact());
        employee.setSalary(employeeDTO.getSalary());
        employee.setRole(employeeDTO.getRole().toString());
        employee.setPassword(employeeDTO.getPassword());

        final Branch branch = branchService.getbranch(employeeDTO.getBranchId());
        employee.setBranch(branch);

        try {
            final AddressEntity addressEntity = new AddressEntity(locationForm);
            addressEntity.setContactNo(employee.getPersonal_contact());
            addressEntity.setName(employee.getName());

            employee.setAddress(addressService.saveAddress(addressEntity));
        } catch (Exception e) {
            logger.info(e.getMessage());
        }

        return employeeRepository.save(employee);

    }

    @Override
    public List<Employee> getAllEmployee() {

        final List<Employee> employees = employeeRepository.findAll().stream().collect(Collectors.toList());

        return employees;
    }

    @Override
    public Employee getEmployee(Long id) throws NoSuchElementException {
        return employeeRepository.findById(id).get();
    }

    @Override
    @Transactional
    public Employee updateEmployee(final Employee updatedEmployeeDetails) {
        // get the employee
        final Employee employee = employeeRepository.findById(updatedEmployeeDetails.getId()).orElseThrow();
        // update details
        /*
         */
        // save back
        employeeRepository.save(employee);
        return employee;
    }

    @Override
    public Employee deleteEmployee(final Long employeeId) {

        if (employeeId == null) {
            throw new RuntimeException("Unable to find Employee");
        }

        return updateEmployeeStatus(employeeId, false);

    }

    @Override
    public Employee restoreEmployee(final Long employeeId) {
        if (employeeId == null) {
            throw new RuntimeException("Unable to find Employee");
        }
        return updateEmployeeStatus(employeeId, true);
    }

    private Employee updateEmployeeStatus(final Long employeeId, final Boolean status) {
        try {
            // find the employee
            final Employee employee = employeeRepository.findById(employeeId).orElseThrow();
            employee.setActive(status); // set active status to false
            return employeeRepository.save(employee);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException("Unable to make changes");
        }
    }

    @Override
    public Set<AvailableRoles> getAllAvailableEmployeeRole() {

        return Stream.of(AvailableRoles.values()).filter(role -> {
            if (role == AvailableRoles.USER || role == AvailableRoles.BRANCH) {
                return false;
            }
            return true;
        }).collect(Collectors.toSet());
    }

    @Override
    @Transactional
    public void updateAddressInfo(final AddressDTO addressDTO, final Long employeeId) {
        final Employee employee = this.getEmployee(employeeId);

        if (employee.getAddress() != null)
            addressService.deleteAddress(employee.getAddress());

        final AddressEntity address = addressMapper.addressDTOToAddressEntity(addressDTO);

        final AddressEntity savedAddressEntity = addressService.saveAddress(address);
        employee.setAddress(savedAddressEntity);

    }

    @Override
    @Transactional
    public void updateEmployeeInfo(final EmployeeDTO employeeDTO) {

        final Employee employee = this.getEmployee(employeeDTO.getId());

        employee.setName(employeeDTO.getName());
        employee.setPersonal_contact(employeeDTO.getPersonal_contact());
        employee.setSalary(employeeDTO.getSalary());
        employee.setEmail(employeeDTO.getEmail());

    }

    @Override
    @Transactional(readOnly = true)
    public Long getCountOfAllEmployees() {

        return employeeRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employee> getActiveAdminList() {
        return employeeRepository.findAllByActiveAndRoleIn(true, Arrays.asList(AvailableRoles.ADMIN.toString()));
    }

}
