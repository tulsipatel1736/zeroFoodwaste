package example.application.controllers;

import example.data.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


// Controller controls what page is shown when someone is logged in
// Gets user information from database which is passed in from the login-pages
// Renders
@Controller
public class LoggedInController {
    @RequestMapping("/logged-in")
    public String showMainView(@ModelAttribute("user") User userFromDatabase, Model model) {
        model.addAttribute("user", userFromDatabase);

        if(userFromDatabase.getUserType().equals("Student")) {
            return "student-view";
        } else {
            return "Teacher-view";
        }
    }
}