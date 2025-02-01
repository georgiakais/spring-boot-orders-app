package com.mydata.controller.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginPage() {
        return "login";  // view for login
    }
}