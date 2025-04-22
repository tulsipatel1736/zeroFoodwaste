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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Controller for the login page.
 */
@Controller
public class LoginController {
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
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        // The user attribute is initialized to an empty user.
        try {
            model.addAttribute("user", new User("","","", ""));
        } catch (InvalidUserException e) {
            throw new RuntimeException(e);
        }
        // When the user first visits the login page, they are not registering.
        model.addAttribute("isRegistering", false);
        return "login-view";
    }

    /**
     * Attempts to log in the user. If successful, redirects to the logged in page. If
     * unsuccessful, displays an error message and returns to the login page.
     * @param model The model to add attributes to.
     * @return The name of the view to display.
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST, params = "login")
    public String loginSubmit(@ModelAttribute User user, Model model, RedirectAttributes redirectAttrs) {
        // Attempt to retrieve the user from the database.
        JBDConnection jbdConnection = new JBDConnection("jdbc:mysql://foodwastequt.com.au:3306/foodwastequtcom_foodwaste?autoReconnect=true&useSSL=false", "foodwastequtcom_jadezia", "rY[]XPbS.B_M");
        System.out.println(jbdConnection);
        String name = user.getName();
        String email = user.getEmail();
        String password = user.getPassword();
        System.out.println("Email in requestmap: "+ email);
        System.out.println("password in requestmap: "+ password);

        User userFromDatabase = jbdConnection.getUser(email, password);
        System.out.println(userFromDatabase);
        if (userFromDatabase != null) {
            // If the user is found and the password matches, update the user's name if it
            // has been changed.
            if (user.getName() != null && !user.getName().isEmpty())
                userFromDatabase.setName(user.getName());

            // Add the user to the redirect attributes so that it can be accessed by the
            // logged in page controller.
            redirectAttrs.addFlashAttribute("user", userFromDatabase);
            return "redirect:/logged-in";

        }

        // If the user is not found or the password does not match, display an error
        model.addAttribute("error", "Invalid email or password");
        model.addAttribute("isRegistering", false);
        System.out.println("Returning login view");
        return "redirect:/login-view";
    }





    /**
     * ---------------- Marnie fixed this ----------------
     * Attempts to register the user. If successful, displays a success message and
     * returns to the login page. If unsuccessful, displays an error message and returns
     * to the login page.
     * ---------------- THIS WORKS  ----------------------
     */
    // Passes user information from register page to login page
    @RequestMapping(value = "/login", method = RequestMethod.POST, params = "register")
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


//    /**
//     * When user clicks Logout, invalidate the current session and redirect the user to the login page
//     */

    /**
     * ---------------- Marnie fixed this ----------------
     * Logs a user out
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.invalidate();
        System.out.print("User logged out");
        return "redirect:/login";
//        return "login-view";
    }
}

















//    /**
//     * Attempts to log in the user. If successful, redirects to the main page. If
//     * unsuccessful, displays an error message and returns to the login page.
//     *
//     * @param model The model to add attributes to.
//     * @return The name of the view to display.
//     */
//    @RequestMapping(value = "/login", method = RequestMethod.POST, params = "login")
//    public String loginSubmit(@ModelAttribute User user, Model model, RedirectAttributes redirectAttrs,
//                              HttpServletResponse response, HttpServletRequest request, @RequestParam(value = "rememberMe", required = false) boolean rememberMe) throws ServletException, IOException {
//
//        //another check if the "remember me" option was selected option #2
//        boolean rememberMeValue = checkRememberMe(request);
//
//        // Attempt to retrieve the user from the database.
//        User u = userDAO.getUser(user.getEmail());
//        if (u != null && u.getPassword().equals(user.getPassword())) {
//            // If the user is found and the password matches, update the user's name if it
//            // has been changed.
//            if (user.getName() != null && !user.getName().isEmpty())
//                u.setName(user.getName());
//
//            // Add the user to the session
//            HttpSession session = (HttpSession) request.getSession();
//            session.setAttribute("user", u);
//
//            // Check if the "remember me" option was selected. old option
//            //String rememberMeValue = request.getParameter("rememberMe");
//            //if (rememberMeValue != null && rememberMeValue.equals("true")) {
//             if(rememberMe) {
//                // Generate a unique token for the user and store it in a cookie.
//                String token = UUID.randomUUID().toString();
//                Cookie rememberMeCookie = new Cookie(REMEMBER_ME_COOKIE_NAME, token);
//                rememberMeCookie.setMaxAge(REMEMBER_ME_COOKIE_MAX_AGE);
//                rememberMeCookie.setHttpOnly(true);
//                response.addCookie(rememberMeCookie);
//
//                // Store the token in the database so that we can match it with the user
//                // when they return.
//                userDAO.addRememberMeToken(u, token);
//            }
//
//            // Add the user to the redirect attributes so that it can be accessed by the
//            // main page controller.
//            redirectAttrs.addFlashAttribute("user", u);
//            return "redirect:/main";
//        }
//
//        // If the user is not found or the password does not match, display an error
//        model.addAttribute("error", "Invalid email or password");
//        model.addAttribute("isRegistering", false);
//        return "login-view";
//    }
//
//    /**
//     * Attempts to getCustomer the user. If successful, displays a success message and
//     * returns to the login page. If unsuccessful, displays an error message and returns
//     * to the login page.
//     *
//     * @param model The model to add attributes to.
//     * @return The name of the view to display.
//     */
//    @RequestMapping(value = "/login", method = RequestMethod.POST, params = "getCustomer")
//    public String registerSubmit(@ModelAttribute User user, Model model) {
//        if (userDAO.getUser(user.getEmail()) != null) {
//            model.addAttribute("error", "Email already registered");
//        } else {
//            userDAO.addUser(user);
//            model.addAttribute("success", "Registration successful. Please fill in your name before logging in.");
//            model.addAttribute("isRegistering", true);
//        }
//        model.addAttribute("user", user);
//        return "login-view";
//    }
//    private boolean checkRememberMe(HttpServletRequest request) {
//        String rememberMeValue = request.getParameter("rememberMe");
//        return rememberMeValue != null && rememberMeValue.equals("true");
//        }
//    }




