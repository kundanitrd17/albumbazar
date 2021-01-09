package com.albumbazaar.albumbazar.services.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.albumbazaar.albumbazar.dao.EmployeeRepository;
import com.albumbazaar.albumbazar.form.LocationForm;
import com.albumbazaar.albumbazar.form.employee.BasicEmployeeDetailForm;
import com.albumbazaar.albumbazar.model.AvailableRoles;
import com.albumbazaar.albumbazar.model.Employee;
import com.albumbazaar.albumbazar.principals.EmployeePrincipal;
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


    @Autowired
    public EmployeeServiceImpl(final EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
        
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

}
