package hk.edu.polyu.comp.comp2411.project.user;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
import java.math.BigInteger;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import hk.edu.polyu.comp.comp2411.project.user.User;
import hk.edu.polyu.comp.comp2411.project.user.UserAccountManager;
public class User {
    private String username;
    private String password;
    private String email;
    private String address;

    public User(String username, String password, String email, String address) {
        this.username = username;
        this.setPassword(password);
        this.setEmail(email);
        this.address = address;
    }

    // Getter and Setter methods
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes(StandardCharsets.UTF_8));
            BigInteger number = new BigInteger(1, hash);
            String hashedPassword = number.toString(16);
            while (hashedPassword.length() < 32) {
                hashedPassword = "0" + hashedPassword;
            }
            this.password = hashedPassword;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (isValidEmail(email)) {
            this.email = email;
        } else {
            System.out.println("Invalid email format");
        }
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // Method to display user account details
    public void displayUserAccount() {
        System.out.println("Username: " + getUsername());
        System.out.println("Email: " + getEmail());
        System.out.println("Address: " + getAddress());
    }

    // Method to validate email format
    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}


class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter your username:");
        String username = scanner.nextLine();

        System.out.println("Please enter your password:");
        String password = scanner.nextLine();

        System.out.println("Please enter your email:");
        String email = scanner.nextLine();

        System.out.println("Please enter your address:");
        String address = scanner.nextLine();

        // Create a new User object
        User user = new User(username, password, email, address);
        // Create a UserAccountManager object
        hk.edu.polyu.comp.comp2411.project.user.UserAccountManager manager = new hk.edu.polyu.comp.comp2411.project.user.UserAccountManager(user);
        // Create a PasswordReset object
        hk.edu.polyu.comp.comp2411.project.user.PasswordReset reset = new hk.edu.polyu.comp.comp2411.project.user.PasswordReset(user);

        // Display the user account details
        user.displayUserAccount();

        System.out.println("Do you want to update the user account information? Please enter 'yes' or 'no':");
        String update = scanner.nextLine();

        // Update the user account information if the user inputs "yes"
        if (update.equals("yes")) {
            System.out.println("Please enter your new username:");
            username = scanner.nextLine();

            System.out.println("Please enter your new password:");
            password = scanner.nextLine();

            System.out.println("Please enter your new email:");
            email = scanner.nextLine();

            System.out.println("Please enter your new address:");
            address = scanner.nextLine();

            manager.updateUserInformation(username, password, email, address);

            // Display the updated user account details
            user.displayUserAccount();
        }

        System.out.println("Do you want to reset the password? Please enter 'yes' or 'no':");
        String resetPassword = scanner.nextLine();

        // Reset the password if the user inputs "yes"
        if (resetPassword.equals("yes")) {
            System.out.println("Please enter the new password:");
            String newPassword = scanner.nextLine();

            reset.resetPassword(newPassword);

            // Display the user account details
            user.displayUserAccount();
        }

        System.out.println("Do you want to deactivate the account? Please enter 'yes' or 'no':");
        String deactivate = scanner.nextLine();

        // Deactivate the account if the user inputs "yes"
        if (deactivate.equals("yes")) {
            manager.deactivateAccount();

            // Display the user account details
            user.displayUserAccount();
        }

        scanner.close();
    }
}
