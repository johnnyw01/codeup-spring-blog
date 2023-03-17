package com.codeup.codeupspringblog.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class MathController {

    @GetMapping("/add/{a}/and/{b}")
    public double add(@PathVariable double a, @PathVariable double b) {
        return a + b;
    }

    @GetMapping("/subtract/{b}/from/{a}")
    public double subtract(@PathVariable double a, @PathVariable double b) {
        return a - b;
    }

    @GetMapping("/multiply/{a}/and/{b}")
    public double multiply(@PathVariable double a, @PathVariable double b) {
        return a * b;
    }

    @GetMapping("/divide/{a}/by/{b}")
    public double divide(@PathVariable double a, @PathVariable double b) {
        return a / b;
    }

}


//In this code, we define a class called MathController that listens for HTTP GET requests to four different routes, one for each arithmetic operation. The @GetMapping annotation specifies the URL path for each route, and the @PathVariable annotation extracts the values for the variables a and b from the URL.
//
//The actual arithmetic is performed by simple math expressions in each method. The result of each operation is returned as a double value, which will be automatically converted to JSON by Spring Boot and sent back as the response to the client.
//
//Note that we've used the @RestController annotation on the class, which is a shortcut for @Controller and @ResponseBody. This tells Spring Boot to treat the class as a REST controller and automatically serialize the return value of each method as a JSON response.