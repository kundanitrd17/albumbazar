package com.albumbazaar.albumbazar.services;

import java.util.List;
import java.util.Optional;

import com.albumbazaar.albumbazar.form.LocationForm;
import com.albumbazaar.albumbazar.form.employee.BasicEmployeeDetailForm;
import com.albumbazaar.albumbazar.model.Employee;

public interface EmployeeService {

    boolean addEmployee(final BasicEmployeeDetailForm employeeDetail, final LocationForm addressDetail);

    boolean deleteEmployee(Long id);

    Optional<List<Employee>> getAllEmployee();

    Employee getEmployee(Long id);

    Employee updateEmployee(final Employee updatedEmployeeDetails);

}
