package example.application.controllers;

import example.data.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Controller
public class DeleteUserController {

    // opens a connection
    // deletes a user from the database
    // redirects to homepage
    @RequestMapping(value = "/delete-user", method = {RequestMethod.GET, RequestMethod.POST})
    public String deleteUser(@RequestParam String email, @RequestParam String password, @ModelAttribute User user, Model model) {
        model.addAttribute("user", user);
        System.out.println("Inside deleteUserController ");

        // Attempt to retrieve the user from the database.
        JBDConnection jbdConnection = new JBDConnection("jdbc:mysql://foodwastequt.com.au:3306/foodwastequtcom_foodwaste?autoReconnect=true&useSSL=false", "foodwastequtcom_jadezia", "rY[]XPbS.B_M");
        System.out.println(jbdConnection);
        System.out.println("Email: "+ email);
        System.out.println("password: "+ password);

        User userFromDatabase = jbdConnection.getUser(email, password);
        System.out.println(userFromDatabase);
        if (userFromDatabase != null) {
            // Delete the user from the database.
            jbdConnection.deleteUser(email, password);
            System.out.println("User deleted");

            // Redirect to the login page.
            return "redirect:/";

        } else {
            // If the user is not found or the password does not match, display an error
            model.addAttribute("error", "Invalid email or password");
            model.addAttribute("isRegistering", false);
            System.out.println("Returning login view");
        }
        return "redirect:/";

    }



}
