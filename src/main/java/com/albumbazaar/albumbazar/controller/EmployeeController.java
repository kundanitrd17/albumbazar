package com.albumbazaar.albumbazar.controller;

import com.albumbazaar.albumbazar.form.LocationForm;
import com.albumbazaar.albumbazar.form.employee.BasicEmployeeDetailForm;
import com.albumbazaar.albumbazar.services.EmployeeService;

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
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(@Qualifier("employeeService") final EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(value = "")
    public ModelAndView allEmployeeView() {
        ModelAndView modelAndView = new ModelAndView("/superuser/emp-list");

        try {
            modelAndView.addObject("employees", employeeService.getAllEmployee());
        } catch (Exception e) {
            modelAndView.addObject("employees", null);
        }

        return modelAndView;
    }

    @GetMapping(value = "add")
    public String viewAddEmployee() {
        return "/superuser/add-emp";
    }

    @PostMapping(value = "add")
    @ResponseBody
    public String addEmployee(@ModelAttribute BasicEmployeeDetailForm employeeDetail,
            @ModelAttribute LocationForm addressDetail) {

        employeeService.addEmployee(employeeDetail, addressDetail);

        return "Added Employee";
    }

}
