package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
String url  = "jdbc:mysql://localhost:3306/demo_jdbc";
String username = "root";
String password = "bq2PQaoyZH6&L3hE";

try {
    Connection connection = DriverManager.getConnection(url, username, password);
    if(connection != null) {
        System.out.println("Connection successful");
        connection.close();
    }else{
        System.out.println("Connection failed");
    }
}catch(SQLException e) {
    System.out.println(e.getMessage());
}
    }
}