package example.application.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class DailyTrackerController {

    @GetMapping("/daily-tracker")
    public String getCustomer(){
        return "daily-tracker.html";
    }
}
