package com.user;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {


    public static boolean saveUser(User user) {
        boolean result = false;
        try {
            Connection conn = DBConnection.getConnection();
            String query = "INSERT INTO user_details(first_name, last_name, gender, skills) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getGender());
            ps.setString(4, user.getSkills());

            int i = ps.executeUpdate();
            if (i > 0) result = true;

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // READ - Get all users
    public static List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();
            String query = "SELECT * FROM user_details";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setGender(rs.getString("gender"));
                user.setSkills(rs.getString("skills"));
                users.add(user);
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public static User getUserById(int id) {
        User user = null;
        try {
            Connection conn = DBConnection.getConnection();
            String query = "SELECT * FROM user_details WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setGender(rs.getString("gender"));
                user.setSkills(rs.getString("skills"));
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    // UPDATE - Update existing user
    public static boolean updateUser(User user) {
        boolean result = false;
        try {
            Connection conn = DBConnection.getConnection();
            String query = "UPDATE user_details SET first_name = ?, last_name = ?, gender = ?, skills = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getGender());
            ps.setString(4, user.getSkills());
            ps.setInt(5, user.getId());

            int i = ps.executeUpdate();
            if (i > 0) result = true;

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    
    public static boolean deleteUser(int id) {
        boolean result = false;
        try {
            Connection conn = DBConnection.getConnection();
            String query = "DELETE FROM user_details WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);

            int i = ps.executeUpdate();
            if (i > 0) result = true;

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
