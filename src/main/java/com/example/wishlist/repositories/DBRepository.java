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


    public List<Wishlist> getWishlists() {
        try(Connection con = DBManager.getConnection()) {
            String SQL = "SELECT * FROM user JOIN wishlist WHERE user.userID = wishlistID;";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                int wishlistID = rs.getInt("wishlistID");
                String wishlistNamevar = rs.getString("wishlistName");
                int userID = rs.getInt("userID");
                Wishlist wishlist = new Wishlist(wishlistID, wishlistNamevar);
                fetchUser(userID).addWishlist(wishlist);
                return fetchUser(userID).getWishlists();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
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


    public List<Item> getItemlist(String itemName, int ID) {
        try(Connection con = DBManager.getConnection()) {
            Wishlist wishlist = fetchWishlist(ID);
            String SQL = "SELECT * FROM wishlist JOIN item WHERE wishlistID = itemID AND itemName = ?;";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.setString(1, itemName);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int itemID = rs.getInt("ItemID");
                String itemNamevar = rs.getString("ItemName");
                Item item = new Item(itemID, itemNamevar);
                wishlist.addItem(item);
                return wishlist.getItems();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}
