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
                        switch (cart_command){
                            case "1":
                                break;
                            case "2":
                                break;
                            case "3":
                                break;
                            case "4":
                                break;
                            case "5":
                                PreparedStatement statement = conn.prepareStatement("INSERT INTO shopping_cart(customer_ID,product_NO,product_name,unit_price,quantity) VALUES (?,?,?,?,?)");

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

    public void insertValue(OracleConnection conn) throws SQLException {

        String selectQuery = "CREATE TABLE combinedTable AS" +
                "SELECT o.order_NO, c.customer_ID, c.customer_name, o.date, p.product_NO, p.product_name, o.quantity, o.unit_price, c.address, c.payment, p.merchant_name, p.category, p.brand, p.inventory, p.sales" +
                "FROM `Order` o" +
                "JOIN Customer c ON o.customer_ID = c.customer_ID" +
                "JOIN Product p ON o.product_NO = p.product_NO" +
                "JOIN Shopping_cart sc ON c.customer_ID = sc.customer_ID AND p.product_NO = sc.product_NO" +
                "JOIN Merchant m ON p.merchant_name = m.merchant_name" +
                "JOIN Report r ON c.customer_ID = r.customer_ID AND p.product_NO = r.product_NO;";

        PreparedStatement selectStatement = conn.prepareStatement(selectQuery);
        ResultSet resultSet = selectStatement.executeQuery();
        String select_cart = "SELECT customer_ID, product_NO, product_name, unit_price, quantity \n"+
                "FROM combinedTable"+
                "WHERE customer_ID = " +userID;
        PreparedStatement select_for_cart = conn.prepareStatement(select_cart);
        ResultSet cart_record = select_for_cart.executeQuery();

        while (cart_record.next()) {
            // 从结果集中获取每个属性的值
            int customerId = resultSet.getInt("customer_ID");
            String productNo = resultSet.getString("product_NO");
            String productName = resultSet.getString("product_name");
            float unitPrice = resultSet.getFloat("unit_price");
            int quantity = resultSet.getInt("quantity");

            // 进行进一步的处理，比如输出到控制台或执行其他逻辑

            // 将获取到的值插入到数据库中的另一张表或同一张表的其他记录
            // 创建插入语句
            String insertQuery = "INSERT INTO shopping_cart(customer_ID, product_NO, product_name, unit_price, quantity) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement insertStatement = conn.prepareStatement(insertQuery);

            // 设置每个属性的参数
            insertStatement.setInt(1, customerId);
            insertStatement.setString(2, productNo);
            insertStatement.setString(3, productName);
            insertStatement.setFloat(4, unitPrice);
            insertStatement.setInt(5, quantity);

            // 执行插入操作
            insertStatement.executeUpdate();
        }

        String insertQuery = "INSERT INTO shopping_cart(customer_ID, product_NO, product_name, unit_price, quantity) VALUES (?, ?, ?, ?, ?)";

        resultSet.close();
        selectStatement.close();
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

