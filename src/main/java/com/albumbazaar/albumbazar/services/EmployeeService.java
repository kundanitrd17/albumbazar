package com.albumbazaar.albumbazar.services;

import java.util.List;
import java.util.Set;

import com.albumbazaar.albumbazar.dto.EmployeeDTO;
import com.albumbazaar.albumbazar.form.LocationForm;
import com.albumbazaar.albumbazar.form.employee.BasicEmployeeDetailForm;
import com.albumbazaar.albumbazar.model.AvailableRoles;
import com.albumbazaar.albumbazar.model.Employee;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface EmployeeService {

    boolean addEmployee(final BasicEmployeeDetailForm employeeDetail, final LocationForm addressDetail);

    void createEmployee(EmployeeDTO employeeDTO);

    Employee deleteEmployee(Long id);

    Employee restoreEmployee(Long id);

    List<Employee> getAllEmployee();

    Employee getEmployee(Long id);

    Employee loadByEmail(final String email) throws UsernameNotFoundException;

    Employee updateEmployee(final Employee updatedEmployeeDetails);

    /**
     * Get a list of all the roles that are allowed for a employee
     * 
     * @return List of AvailableRoles enum class
     */
    Set<AvailableRoles> getAllAvailableEmployeeRole();

}
