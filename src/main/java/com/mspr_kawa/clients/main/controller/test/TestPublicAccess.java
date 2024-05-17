package com.mspr_kawa.clients.main.controller.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestPublicAccess {

    @GetMapping("/test")
    public String test() {
        return "API public access without authentication";
    }
}
