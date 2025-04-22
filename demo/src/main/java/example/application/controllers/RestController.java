package example.application.controllers;

import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    // This is a request mapping API
    @RequestMapping("/")
    public String getGreeting(){
        return "This is the getGreeting rest controller";
    }
}
