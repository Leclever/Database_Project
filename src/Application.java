import java.io.*;
import java.io.Console;
import java.sql.*;
import oracle.jdbc.driver.*;
import oracle.sql.*;

public class Application {
    public static void main(String[] args) throws SQLException {
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        String username = ""+22074854d+"@dbms";
        String password = "bjhigyjf";
        OracleConnection conn =
                (OracleConnection)DriverManager.getConnection(
                        "jdbc:oracle:thin:@studora.comp.polyu.edu.hk:1521:dbms", username,password);
        boolean quit = false;
        System.out.println("Welcome to the OSS system!");
        while (!quit){

        }
        while (!quit){
            System.out.println("please input the command:");

        }


    }

    public void print_menu(User user){
        String level = user.getType();
        switch (level){
            case "customer":
                System.out.println("please input the code of the code you need:");
                System.out.println("[1] check your shopping record");
                System.out.println("[2] search product");
                System.out.println("[3] search merchant");
                System.out.println("[4] search brand");
                break;
            case "merchant":
                System.out.println("[1] update the ");
                break;
            case "administrator":
                break;
        }
    }

    public String input_detection(String input){
        return input;
    }

}
