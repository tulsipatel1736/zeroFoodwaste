package example.application.controllers;
import org.springframework.stereotype.Controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class SettingsController {
    private static final String USER_FILE = "user_info.txt";
    @GetMapping("/Settings")
    public String showSettingsPage(){

        return "Settings.html";
    }

    // method to update user profile information
    public void updateUserProfile(String username, String email) throws IOException {
        //Retrieve the user profile from the database using username
        // I don't know how to set up a database so gonna do
        // Read the user information from the file
        File file = new File(USER_FILE);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        String content = stringBuilder.toString();
        reader.close();

        // Update the user information
        String newContent = content.replaceAll("username:.*", "username:" + username);
        newContent = newContent.replaceAll("email:.*", "email:" + email);

        // Write the updated information back to the file
        FileWriter writer = new FileWriter(file);
        writer.write(newContent);
        writer.close();
    }



    // method to manage notification preferences
    public void manageNotifications() {
        // logic to manage notifications
    }

    // method to handle "edit account" button click
    public void handleEditAccount() {
        // logic to handle "edit account" button click
    }

    // method to handle "delete account" button click
    public void handleDeleteAccount() {
        // logic to handle "delete account" button click
    }

    // method to handle "verify account" button click
    public void handleVerifyAccount() {
        // logic to handle "verify account" button click
    }
}
