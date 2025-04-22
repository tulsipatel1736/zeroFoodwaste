package example.application.controllers;

import example.data.StaticUserDAO;
import example.data.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for the main page, displayed after the user has logged in.
 */
@Controller
public class MainViewController {
    /**
     * The singleton instance of the database connection. This is used to access the
     * database of users.
     */
    //private final StaticUserDAO userDAO = new StaticUserDAO();
    private final String url = "jdbc:mysql://foodwastequt.com.au:3306/foodwastequtcom_foodwaste?autoReconnect=true&useSSL=false";

    private final String username = "foodwastequtcom_jadezia";
    private final String password = "rY[]XPbS.B_M";
    private final StaticUserDAO userDAO = new StaticUserDAO(url, username, password);
    

    /**
     * Displays the main page.
     *
     * @param model The model that defines the attributes to be displayed.
     * @return The name of the view to display.
     */
    @GetMapping("/main")
    public String main(Model model) {
        User user = (User) model.getAttribute("user");
        model.addAttribute("user", user);
        model.addAttribute("users", userDAO.listUsers());

        if (user.getUserType().equals("Teacher")) {
            System.out.println("User object: " + user);
            System.out.println("User type: " + user.getUserType());
            return "Teacher-view";
        } else if (user.getUserType().equals("Student")) {
            System.out.println("User object: " + user);
            System.out.println("User type: " + user.getUserType());
            return "Student-view";
        } else {
            throw new RuntimeException("Unknown user role");
        }
    }

    /**
     * Logs the user out.
     * @return The name of the view to display.
     */
    @PostMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }


    @PostMapping("/create-challenge-view")
    public String createChallenge() {
        return "create-challenge-view";
    }

//    /**
//     * Get's the user information from Login Controller once user is logged in
//     * so user information can be rendered to the use homepage
//     */
//    @RequestMapping(value = "/main", method = RequestMethod.GET)
//    public String mainPage(Model model, @ModelAttribute("user") User user) {
//        String userName = user.getName();
//        model.addAttribute("userName", userName);
//        return "main-view";
//    }

}