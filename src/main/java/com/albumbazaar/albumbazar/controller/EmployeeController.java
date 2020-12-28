package com.albumbazaar.albumbazar.controller;

import java.util.NoSuchElementException;
import java.util.Optional;

import com.albumbazaar.albumbazar.dto.ErrorDTO;
import com.albumbazaar.albumbazar.form.LocationForm;
import com.albumbazaar.albumbazar.form.employee.BasicEmployeeDetailForm;
import com.albumbazaar.albumbazar.services.BranchService;
import com.albumbazaar.albumbazar.services.EmployeeService;
import com.albumbazaar.albumbazar.services.OrderService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "employee")
public final class EmployeeController {

    private final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    private final EmployeeService employeeService;
    private final BranchService branchService;

    @Autowired
    public EmployeeController(@Qualifier("employeeService") final EmployeeService employeeService,
            @Qualifier("branchService") final BranchService branchService) {
        this.employeeService = employeeService;
        this.branchService = branchService;
    }

}
