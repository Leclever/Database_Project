import java.util.Scanner;
import java.sql.*;

import oracle.jdbc.OracleConnection;
import oracle.jdbc.driver.OracleDriver;

public class Application1 {
    String userName;
    int userId;

    public static void main(String[] args) throws Exception {
        Application1 application = new Application1();
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
                while (exists) {
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
                        application.userName = name;
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
                                Statement statement = conn.createStatement();
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
                    boolean cart_flag = false;
                    while (!cart_flag) {
                        System.out.println("please choose the function you need:");
                        System.out.println("[1] check all the products in the cart");
                        System.out.println("[2] check out");
                        System.out.println("[3] remove a product in the cart");
                        System.out.println("[4] change the number of item in the cart");
                        System.out.println("[5] insert a product into the cart");
                        System.out.println("enter 'back' to back to the last page");
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
                                            System.out.println(customerID + " " + productNO + " " + productName + " " + unit_price + " " + quantity + " " + total_price);
                                        }
                                    } catch (SQLException e) {
                                        System.out.println("invalid input, try again");
                                        break;
                                    }
                                    flag1 = false;
                                }
                                break;
                            case "2":
                                String sum = "SELECT SUM(total_price) AS overall_price FROM shopping_cart";
                                Statement statement = conn.createStatement();
                                ResultSet resultSet = statement.executeQuery(sum);
                                float overallPrice;
                                if (resultSet.next()) {
                                    overallPrice = resultSet.getFloat("overall_price");
                                    System.out.println("Overall Price: " + overallPrice);
                                }
                                String ap = "SELECT address, payment FROM Customer WHERE customer_name = ?";
                                PreparedStatement state = conn.prepareStatement(ap);
                                state.setString(1, application.userName);
                                ResultSet result = statement.executeQuery(ap);
                                String address = null;
                                String payment = null;
                                if (resultSet.next()) {
                                    address = resultSet.getString("address");
                                    payment = resultSet.getString("payment");
                                }
                                String all = "SELECT * FROM shopping_cart";
                                Statement st = conn.createStatement();
                                ResultSet rs = statement.executeQuery(all);
                                int customerID, productNO, quantity;
                                float unitPrice, totalPrice;
                                String productName;
                                while (rs.next()) {
                                    customerID = rs.getInt("customer_ID");
                                    productNO = rs.getInt("product_NO");
                                    productName = rs.getString("product_name");
                                    unitPrice = rs.getFloat("unit_price");
                                    quantity = rs.getInt("quantity");
                                    totalPrice = rs.getFloat("total_price");

                                    System.out.println(customerID + " " + productNO + " " + productName + " " + unitPrice +
                                            " " + quantity + " " + totalPrice);
                                }
                                System.out.println("the customer name is: " + application.userName);
                                System.out.println("the payment is: " + payment);
                                System.out.println("the address is: " + address);
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
                                    flag3 = false;
                                }
                                break;
                            case "4":
                                boolean flag4 = true;
                                while (flag4) {
                                    try{
                                        Scanner temp = new Scanner(System.in);
                                        System.out.println("please enter the new value and the product_name that you want to change\n"+
                                                "please enter by the order otherwise the input may be invalid"+
                                                "only the quantity, total value");
                                        float required = temp.nextFloat();
                                        String pName = temp.next();
                                        float totp;
                                        String updateQuery = "UPDATE shopping_cart SET quantity = ?,total_price = ? WHERE product_name = ?";
                                        String unitp = "SELECT unit_price FROM shopping_cart WHERE product_name = ?";
                                        PreparedStatement st1 = conn.prepareStatement(unitp);
                                        st1.setString(1, pName);
                                        ResultSet rs1 = st1.executeQuery();
                                        float up = 0;
                                        while (rs1.next()) {
                                            up = rs1.getFloat("unit_price");
                                        }
                                        totp = up*required;
                                        PreparedStatement updateStatement = conn.prepareStatement(updateQuery);
                                        updateStatement.setFloat(1, required);
                                        updateStatement.setFloat(2, totp);
                                        updateStatement.setString(3,pName);
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
                                        int customer_ID,product_NO,quant;
                                        float unit_price,total_value;
                                        String product_name;
                                        System.out.println("please enter the customer_ID, product_NO, product_name, unit_price, quantity and split with space\n"+
                                                "please follow the order or the input may be invalid");
                                        Scanner input = new Scanner(System.in);
                                        customer_ID =input.nextInt();
                                        product_NO = input.nextInt();
                                        product_name = input.next();
                                        unit_price = input.nextFloat();
                                        quant = input.nextInt();
                                        total_value = unit_price*quant;
                                        String insertQuery = "INSERT INTO shopping_cart(customer_ID, product_NO, product_name, unit_price, quantity, total_value) VALUES (?, ?, ?, ?, ?, ?)";
                                        PreparedStatement insertStatement = conn.prepareStatement(insertQuery);
                                        insertStatement.setInt(1, customer_ID);
                                        insertStatement.setInt(2, product_NO);
                                        insertStatement.setString(3, product_name);
                                        insertStatement.setFloat(4, unit_price);
                                        insertStatement.setInt(5, quant);
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
                                                String brand = rSet.getString(" brand");
                                                int stock_level = rSet.getInt("stock_level");
                                                System.out.println(product_NO + "\t\t" + product_name + "\t\t" + category + "\t\t" + Price + "\t\t" + brand + "\t\t" + stock_level);
                                            }
                                        } catch (SQLException e) {
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
                                                String brand = rSet.getString(" brand");
                                                int stock_level = rSet.getInt("stock_level");
                                                System.out.println(product_NO + "\t\t" + product_name + "\t\t" + category + "\t\t" + Price + "\t\t" + brand + "\t\t" + stock_level);
                                            }
                                        } catch (SQLException e) {
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
                                        } catch (SQLException e) {
                                            System.out.println(e);
                                        }
                                        break;
                                    case "4":
                                        System.out.println("Please enter the product NO of the product:");
                                        input = scanner.nextLine();
                                        try {
                                            String sql = "DELETE Product WHERE product_NO = ?";
                                            PreparedStatement deleteproduct = conn.prepareStatement(sql);
                                            deleteproduct.setInt(1, Integer.parseInt(input));
                                            deleteproduct.executeUpdate();
                                            System.out.println("Delete successfully!");
                                        } catch (SQLException e) {
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
                                                    updateproduct.setString(1, input);
                                                    updateproduct.setInt(2, Integer.parseInt(NO));
                                                    updateproduct.executeUpdate();
                                                    System.out.println("Update successfully!");
                                                } catch (SQLException e) {
                                                    System.out.println(e);
                                                }
                                                break;
                                            case "2":
                                                try {
                                                    System.out.println("Please enter the new price:");
                                                    input = scanner.nextLine();
                                                    String sql = "UPDATE Product SET unit_price = ? WHERE product_NO = ?;";
                                                    PreparedStatement updateproduct = conn.prepareStatement(sql);
                                                    updateproduct.setFloat(1, Float.parseFloat(input));
                                                    updateproduct.setInt(2, Integer.parseInt(NO));
                                                    updateproduct.executeUpdate();
                                                    System.out.println("Update successfully!");
                                                } catch (SQLException e) {
                                                    System.out.println(e);
                                                }
                                                break;
                                            case "3":
                                                try {
                                                    System.out.println("Please enter the new category:");
                                                    input = scanner.nextLine();
                                                    String sql = "UPDATE Product SET category = ? WHERE product_NO = ?;";
                                                    PreparedStatement updateproduct = conn.prepareStatement(sql);
                                                    updateproduct.setString(1, input);
                                                    updateproduct.setInt(2, Integer.parseInt(NO));
                                                    updateproduct.executeUpdate();
                                                    System.out.println("Update successfully!");
                                                } catch (SQLException e) {
                                                    System.out.println(e);
                                                }
                                                break;
                                            case "4":
                                                try {
                                                    System.out.println("Please enter the new stock level:");
                                                    input = scanner.nextLine();
                                                    String sql = "UPDATE Product SET stock_level = ? WHERE product_NO = ?;";
                                                    PreparedStatement updateproduct = conn.prepareStatement(sql);
                                                    updateproduct.setInt(1, Integer.parseInt(input));
                                                    updateproduct.setInt(2, Integer.parseInt(NO));
                                                    updateproduct.executeUpdate();
                                                    System.out.println("Update successfully!");
                                                } catch (SQLException e) {
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
                    System.out.println("Thank you for using our system.");
            }
        }
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
}