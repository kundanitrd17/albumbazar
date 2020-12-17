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
    public String home() {

        return "index";
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

    @RequestMapping("/login-user")
    public String loginUser() {
        return "loginUser";
    }

    @RequestMapping("/login-super")
    public String loginSuper() {
        return "superuser/login-super";
    }

    // Need to change this callback url later when I create a separate account for
    // this project itself
    @GetMapping(value = { "/Callback" })

    public String saveGoogleDriveAuthorizationCode(HttpServletRequest request) throws Exception {

        // Object principal =
        // SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // principal. get user id

        // This user id has to taken from the session object after adding security
        final String userId = "harsh";
        final String code = request.getParameter("code");

        googleDriveService.saveGoogleAuthorizationCode(code, userId);

        return "redirect:/";
    }

    @GetMapping(value = { "/googlesignin" })
    public void doGoogleDriveSignIn(HttpServletResponse response) throws Exception {

        if (googleDriveService.isAuthenticatedToGoogle("harsh")) {
            // response.sendRedirect("/");
            System.out.println("already signed-in");
        }

        // Redirect to the url provided by google authorization code flow
        response.sendRedirect(googleDriveService.getRedirectUrlForGoogleSignIn());

    }

}