package com.user;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    public static Connection getConnection() {
        Connection conn = null;
        try {
            String url = "jdbc:mysql://localhost:3306/user_management?useSSL=false&requireSSL=false&allowPublicKeyRetrieval=true";
            String username = "root"; // Change if your MySQL username is different
            String password = "";     // Change this if your MySQL has a password

            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("✅ User Database Connected Successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("❌ User Database connection failed!");
        }
        return conn;
    }
}
