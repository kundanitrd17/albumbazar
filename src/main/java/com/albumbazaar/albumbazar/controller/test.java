package com.albumbazaar.albumbazar.controller;

import java.nio.file.Path;
import java.util.stream.Collectors;

import com.albumbazaar.albumbazar.model.OrderDetailStatus;
import com.albumbazaar.albumbazar.services.OrderService;
import com.albumbazaar.albumbazar.services.storage.StorageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

@Controller
public class test {

    @Autowired
    StorageService imageStoreService;

    @Autowired
    OrderService orderService;

    @GetMapping("/test/order")
    @ResponseBody
    public ResponseEntity<?> orderget(@RequestParam("status") OrderDetailStatus status, @RequestParam("page") int page,
            @RequestParam(value = "size", defaultValue = "20") int size) {

        System.out.println(status + " " + page + " " + size);
        return ResponseEntity.ok().body(orderService.getOrdersWithStatus(status, page, size));

    }

    @GetMapping("/test")
    public ModelAndView testUpload(Model model) {

        Path p = imageStoreService.load("harsh.png");
        System.out.println(p);
        System.out.println(imageStoreService.load("harsh.png"));

        ModelAndView mv = new ModelAndView("test");
        Resource ff = imageStoreService.loadAsResource("harsh.png");

        model.addAttribute("files",
                imageStoreService.loadAll()
                        .map(path -> MvcUriComponentsBuilder
                                .fromMethodName(test.class, "serveFile", path.getFileName().toString()).build().toUri()
                                .toString())
                        .collect(Collectors.toList()));

        System.out.println(model);

        String s = MvcUriComponentsBuilder
                .fromMethodName(test.class, "serveFile", imageStoreService.load("harh.png").getFileName().toString())
                .build().toUri().toString();

        System.out.println(s);
        mv.addObject("photo", s);

        return mv;
    }

    @PostMapping("/test")
    public String posttestUpload(@RequestPart("photo") MultipartFile file) {

        imageStoreService.store(file, file.getOriginalFilename());

        return "redirect:/test";
    }

}
