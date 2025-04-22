package example.application.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class PrivacyPolicyController {

    @GetMapping("/privacy-policy")
    public String getCustomer(){
        return "privacy-policy.html";
    }
}
