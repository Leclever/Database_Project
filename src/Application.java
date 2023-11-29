import java.util.Objects;
import java.util.Scanner;
import java.sql.*;

import oracle.jdbc.OracleConnection;
import oracle.jdbc.driver.OracleDriver;

public class Application {
    int userID;
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
        Statement IDsearch = conn.createStatement();
        String searchID = "SELECT type FROM users WHERE username = " + userName;
        ResultSet cus_id = IDsearch.executeQuery(searchID);
        while (typeName.next()) {
            application.userID = cus_id.getInt("customer_ID");
        }
        boolean cart_flag = false;
        while (!quit){
            application.menu();
            String command_code = application.input_detection();
            switch (command_code){
                case "1":
                    break;
                case "2":
                    break;
                case "3":
                    while(!cart_flag){
                        cart.cart_menu();
                        String cart_command = application.input_detection();
                        switch (cart_command) {
                            case "1":
                                boolean flag1 = true;
                                while (flag1) {
                                    try {
                                        String query = "SELECT * FROM shopping_cart";
                                        Statement statement = conn.createStatement();
                                        ResultSet resultSet = statement.executeQuery(query);
                                        while (resultSet.next()) {
                                            int customerID = resultSet.getInt("customer_ID");
                                            int productNO = resultSet.getInt("product_NO");
                                            String productName = resultSet.getString("product_name");
                                            float unit_price = resultSet.getFloat("unit_price");
                                            int quantity = resultSet.getInt("quantity");
                                            float total_price = resultSet.getFloat("total_price");
                                            System.out.println(customerID+" "+productNO+" "+productName+" "+unit_price+" "+quantity+" "+total_price);
                                        }
                                    }catch (SQLException e){
                                        System.out.println("invalid input, try again");
                                        break;
                                    }
                                    flag1 = false;
                                }
                                break;
                            case "2":
                                break;
                            case "3":
                                boolean flag3 = true;
                                while (flag3) {
                                    try {
                                        Scanner input3 = new Scanner(System.in);
                                        System.out.println("please input the name of the product you want to delete:");
                                        String pName = input3.next();
                                        String deleteQuery = "DELETE FROM shopping_cart WHERE product_name = ?";
                                        PreparedStatement deleteStatement = conn.prepareStatement(deleteQuery);
                                        deleteStatement.setString(1, pName);
                                        deleteStatement.executeUpdate();
                                    } catch (SQLException e) {
                                        System.out.println("invalid input, try again");
                                        break;
                                    }
                                    flag3 =false;
                                }
                                break;
                            case "4":
                                boolean flag4 = true;
                                while (flag4) {
                                    try{
                                        Scanner temp = new Scanner(System.in);
                                        System.out.println("please enter the attribute, new value and the product_name that you want to change\n"+
                                                "please enter by the order otherwise the input may be invalid"+
                                                "only the quantity, total value");
                                        String property = temp.next();
                                        float required = temp.nextFloat();
                                        String productName = temp.next();
                                        String updateQuery = "UPDATE shopping_cart SET ? = ? WHERE product_name = ?";
                                        PreparedStatement updateStatement = conn.prepareStatement(updateQuery);
                                        updateStatement.setString(1, property);
                                        updateStatement.setFloat(2, required);
                                        updateStatement.setString(3, productName);
                                        updateStatement.executeUpdate();
                                    }catch (SQLException e){
                                        System.out.println("invalid input, try again");
                                        break;
                                    }
                                    flag4 = false;
                                }
                                break;
                            case "5":
                                boolean flag5 = true;
                                while (flag5) {
                                    try {
                                        int customer_ID,product_NO,quantity;
                                        float unit_price,total_value;
                                        String product_name;
                                        System.out.println("please enter the customer_ID, product_NO, product_name, unit_price, quantity, total-value and split with space\n"+
                                                "please follow the order or the input may be invalid");
                                        Scanner input = new Scanner(System.in);
                                        customer_ID =input.nextInt();
                                        product_NO = input.nextInt();
                                        product_name = input.next();
                                        unit_price = input.nextFloat();
                                        quantity = input.nextInt();
                                        total_value = input.nextFloat();
                                        String insertQuery = "INSERT INTO shopping_cart(customer_ID, product_NO, product_name, unit_price, quantity, total_value) VALUES (?, ?, ?, ?, ?, ?)";
                                        PreparedStatement insertStatement = conn.prepareStatement(insertQuery);
                                        insertStatement.setInt(1, customer_ID);
                                        insertStatement.setInt(2, product_NO);
                                        insertStatement.setString(3, product_name);
                                        insertStatement.setFloat(4, unit_price);
                                        insertStatement.setInt(5, quantity);
                                        insertStatement.setFloat(6, total_value);
                                        insertStatement.executeUpdate();
                                    }catch (SQLException e){
                                        System.out.println("invalid input, try again");
                                        break;
                                    }
                                    flag5 = false;
                                }
                                break;
                            case "back":
                                cart_flag = true;
                                break;
                            default:
                                System.out.println("no such function,please try again");
                                break;
                        }
                    }
                    break;
                case "4":
                    if (Objects.equals(userType, "customer")){
                        System.out.println("user is not allowed to access this module");
                        break;
                    }
                    break;
                case "5":
                    if (!Objects.equals(userType, "administrator")){
                        System.out.println("only administrator can access this module");
                        break;
                    }
                    break;
                case "quit":
                    quit = true;
                    break;
            }

        }
        System.out.println("Thank you for using the OSS system by Group 7");
        conn.close();
    }
        public String input_detection (){
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
                            break;
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
    public void menu(){
        System.out.println("[1] Account management");
        System.out.println("[2] Product listing and search");
        System.out.println("[3] Shopping cart and check out");
        System.out.println("[4] Inventory management (only for merchant and administrator accounts)");
        System.out.println("[5] Report and analytics (only for administrator accounts)");
        System.out.println("enter 'quit' to quit the whole system");
    }
}
class cart{
    public static void cart_menu(){
        System.out.println("please choose the function you need:");
        System.out.println("[1] check all the products in the cart");
        System.out.println("[2] check out");
        System.out.println("[3] remove a product in the cart");
        System.out.println("[4] change the number of item in the cart");
        System.out.println("[5] insert a product into the cart");
        System.out.println("enter 'back' to back to the last page");
    }

}

