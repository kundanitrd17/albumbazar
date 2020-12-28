package com.albumbazaar.albumbazar.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.albumbazaar.albumbazar.dao.BranchRepository;
import com.albumbazaar.albumbazar.dao.CoverRepository;
import com.albumbazaar.albumbazar.dao.CustomerRepository;
import com.albumbazaar.albumbazar.dao.EmployeeRepository;
import com.albumbazaar.albumbazar.dao.OrderRepository;
import com.albumbazaar.albumbazar.dao.SuperuserRepository;
import com.albumbazaar.albumbazar.dto.CustomerDTO;
import com.albumbazaar.albumbazar.model.OrderDetailStatus;
import com.albumbazaar.albumbazar.services.GoogleDriveService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public final class HomeController {

    private Logger logger = LoggerFactory.getLogger(HomeController.class);

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
    private CustomerRepository custRepo;

    @Autowired
    private CoverRepository coverRepo;

    @GetMapping("/")
    public ModelAndView index() {
        final ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("customer", custRepo.getOne(1l));

        return modelAndView;
    }

    @GetMapping("/upload-photo")
    public String uploadPhotoTest() {

        return "upload_photos";
    }

    @GetMapping("/user")
    public String user() {
        return "user";
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
        final String userId = "harsh";
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