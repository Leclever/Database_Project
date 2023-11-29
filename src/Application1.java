package hk.edu.polyu.comp.comp2411.project.Application;

import java.util.Scanner;
import java.sql.*;

import oracle.jdbc.OracleConnection;
import oracle.jdbc.driver.OracleDriver;

public class Application1 {
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
        System.out.println("\nSuccessfully log into Oracle DB.\nProgram starts.\n");
        String in;
        String name = "";
        String type = "";
        Statement stmt = conn.createStatement();
        boolean matched;

        do {
            System.out.print("Enter \"l\" for Login or \"r\" for Register: ");
            in = scanner.nextLine();
            matched = in.equals("l") || in.equals("r");
            PreparedStatement upLoad = conn.prepareStatement("@?");
            if (in.equals("U")) {
                String f = scanner.nextLine();
                upLoad.setString(1, f);
                ResultSet rSet = upLoad.executeQuery();
                boolean exists = true;
                while (exists){
                    exists = rSet.next();
                    if (exists) System.out.printf(rSet.toString());
                }
            }
            // User register
            if (in.equals("r")) {
                PreparedStatement nameCheck = conn.prepareStatement("SELECT * FROM customer WHERE customer_name = ?");
                PreparedStatement userRegister = conn.prepareStatement("INSERT INTO customer VALUES (?, ?)");

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
                        boolean typeMatch;
                        userRegister.setString(1, name);
                        userRegister.setString(2, password1);

                        System.out.println("Register successfully!");

                    }
                } while (!matched);
            }
            // User login
            else if (in.equals("l")) {
                PreparedStatement userLogin = conn.prepareStatement("SELECT * FROM LOGS WHERE Customer = ? AND passward = ?");
                String pwd;
                do {
                    System.out.print("Enter your username: ");
                    name = scanner.nextLine();
                    System.out.print("Enter your password: ");
                    pwd = scanner.nextLine();
                    userLogin.setString(1, name);
                    userLogin.setString(2, pwd);
                    ResultSet rSet = userLogin.executeQuery();
                    if (rSet.next()) {
                        matched = true;
                        type = rSet.getString(3);
                        System.out.println("Login successfully!");
                    } else {
                        matched = false;
                        System.out.println("Username and password not matched. Please try again.\n");
                    }
                } while (!matched);
            } else System.out.println("\nWrong input! Please try again.\n");
        } while (!matched);
        // After user successful login or registration, the main application loop starts
        boolean quit = false;
        while (!quit) {
            System.out.println("4. Inventory Management (Administrators Only)");
            System.out.println("Please input the command:");
            in = scanner.nextLine();
            switch (in) {
                case "1":

                    break;
                case "2":
                    // Search product
                    System.out.println("Please select an option:");
                    System.out.println("1. Display all products");
                    System.out.println("2. Search for a product");
                    System.out.println("3. Filter products by price");

                    String option = scanner.nextLine();
                    try {
                        switch (option) {
                            case "1":
                                // Display all products
                                statement = conn.createStatement();
                                String allProductsQuery = "SELECT Product.*, Merchant.merchant_name FROM Product JOIN Merchant ON Product.merchant_ID = Merchant.merchant_ID";
                                ResultSet allProductsResultSet = statement.executeQuery(allProductsQuery);
                                while (allProductsResultSet.next()) {
                                    System.out.println("Product Name: " + allProductsResultSet.getString("product_name"));
                                    System.out.println("Price: " + allProductsResultSet.getDouble("unit_price"));
                                    System.out.println("Merchant Name: " + allProductsResultSet.getString("merchant_name"));
                                    System.out.println("---------------------");
                                }
                                allProductsResultSet.close();
                                statement.close();
                                break;

                            case "2":
                                // Search for a product
                                System.out.println("Enter the product name:");
                                String productName = scanner.nextLine();
                                statement = conn.createStatement();
                                String searchQuery = "SELECT Product.*, Merchant.merchant_name FROM Product JOIN Merchant ON Product.merchant_ID = Merchant.merchant_ID WHERE Product.product_name LIKE '%" + productName + "%'";
                                ResultSet searchResultSet = statement.executeQuery(searchQuery);
                                while (searchResultSet.next()) {
                                    System.out.println("Product Name: " + searchResultSet.getString("product_name"));
                                    System.out.println("Price: " + searchResultSet.getDouble("unit_price"));
                                    System.out.println("Merchant Name: " + searchResultSet.getString("merchant_name"));
                                    System.out.println("---------------------");
                                }
                                searchResultSet.close();
                                statement.close();
                                break;

                            case "3":
                                // Filter products by price
                                System.out.println("Enter the maximum price:");
                                double maxPrice = scanner.nextDouble();
                                scanner.nextLine(); // consume newline left-over
                                statement = conn.createStatement();
                                String filterQuery = "SELECT Product.*, Merchant.merchant_name FROM Product JOIN Merchant ON Product.merchant_ID = Merchant.merchant_ID WHERE Product.unit_price <= " + maxPrice;
                                ResultSet filterResultSet = statement.executeQuery(filterQuery);
                                while (filterResultSet.next()) {
                                    System.out.println("Product Name: " + filterResultSet.getString("product_name"));
                                    System.out.println("Price: " + filterResultSet.getDouble("unit_price"));
                                    System.out.println("Merchant Name: " + filterResultSet.getString("merchant_name"));
                                    System.out.println("---------------------");
                                }
                                filterResultSet.close();
                                statement.close();
                                break;

                            // Add more cases here for more options

                            default:
                                System.out.println("Invalid option. Please try again.");
                                break;
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case "3":

                    break;
                case "4":
                    System.out.println("Please enter the corresponding number:\n1. View the information of all product inventories\n2. View the inventory of a specific product\n3. Add products\n4. Delete products\n5. Update products' information");
                    String input = scanner.nextLine();
                    switch (input) {
                        case "1":
                            try {
                                String sql = "SELECT product_NO, product_name, category, Price, brand, stock_level FROM Product;";
                                PreparedStatement viewallinventory = conn.prepareStatement(sql);
                                ResultSet rSet = viewallinventory.executeQuery();
                                System.out.println("product NO\t\tproduct name\t\tcategory\t\tPrice\t\tbrand\t\tstock level");
                                while (rSet.next()) {
                                    int product_NO = rSet.getInt("product_NO");
                                    String product_name = rSet.getString("product_name");
                                    String category = rSet.getString("category");
                                    float Price = rSet.getFloat("Price");
                                    String  brand = rSet.getString(" brand");
                                    int stock_level = rSet.getInt("stock_level");
                                    System.out.println(product_NO + "\t\t" + product_name + "\t\t" + category + "\t\t"+ Price + "\t\t"+ brand + "\t\t"+ stock_level);
                                }
                            }catch (SQLException e) {
                                System.out.println(e);
                            }
                            break;
                        case "2":
                            System.out.println("Please enter the product NO of the product:");
                            input = scanner.nextLine();
                            try {
                                String sql = "SELECT product_NO, product_name, category, Price, brand, stock_level FROM Product WHERE product_NO = ?";
                                PreparedStatement viewspecificinventory = conn.prepareStatement(sql);
                                viewspecificinventory.setInt(1, Integer.parseInt(input));
                                ResultSet rSet = viewspecificinventory.executeQuery();
                                System.out.println("product NO\t\tproduct name\t\tcategory\t\tPrice\t\tbrand\t\tstock level");
                                while (rSet.next()) {
                                    int product_NO = rSet.getInt("product_NO");
                                    String product_name = rSet.getString("product_name");
                                    String category = rSet.getString("category");
                                    float Price = rSet.getFloat("Price");
                                    String  brand = rSet.getString(" brand");
                                    int stock_level = rSet.getInt("stock_level");
                                    System.out.println(product_NO + "\t\t" + product_name + "\t\t" + category + "\t\t"+ Price + "\t\t"+ brand + "\t\t"+ stock_level);
                                }
                            }catch (SQLException e) {
                                System.out.println(e);
                            }
                            break;
                        case "3":
                            System.out.println("Please enter the new product information in format: product_name, unit_price, category, merchantID, brand, stock_level, sales");
                            input = scanner.nextLine();
                            try {
                                String[] info = input.split(",");
                                String sql = "INSERT INTO Product VALUES (NULL, ?, ?, ?, ?, ?, ?, ?);";
                                PreparedStatement addproduct = conn.prepareStatement(sql);
                                addproduct.setString(1, info[0]);
                                addproduct.setFloat(2, Float.parseFloat(info[1]));
                                addproduct.setString(3, info[2]);
                                addproduct.setInt(4, Integer.parseInt(info[3]));
                                addproduct.setString(5, info[4]);
                                addproduct.setInt(6, Integer.parseInt(info[5]));
                                addproduct.setInt(7, Integer.parseInt(info[6]));
                                addproduct.executeUpdate();
                                System.out.println("Add successfully!");
                            }catch (SQLException e) {
                                System.out.println(e);
                            }
                            break;
                        case "4":
                            System.out.println("Please enter the product NO of the product:");
                            input = scanner.nextLine();
                            try {
                                String sql = "DELETE Product WHERE product_NO = ?";
                                PreparedStatement deleteproduct = conn.prepareStatement(sql);
                                deleteproduct.setInt(1,Integer.parseInt(input));
                                deleteproduct.executeUpdate();
                                System.out.println("Delete successfully!");
                            }catch (SQLException e) {
                                System.out.println(e);
                            }
                            break;
                        case "5":
                            System.out.println("Please enter the product NO of the product you want to update:");
                            String NO = scanner.nextLine();
                            System.out.println("Please enter the update information corresponding number:\n1. Product name \n2. Product unit price\n3. Product category \n4. Product stock level ");
                            input = scanner.nextLine();
                            switch (input) {
                                case "1":
                                    try {
                                        System.out.println("Please enter the new name:");
                                        input = scanner.nextLine();
                                        String sql = "UPDATE Product SET product_name = ? WHERE product_NO = ?;";
                                        PreparedStatement updateproduct = conn.prepareStatement(sql);
                                        updateproduct.setString(1,input);
                                        updateproduct.setInt(2,Integer.parseInt(NO));
                                        updateproduct.executeUpdate();
                                        System.out.println("Update successfully!");
                                    }catch (SQLException e) {
                                        System.out.println(e);
                                    }
                                    break;
                                case "2":
                                    try {
                                        System.out.println("Please enter the new price:");
                                        input = scanner.nextLine();
                                        String sql = "UPDATE Product SET unit_price = ? WHERE product_NO = ?;";
                                        PreparedStatement updateproduct = conn.prepareStatement(sql);
                                        updateproduct.setFloat(1,Float.parseFloat(input));
                                        updateproduct.setInt(2,Integer.parseInt(NO));
                                        updateproduct.executeUpdate();
                                        System.out.println("Update successfully!");
                                    }catch (SQLException e) {
                                        System.out.println(e);
                                    }
                                    break;
                                case "3":
                                    try {
                                        System.out.println("Please enter the new category:");
                                        input = scanner.nextLine();
                                        String sql = "UPDATE Product SET category = ? WHERE product_NO = ?;";
                                        PreparedStatement updateproduct = conn.prepareStatement(sql);
                                        updateproduct.setString(1,input);
                                        updateproduct.setInt(2,Integer.parseInt(NO));
                                        updateproduct.executeUpdate();
                                        System.out.println("Update successfully!");
                                    }catch (SQLException e) {
                                        System.out.println(e);
                                    }
                                    break;
                                case "4":
                                    try {
                                        System.out.println("Please enter the new stock level:");
                                        input = scanner.nextLine();
                                        String sql = "UPDATE Product SET stock_level = ? WHERE product_NO = ?;";
                                        PreparedStatement updateproduct = conn.prepareStatement(sql);
                                        updateproduct.setInt(1,Integer.parseInt(input));
                                        updateproduct.setInt(2,Integer.parseInt(NO));
                                        updateproduct.executeUpdate();
                                        System.out.println("Update successfully!");
                                    }catch (SQLException e) {
                                        System.out.println(e);
                                    }
                                    break;
                                default:
                                    System.out.println("Invalid number, please try again.");
                            }
                            break;
                        default:
                            System.out.println("Invalid number, please try again.");
                            // How to go back?
                    }
                    break;
                case "q":
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid command, please try again.");
            }
        }
        System.out.println("Thank you for using our system.");}}
