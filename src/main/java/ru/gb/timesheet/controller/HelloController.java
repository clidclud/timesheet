package ru.gb.timesheet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//@Controller
@RestController
public class HelloController {

    // GET http://localhost:8080/hello
//    @RequestMapping(method = RequestMethod.GET, value = "/hello")

    @GetMapping("/hello")
    public String helloPage(@RequestParam(required = false) String username) {
        if(username == null){
            username = "world";
        }
        return "<h1>Hello, " + username + "!</h1>";
    }

    @GetMapping("/hello/{username}")
    public String helloPagePathVariable(@PathVariable String username) {
        if(username == null){
            username = "world";
        }
        return "<h1>Hello, " + username + "!</h1>";
    }
}
