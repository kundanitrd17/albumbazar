package com.albumbazaar.albumbazar.services;

import java.util.List;

import com.albumbazaar.albumbazar.form.LocationForm;
import com.albumbazaar.albumbazar.form.employee.BasicEmployeeDetailForm;
import com.albumbazaar.albumbazar.model.Employee;

public interface EmployeeService {

    boolean addEmployee(final BasicEmployeeDetailForm employeeDetail, final LocationForm addressDetail);

    Employee deleteEmployee(Long id);

    Employee restoreEmployee(Long id);

    List<Employee> getAllEmployee();

    Employee getEmployee(Long id);

    Employee updateEmployee(final Employee updatedEmployeeDetails);

}
