package com.jit.websecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestRolesController {

    @GetMapping("/admin")
    public String accessAdmin() {
        return "Accediste como ADMIN";
    }

    @GetMapping("/user")
    public String accessUser() {
        return "Accediste como USER";
    }

    @GetMapping("/invited")
    public String accessInvited() {
        return "Accediste como INVITED";
    }
}
