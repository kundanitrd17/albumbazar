package com.albumbazaar.albumbazar.services;

import java.lang.StackWalker.Option;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import com.albumbazaar.albumbazar.dao.EmployeeRepository;
import com.albumbazaar.albumbazar.dao.principals.EmployeePrincipal;
import com.albumbazaar.albumbazar.model.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Qualifier("employeeService")
public class EmployeeService implements UserDetailsService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(final EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        System.out.println("In user service");
        Employee user = employeeRepository.findByEmail(username);
        if(user==null) {
            throw new UsernameNotFoundException("User 404");
        }

        return new EmployeePrincipal(user);
    }

    public boolean addEmployee(/* Take in form attributes or model attributes */) {

        // Created employee model object
        final Employee employee = new Employee(); // create a proper object

        // valid it then save it
        
        // save a new employee
        employeeRepository.save(employee);

        return true;
    }

    public boolean deleteEmployee(Long id) { // might throw employee not found

        // find the employee
        final Employee employee = employeeRepository.findById(id).get();
        employee.setActive(false); // set active status to false
        employeeRepository.save(employee);

        return true;
    }

    public Optional<List<Employee>> getAllEmployee() {

        final List<Employee> employees = employeeRepository.findAll().stream().collect(Collectors.toList());
        
        return Optional.of(employees);
    }

    public Employee getEmployee(Long id) throws NoSuchElementException {
        return employeeRepository.findById(id).get();
    }

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
    
}
