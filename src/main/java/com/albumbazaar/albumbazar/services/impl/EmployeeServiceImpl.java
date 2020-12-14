package com.albumbazaar.albumbazar.services.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import com.albumbazaar.albumbazar.dao.Address1Repository;
import com.albumbazaar.albumbazar.dao.Address2Repository;
import com.albumbazaar.albumbazar.dao.EmployeeRepository;
import com.albumbazaar.albumbazar.dao.principals.EmployeePrincipal;
import com.albumbazaar.albumbazar.form.LocationForm;
import com.albumbazaar.albumbazar.form.employee.BasicEmployeeDetailForm;
import com.albumbazaar.albumbazar.model.Address1;
import com.albumbazaar.albumbazar.model.Address2;
import com.albumbazaar.albumbazar.model.Employee;
import com.albumbazaar.albumbazar.services.EmployeeService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Qualifier("employeeService")
public class EmployeeServiceImpl implements UserDetailsService, EmployeeService {

    private Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    private final EmployeeRepository employeeRepository;
    private final Address1Repository address1Repository;
    private final Address2Repository address2Repository;

    @Autowired
    public EmployeeServiceImpl(final EmployeeRepository employeeRepository, final Address1Repository address1Repository,
            final Address2Repository address2Repository) {
        this.employeeRepository = employeeRepository;
        this.address1Repository = address1Repository;
        this.address2Repository = address2Repository;
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
    public boolean addEmployee(final BasicEmployeeDetailForm employeeDetail, final LocationForm addressDetail) {
        try {
            // working on the address2 creation (pin address)
            final Address2 address2 = new Address2(addressDetail); // creating the address2 model
            address2Repository.save(address2); // saving the model

            // Working on the address1 creation (street address)
            final Address1 address1 = new Address1(addressDetail); // creating the address1 model
            address1.setAddress2(address2); // mapping the address2 to address1
            address1Repository.save(address1); // saving address1

            // Created employee model object
            final Employee employee = new Employee(employeeDetail); // create a proper object
            employee.setAddress(address1);

            // save a new employee
            employeeRepository.save(employee);

        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return false;
        }

        return true;
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
    public Employee updateEmployee(final Employee updatedEmployeeDetails) {
        // get the employee
        final Employee employee = employeeRepository.findById(updatedEmployeeDetails.getId()).get();
        // update details
        /*
         */
        // save back
        employeeRepository.save(employee);
        return employee;
    }

    @Override
    public Employee deleteEmployee(final Long employeeId) {
        return updateEmployeeStatus(employeeId, false);

    }

    @Override
    public Employee restoreEmployee(final Long employeeId) {
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

}
