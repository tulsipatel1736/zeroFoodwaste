package example.application.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class TermsOfUseController {

    @GetMapping("/terms-of-use")
    public String getCustomer(){
        return "terms-of-use.html";
    }
}
