package example.application.controllers;

import example.data.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


/**
 * Controller for the login page.
 */
@Controller
public class RegisterController {
    /**
     * The singleton instance of the database connection. This is used to access the
     * database of users.
     */
    private final String url = "jdbc:mysql://foodwastequt.com.au:3306/foodwastequtcom_foodwaste?autoReconnect=true&useSSL=false";

    private final String username = "foodwastequtcom_jadezia";
    private final String password = "rY[]XPbS.B_M";
    private final StaticUserDAO userDAO = new StaticUserDAO(url, username, password);


    /**
     /**
     * Displays the login page if user not logged in.
     *
     * @param model The model to add attributes to.
     * @return The name of the view to display.
     */
    @GetMapping("/register")
    public String showLoginForm(Model model) {
        // The user attribute is initialized to an empty user.
        try {
            model.addAttribute("user", new User("","","", ""));
        } catch (InvalidUserException e) {
            throw new RuntimeException(e);
        }
        // When the user first visits the login page, they are not registering.
        model.addAttribute("isRegistering", false);
        return "register-view";
    }

    /**
     * ---------------- Marnie fixed this ----------------
     * Attempts to register the user. If successful, displays a success message and
     * returns to the login page. If unsuccessful, displays an error message and returns
     * to the login page.
     * ---------------- THIS WORKS  ----------------------
     */

    @RequestMapping(value = "/register", method = RequestMethod.POST, params = "register")
    public String registerSubmit(@ModelAttribute User user, Model model) {
        if (userDAO.getUser(user.getEmail()) != null) {
            model.addAttribute("error", "Email already registered");
        } else {
            JBDConnection jbdConnection = new JBDConnection("jdbc:mysql://foodwastequt.com.au:3306/foodwastequtcom_foodwaste?autoReconnect=true&useSSL=false", "foodwastequtcom_jadezia", "rY[]XPbS.B_M");
            jbdConnection.insertUser(user);
            model.addAttribute("success", "Registration successful. Please fill in your name before logging in.");
            model.addAttribute("isRegistering", true);
        }
        model.addAttribute("user", user);
        return "login-view";
    }

}




