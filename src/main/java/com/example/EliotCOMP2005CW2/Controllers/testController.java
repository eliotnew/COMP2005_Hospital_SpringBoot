package com.example.EliotCOMP2005CW2.Controllers;
import org.springframework.web.bind.annotation.*;

@RestController
public class testController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }
}
