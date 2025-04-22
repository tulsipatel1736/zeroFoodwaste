package example.application.controllers;

import example.data.StaticUserDAO;
import example.data.Challenge;
import example.data.ChallengeService;
import java.awt.event.ActionEvent;
import example.data.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import example.data.InvalidUserException;
import example.data.JBDConnection;
import java.sql.SQLException;

@Controller
public class CreateChallengeController {

    // Fields to connect to the database
    private final String url = "jdbc:mysql://foodwastequt.com.au:3306/foodwastequtcom_foodwaste?autoReconnect=true&useSSL=false";

    private final String username = "foodwastequtcom_jadezia";
    private final String password = "rY[]XPbS.B_M";
    private final StaticUserDAO userDAO = new StaticUserDAO(url, username, password);

    private JBDConnection connection;

    public CreateChallengeController() {
        this.connection = new JBDConnection(url, username, password);
    }

    @RequestMapping("/create-challenge")
    public String getCreateChallenge() {
        return "create-challenge";
    }

    @PostMapping("/create-challenge")
    public String createChallenge(@RequestParam("school") String schoolName, @RequestParam("prize") String prize, @RequestParam("duration") int challengeDuration, Model model) {
        Challenge challenge = new Challenge(schoolName, prize, challengeDuration);
        connection.insertChallenge(challenge);
        model.addAttribute("challenge", challenge);
        return "Create-challenge-view";
    }

}


//    @PostMapping(value = "/create-challenge")
//    public String createChallenge(@RequestParam("creator-ID") String creatorID, @ModelAttribute("user") User user, Model model) {
//        User creator = userDAO.getUser(creatorID);
//        if (creator == null) {
//            model.addAttribute("error", "Invalid email address");
//            return "create-challenge";
//        }
//        JBDConnection jbdConnection = new JBDConnection(url, username, password); // create JBDConnection object
//        Challenge challenge = null;
//        try {
//            challenge = challengeService.createChallenge(creator);
//            jbdConnection.insertChallenge(challenge); // insert challenge into database using JBDConnection object
//        } catch (InvalidUserException e) {
//            model.addAttribute("error", "Invalid user");
//            return "create-challenge";
//        }
//
//        model.addAttribute("challengeCode", challenge.getUniqueChallengeCode());
//        return "Teacher-view";
//    }








