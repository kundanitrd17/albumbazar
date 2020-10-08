package com.albumbazaar.albumbazar.controller;

import java.util.HashMap;
import java.util.List;

import com.albumbazaar.albumbazar.model.Association;
import com.albumbazaar.albumbazar.services.AssociationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "association")
public class AssociationController {

    private AssociationService associationService;

    @Autowired
    public AssociationController(@Qualifier("associationService") final AssociationService associationService) {
        this.associationService = associationService;
    }

    @GetMapping(value = "")
    @ResponseBody
    public HashMap<String, Object> home() {
        List<Association> associations;
        HashMap<String, Object> hmap = new HashMap<>();
        try {
            associations = associationService.getAllAssociation();
        } catch (Exception e) {
            associations = null;
        }
        hmap.put("data", associations);
        return hmap;
    }

}
