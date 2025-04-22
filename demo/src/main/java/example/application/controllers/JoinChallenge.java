package example.application.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class JoinChallenge {

    @GetMapping("/join-challenge")
    public String getCustomer(){
        return "joinChallenge.html";
    }
}
