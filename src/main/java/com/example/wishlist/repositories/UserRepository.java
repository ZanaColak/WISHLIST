package com.example.wishlist.repositories;

import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.*;
import com.example.wishlist.model.*;

@Repository
public class UserRepository {

    private final List<User> users = new ArrayList<>();

    public List<User> getUsers() {
        try (Connection con = DBManager.getConnection()) {
            String SQL = "SELECT * FROM wishlistDatabase.user;";
            //Statement stmt = con.createStatement();
            //ResultSet rs = stmt.executeQuery(SQL);
            PreparedStatement pstmt = con.prepareStatement(SQL);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int ID = rs.getInt("userID");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String email = rs.getString("email");
                users.add(new User(ID, username, password, email));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }


    public User fetchUser(int ID) {
        try(Connection con = DBManager.getConnection()) {
            String SQL = "SELECT * FROM user WHERE userID = ?;";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.setInt(1, ID);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int userID = rs.getInt("userID");
                String usernamevar = rs.getString("username");
                String password = rs.getString("password");
                String email = rs.getString("email");
                return new User(userID, usernamevar, password, email);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    public void addUser(int ID, String name, String password, String email){
        try(Connection con = DBManager.getConnection()) {
            String SQL = "INSERT INTO user(userID, username, password, email) VALUES(?, ?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.setInt(1, ID);
            pstmt.setString(2, name);
            pstmt.setString(3, password);
            pstmt.setString(4, email);
            pstmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void editUser(int ID, String name, String password, String email) {
        try(Connection con = DBManager.getConnection()) {
            String SQL = "UPDATE user SET username = ?, password = ?, email = ? WHERE userID = ?;";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.setString(1, name);
            pstmt.setString(2, password);
            pstmt.setString(3, email);
            pstmt.setInt(4, ID);
            pstmt.executeUpdate();

            User userEdit = fetchUser(ID);
            userEdit.setUserName(name);
            userEdit.setEmail(email);
            userEdit.setPassword(password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void deleteUser(int userID) {
        try(Connection con = DBManager.getConnection()) {
            String SQL = "DELETE FROM user WHERE userID = ?";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.setInt(1, userID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
