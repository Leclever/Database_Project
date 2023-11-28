package hk.edu.polyu.comp.comp2411.project.Application;

import java.util.Scanner;
import java.sql.*;

import oracle.jdbc.OracleConnection;
import oracle.jdbc.driver.OracleDriver;

public class Application {
    public static void main(String[] args) throws Exception {
        // Access to server
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your username: ");
        String comp_name = scanner.nextLine();
        System.out.print("Enter your password: ");
        String comp_pwd = scanner.nextLine();

        DriverManager.registerDriver(new OracleDriver());
        OracleConnection conn = (OracleConnection) DriverManager.getConnection(
                "jdbc:oracle:thin:@studora.comp.polyu.edu.hk:1521:dbms", comp_name, comp_pwd);

        // User login or register
        System.out.println("\nSuccessfully logged into Oracle DB.\nProgram starts.\n");
        String in;
        String name = "";
        String type = "";
        boolean matched;

        do {
            System.out.print("Enter \"l\" for Login or \"r\" for Register: ");
            in = scanner.nextLine();
            matched = in.equalsIgnoreCase("l") || in.equalsIgnoreCase("r");
            
            // User register
            if (in.equalsIgnoreCase("r")) {
                PreparedStatement nameCheck = conn.prepareStatement("SELECT * FROM users WHERE username = ?");
                PreparedStatement userRegister = conn.prepareStatement("INSERT INTO users (username, password) VALUES (?, ?)");

                boolean exists;
                // Username input, if name already exists then try again
                do {
                    System.out.print("Enter your username: ");
                    name = scanner.nextLine();
                    nameCheck.setString(1, name);
                    ResultSet rSet = nameCheck.executeQuery();
                    exists = rSet.next();
                    if (exists) System.out.printf("Username %s already exists. Please try again.\n\n", name);
                } while (exists);
                // Password input, if not match then try again
                do {
                    System.out.print("Enter your password(<=32 chars): ");
                    String password1 = scanner.nextLine();
                    System.out.print("Confirm your password: ");
                    String password2 = scanner.nextLine();
                    matched = password1.equals(password2);
                    if (!matched) System.out.println("Passwords don't match. Please try again.\n\n");
                    else {
                        userRegister.setString(1, name);
                        userRegister.setString(2, password1);

                        userRegister.executeUpdate();
                        System.out.println("Register successfully!");
                    }
                } while (!matched);
            }
            // User login
            else if (in.equalsIgnoreCase("l")) {
                PreparedStatement userLogin = conn.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
                String pwd;
                do {
                    System.out.print("Enter your username: ");
                    name = scanner.nextLine();
                    System.out.print("Enter your password: ");
                    pwd = scanner.nextLine();
                    userLogin.setString(1, name);
                    userLogin.setString(2, pwd);
                    ResultSet rSet = userLogin.executeQuery();
                    matched = rSet.next();
                    if (matched) System.out.println("Login successfully!");
                    else System.out.println("Username and password not matched. Please try again.\n");
                } while (!matched);
            } else System.out.println("\nWrong input! Please try again.\n");
        } while (!matched);
        // After user successful login or registration, the main application loop starts
        boolean quit = false;
        while (!quit) {
            System.out.println("Please input the command:");
            in = scanner.nextLine();
            switch (in) {
                case "1":
                    System.out.println("Command 1 executed");
                    // Add code for command 1
                    break;
                case "2":
                    System.out.println("Command 2 executed");
                    // Add code for command 2
                    break;
                case "3":
                    System.out.println("Command 3 executed");
                    // Add code for command 3
                    break;
                case "4":
                    System.out.println("Command 4 executed");
                    // Add code for command 4
                    break;
                case "q":
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid command, please try again.");
            }
        }
        System.out.println("Thank you for using our system.");
        // Close connection
        conn.close();
    }
}
