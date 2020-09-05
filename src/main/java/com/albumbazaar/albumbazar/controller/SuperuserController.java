package com.albumbazaar.albumbazar.controller;

import com.albumbazaar.albumbazar.form.ForgotPasswordFormSuperuser;
import com.albumbazaar.albumbazar.services.SuperuserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/superuser")
public class SuperuserController {

    private final SuperuserDetailsService superuserDetailsService;

    @Autowired
    public SuperuserController(@Qualifier("superuserDetailsService") SuperuserDetailsService superuserDetailsService) {
        this.superuserDetailsService = superuserDetailsService;
    }

    @RequestMapping(value = "/api/resetsuperuser", method = RequestMethod.POST)
    @ResponseBody
    public ModelMap hello(@ModelAttribute ForgotPasswordFormSuperuser forgotPasswordForm) {
        System.out.println("Hello password " + forgotPasswordForm.getPassword1() + forgotPasswordForm.getPassword2());
        System.out.println(forgotPasswordForm);

        return superuserDetailsService.resetPassword(forgotPasswordForm);
    }


    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index() {

        System.out.println("Superuser controller");

        return "superuser";
    }

}