package example.application.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChallengeLeaderBoardController {

    @GetMapping("/challenge-leaderboard")
    public String showChallengeLeaderboard(){
        return "challenge-leaderboard.html";
    }
}
