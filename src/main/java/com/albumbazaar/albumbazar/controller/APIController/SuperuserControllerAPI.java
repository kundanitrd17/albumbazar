package com.albumbazaar.albumbazar.controller.APIController;

import java.util.Hashtable;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;

import javax.validation.Valid;

import com.albumbazaar.albumbazar.dto.BranchDTO;
import com.albumbazaar.albumbazar.dto.EmployeeDTO;
import com.albumbazaar.albumbazar.dto.ErrorDTO;
import com.albumbazaar.albumbazar.model.Association;
import com.albumbazaar.albumbazar.model.Branch;
import com.albumbazaar.albumbazar.model.Customer;
import com.albumbazaar.albumbazar.model.Employee;
import com.albumbazaar.albumbazar.services.AssociationService;
import com.albumbazaar.albumbazar.services.BranchService;
import com.albumbazaar.albumbazar.services.CustomerService;
import com.albumbazaar.albumbazar.services.EmployeeService;
import com.albumbazaar.albumbazar.services.OrderService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/superuser")
public class SuperuserControllerAPI {

    private final Logger logger = LoggerFactory.getLogger(SuperuserControllerAPI.class);

    private final EmployeeService employeeService;
    private final BranchService branchService;
    private final AssociationService associationService;
    private final CustomerService customerService;
    private final OrderService orderService;

    @Autowired(required = true)
    protected SuperuserControllerAPI(@Qualifier("employeeService") final EmployeeService employeeService,
            @Qualifier("orderService") final OrderService orderService,
            @Qualifier("branchService") final BranchService branchService,
            @Qualifier("associationService") final AssociationService associationService,
            @Qualifier("customerService") final CustomerService customerService) {
        this.employeeService = employeeService;
        this.branchService = branchService;
        this.associationService = associationService;
        this.customerService = customerService;
        this.orderService = orderService;
    }

    @GetMapping(value = "/home-page-info")
    public ResponseEntity<?> getSuperuserHomeInfo() {
        final Hashtable<String, Object> info = new Hashtable<>();

        try {
            // Get Employee count
            info.put("employee_count", employeeService.getCountOfAllEmployees());

            // Get Admins
            info.put("admin_list", employeeService.getActiveAdminList());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        try {
            // Get Customer count
            info.put("customer_count", customerService.getCountOfAllCustomers());

        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        try {
            // Get Customer count
            info.put("order_count", orderService.getCountOfAllOrders());

        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return ResponseEntity.ok().body(info);
    }

    /**
     * Deactivate a employee by setting it's active property to false
     * 
     * @param employeeId
     * @return
     */
    @DeleteMapping(value = "employee-delete")
    public ResponseEntity<?> deActivateEmployee(@RequestBody Long employeeId) {

        try {
            final Employee employee = employeeService.deleteEmployee(employeeId);
            return ResponseEntity.ok().body(employee);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PutMapping(value = "employee-restore")
    public ResponseEntity<?> activateEmployee(@RequestBody Long employeeId) {

        try {
            final Employee employee = employeeService.restoreEmployee(employeeId);
            return ResponseEntity.ok().body(employee);
        } catch (Exception e) {
            final ErrorDTO error = new ErrorDTO();
            error.setMessage(e.getMessage());

            return ResponseEntity.badRequest().body(error);
        }

    }

    @PutMapping(value = "/employee/info")
    public ResponseEntity<?> updateEmployeeInfo(@RequestBody final EmployeeDTO employeeDTO) {

        try {
            employeeService.updateEmployeeInfo(employeeDTO);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping(value = "association-delete")
    public ResponseEntity<?> deactivateAssociation(@RequestBody Long associationId) {
        try {
            final Association association = associationService.deleteAssociation(associationId);
            return ResponseEntity.ok().body(association);
        } catch (Exception e) {
            final ErrorDTO error = new ErrorDTO();
            error.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @PutMapping(value = "association-restore")
    public ResponseEntity<?> activateAssociation(@RequestBody Long associationId) {
        try {
            final Association association = associationService.restoreAssociation(associationId);
            return ResponseEntity.ok().body(association);
        } catch (Exception e) {
            final ErrorDTO error = new ErrorDTO();
            error.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }

    }

    @GetMapping(value = "/order/branch")
    public ResponseEntity<?> getOrdersOfBranch(@RequestParam(value = "code") final String branchCode) {

        try {

            System.out.println(branchCode);

            final Branch branch = branchService.getBranchWithCode(branchCode);

            return ResponseEntity.ok().body(orderService.getOrdersOfBranch(branch.getId()));

        } catch (NoSuchElementException e) {
            logger.info(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());

        }

        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping(value = "branch-delete")
    public ResponseEntity<?> deactivateBranch(@RequestBody Long branchId) {

        try {
            final Branch branch = branchService.deletebranch(branchId);
            return ResponseEntity.ok().body(branch);
        } catch (Exception e) {
            final ErrorDTO error = new ErrorDTO();
            error.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }

    }

    @PutMapping(value = "branch-restore")
    public ResponseEntity<?> activateBranch(@RequestBody Long branchId) {
        try {
            final Branch branch = branchService.restoreBranch(branchId);
            return ResponseEntity.ok().body(branch);
        } catch (Exception e) {
            final ErrorDTO error = new ErrorDTO();
            error.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }

    }

    /**
     * Rest api to update branch information
     * 
     * @param Branch Model
     * @return ResponseEntity with json data
     */
    @PutMapping(value = "branch-info")
    public ResponseEntity<?> updateBranchInfo(@RequestBody BranchDTO branchInfo) {
        System.out.println(branchInfo);

        try {
            final Branch branch = branchService.updateBranch(branchInfo);
            return ResponseEntity.ok().body(branch);
        } catch (Exception e) {

            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping(value = "admin-detail/{id}")
    public ResponseEntity<?> getAdminDetailsWithId(@PathVariable(name = "id", required = true) String id) {

        try {
            return ResponseEntity.ok().body(employeeService.getEmployee(Long.parseLong(id)));
        } catch (NumberFormatException e) {
            final ErrorDTO error = new ErrorDTO();
            error.setMessage("Invalid id type");
            return ResponseEntity.badRequest().body(error);
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("body");
        }
    }

    @DeleteMapping(value = "customer-delete")
    public ResponseEntity<?> deactivateCustomer(@RequestBody Long customerId) {

        try {
            customerService.deleteCustomer(customerId);
            return ResponseEntity.ok().body("done deleting");
        } catch (Exception e) {
            logger.error(e.getMessage());

            final ErrorDTO error = new ErrorDTO();
            error.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }

    }

    @PutMapping(value = "customer-restore")
    public ResponseEntity<?> activateCustomer(@RequestBody Long customerId) {
        try {

            customerService.restoreCustomer(customerId);
            return ResponseEntity.ok().body("done restoring");
        } catch (Exception e) {
            logger.error(e.getMessage());
            final ErrorDTO error = new ErrorDTO();
            error.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }

    }

}
