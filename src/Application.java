import java.util.Objects;
import java.util.Scanner;
import java.sql.*;

import oracle.jdbc.OracleConnection;
import oracle.jdbc.driver.OracleDriver;

public class Application {
    public static void main(String[] args) throws Exception {
        Application application = new Application();
        //initiation of the system
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
        String command;
        String userName = "";
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
                    if (matched) {
                        System.out.println("Login successfully!");
                        userName = name;
                    } else System.out.println("Username and password not matched. Please try again.\n");
                } while (!matched);
            } else System.out.println("\nWrong input! Please try again.\n");
        } while (!matched);
        // After user successful login or registration, the main application loop starts
        boolean quit = false;
        Statement typeSearching = conn.createStatement();
        String search_type = "SELECT type FROM users WHERE username = " + userName;
        ResultSet typeName = typeSearching.executeQuery(search_type);
        String userType = "";
        while (typeName.next()) {
            userType = typeName.getString("type");
        }
        switch (userType) {
            case "customer":
                while (!quit) {
                    application.menu(userType);
                    command = application.input_detection(application);
                    switch (command) {
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
                        case "quit":
                            quit = true;
                            break;
                        default:
                            System.out.println("no such function, try again please");
                            break;
                    }
                }
                System.out.println("Thank you for using our system.");
                // Close connection
                conn.close();
                break;
            case "merchant":
                while (!quit) {
                    application.menu(userType);
                    command = application.input_detection(application);
                    switch (command) {
                        case "1":

                            // Add code for command 1
                            break;
                        case "2":

                            // Add code for command 2
                            break;
                        case "3":

                            // Add code for command 3
                            break;
                        case "4":

                            // Add code for command 4
                            break;
                        case "quit":
                            quit = true;
                            break;
                        default:
                            System.out.println("no such function, try again please");
                            break;
                    }
                }
                System.out.println("Thank you for using our system.");
                // Close connection
                conn.close();
                break;
            case "administrator":
                while (!quit) {
                    application.menu(userType);
                    command = application.input_detection(application);
                    switch (command) {
                        case "1":

                            // Add code for command 1
                            break;
                        case "2":

                            // Add code for command 2
                            break;
                        case "3":

                            // Add code for command 3
                            break;
                        case "4":

                            // Add code for command 4
                            break;
                        case "5":
                            break;
                        case "6":
                            break;
                        case "quit":
                            quit = true;
                            break;
                        default:
                            System.out.println("no such function, try again please");
                            break;
                    }
                }
                System.out.println("Thank you for using our system.");
                // Close connection
                conn.close();
                break;
        }
    }
        public String input_detection (Application main_program){
            boolean flag = true;
            String input = null;
            while (flag) {
                try {
                    System.out.println("Please input the command:");
                    Scanner sc = new Scanner(System.in);
                    input = sc.nextLine();
                    switch (input) {
                        case "":
                            System.out.println("the input cannot be empty");
                            throw new IllegalArgumentException();
                        case "quit":
                            flag =false;
                            return null;
                        default:
                            if (input.charAt(0)<'0' || input.charAt(0)>'9') {
                                throw new IllegalArgumentException();
                            }
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("please try again");
                }
            }
            return input;
        }
    public void menu(String userType){
        switch (userType){
            case "customer":
                System.out.println("[1] change user information");
                System.out.println("[2] check your order record");
                System.out.println("[3] search product by name");
                System.out.println("[4] search product by brand");
                System.out.println("[5] search merchant by name");
                break;
            case "merchant":
                System.out.println("[1] check the inventory level");
                System.out.println("[2] check the order");
                break;
            case "administrator":
                break;
        }
    }
}

