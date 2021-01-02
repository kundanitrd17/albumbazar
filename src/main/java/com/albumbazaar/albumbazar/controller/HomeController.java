package com.albumbazaar.albumbazar.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.albumbazaar.albumbazar.Mapper.CustomerMapper;
import com.albumbazaar.albumbazar.dao.AddressRepository;
import com.albumbazaar.albumbazar.dao.AssociationRepository;
import com.albumbazaar.albumbazar.dao.BranchRepository;
import com.albumbazaar.albumbazar.dao.CoverRepository;
import com.albumbazaar.albumbazar.dao.CustomerRepository;
import com.albumbazaar.albumbazar.dao.EmployeeRepository;
import com.albumbazaar.albumbazar.dao.OrderRepository;
import com.albumbazaar.albumbazar.dao.SuperuserRepository;
import com.albumbazaar.albumbazar.dao.TestingRepository;
import com.albumbazaar.albumbazar.dto.CustomerDTO;
import com.albumbazaar.albumbazar.model.Customer;
import com.albumbazaar.albumbazar.principals.CustomerPrincipal;
import com.albumbazaar.albumbazar.services.CustomerService;
import com.albumbazaar.albumbazar.services.GoogleDriveService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public final class HomeController {

    private Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    @Qualifier("customerService")
    private CustomerService customerService;

    @Autowired
    private AddressRepository addressRepo;

    @Autowired
    private GoogleDriveService googleDriveService;

    @Autowired
    private SuperuserRepository repo;

    @Autowired
    private EmployeeRepository erepo;
    @Autowired
    private BranchRepository brepo;
    @Autowired
    private OrderRepository orderrepo;

    @Autowired
    private AssociationRepository assRepo;

    @Autowired
    private CustomerRepository custRepo;

    @Autowired
    private CoverRepository coverRepo;

    @Autowired
    private TestingRepository testRepo;

    @GetMapping("/")
    public ModelAndView index() {

        final ModelAndView modelAndView = new ModelAndView("index");

        try {

            if (SecurityContextHolder.getContext().getAuthentication() != null) {
                // Get Customer id from the pricipal objects
                final Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

                if (principal instanceof CustomerPrincipal) {
                    final CustomerPrincipal customerPrincipal = (CustomerPrincipal) principal;
                    Customer customer = customerService.getCustomer(customerPrincipal.getId());
                    CustomerDTO customerDTO = customerMapper.customerEntityToCustomerDTO(customer);

                    modelAndView.addObject("customer", customerDTO);
                }
            }

        } catch (Exception e) {
            logger.info(e.getMessage());
        }

        return modelAndView;
    }

    @GetMapping("/upload-photo")
    public String uploadPhotoTest() {

        return "upload_photos";
    }

    @GetMapping("/user")
    @ResponseBody
    public ResponseEntity<?> user() {
        System.out.println("\n\n\n\n\n\n...................................................");

        Customer customer = custRepo.getOne(1l);

        CustomerDTO cdto = customerMapper.customerEntityToCustomerDTO(customer);
        System.out.println(cdto);
        System.out.println(customer);
        System.out
                .println(customerMapper.customerEntityToCustomerDTO(customerMapper.customerDTOToCustomerEntity(cdto)));
        System.out.println("\n\n\n\n\n\n...................................................");

        return ResponseEntity.ok(cdto);
    }

    @GetMapping("/foo")
    @ResponseBody
    public String foo() {
        return "foo";
    }

    @RequestMapping("/login-super")
    public String loginSuper() {
        return "superuser/login_super";
    }

    @RequestMapping("/login-delivery")
    public String loginDelivery() {
        return "login-customer";
    }

    // Need to change this callback url later when I create a separate account for
    // this project itself
    @GetMapping(value = { "/Callback" })

    public String saveGoogleDriveAuthorizationCode(HttpServletRequest request) throws Exception {

        // Object principal =
        // SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // principal. get user id

        // This user id has to taken from the SecurityContextHolder object after adding
        // spring security
        // Get Customer id from the pricipal objects
        final CustomerPrincipal principal = (CustomerPrincipal) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();

        final String userId = principal.getUsername();
        final String code = request.getParameter("code");

        googleDriveService.saveGoogleAuthorizationCode(code, userId);

        System.out.println("request object has---------------");
        System.out.println(request.getRequestURL());
        System.out.println(request.getRequestURI());

        return "redirect:/";
    }

    @GetMapping(value = { "/googlesignin" })
    public void doGoogleDriveSignIn(HttpServletResponse response) throws Exception {

        // Redirect to the url provided by google authorization code flow for OAuth
        // sign-in and wait at the callback to get the secret code
        String url = googleDriveService.getRedirectUrlForGoogleSignIn();
        System.out.println(url);
        response.sendRedirect(url);

    }

}