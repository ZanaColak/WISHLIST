package com.example.wishlist.repositories;

import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.*;
import com.example.wishlist.model.*;

@Repository
public class DBRepository {

    private final List<User> users = new ArrayList<>();

    public List<User> getUsers() {
        try (Connection con = DBManager.getConnection()) {
            String SQL = "SELECT * FROM wishlistDatabase.user;";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                int ID = rs.getInt("UserID");
                String username = rs.getString("Username");
                String password = rs.getString("Password");
                String email = rs.getString("Email");
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
                int userID = rs.getInt("UserID");
                String usernamevar = rs.getString("Username");
                String password = rs.getString("Password");
                String email = rs.getString("Email");
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
            users.add(new User(ID, name, password, email));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void editUser(int ID, String name, String password, String email) {

    }

    public void deleteUser(int ID, String username) {

    }

    public List<Wishlist> getWishlists(int ID) {
        try(Connection con = DBManager.getConnection()) {
            String SQL = "SELECT * FROM user JOIN wishlist WHERE user.userID = wishlistID";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                int wishlistID = rs.getInt("wishlistID");
                String wishlistNamevar = rs.getString("wishlistName");
                //int userID = rs.getInt("userID");
                Wishlist wishlist = new Wishlist(wishlistID, wishlistNamevar);
                fetchUser(ID).addWishlist(wishlist);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return fetchUser(ID).getWishlists();
    }

    public Wishlist fetchWishlist(int wishlistID) {
        try(Connection con = DBManager.getConnection()) {
            String SQL = "SELECT * FROM wishlist WHERE wishlistID = ?;";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.setInt(1, wishlistID);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int wishlistIDvar = rs.getInt("wishlistID");
                String wishlistname = rs.getString("wishlistName");
                return new Wishlist(wishlistIDvar, wishlistname);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    public List<Item> getItemlist(int ID) {
        Wishlist wishlist = fetchWishlist(ID);
        try(Connection con = DBManager.getConnection()) {
            String SQL = "SELECT * FROM wishlist JOIN item WHERE wishlistID = itemID;";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                int itemID = rs.getInt("ItemID");
                String itemNamevar = rs.getString("ItemName");
                Item item = new Item(itemID, itemNamevar);
                wishlist.addItem(item);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return wishlist.getItems();
    }

}
