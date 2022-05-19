package com.example.springSecImp.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class LoginController {

    @GetMapping("login")
    public String getLoginView(){
        return "login";
    }

    @GetMapping("courses")
    public String getCourses(){
        return "courses";
    }
}
