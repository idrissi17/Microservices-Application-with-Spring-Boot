package org.example.accounts.web;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountsController {

    @GetMapping("/hello")
    public String hello() {
        return "hi";
    }


}
