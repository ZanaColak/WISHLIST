package com.example.wishlist.repositories;

import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.*;
import com.example.wishlist.model.*;

@Repository
public class DBRepository {

    private final List<User> users = new ArrayList<>();

    public List<User> getSuperheroes() {
        if (!users.isEmpty()){
            return users;
        }
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

    public User fetchUser(String username) {
        try(Connection con = DBManager.getConnection()) {
            String SQL = "SELECT * FROM user WHERE UserName = ?;";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int userID = rs.getInt("UserID");
                String usernametmp = rs.getString("Username");
                String password = rs.getString("Password");
                String email = rs.getString("Email");
                return new User(userID, usernametmp, password, email);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<Wishlist> getWishlists(String username) {
        try(Connection con = DBManager.getConnection()) {
            String SQL = "SELECT * FROM user JOIN wishlist WHERE UserID = WishlistID AND UserName = ?;";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int userID = rs.getInt("UserID");
                String usernametmp = rs.getString("Username");
                String password = rs.getString("Password");
                String email = rs.getString("Email");
                User user = new User(userID, usernametmp, password, email);
                return user.getWishlists();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void addWishlist(User user, Wishlist wishlist) {
        user.addWishlist(wishlist);
    }

    //Skal ændres, har ikke fikset hvordan items list og wish lists skal tilknyttes user.
    public List<Item> getItemlist(String wishlistName) {
        ArrayList<Item> items = new ArrayList<>();
        try(Connection con = DBManager.getConnection()) {
            String SQL = "SELECT * FROM wishlist JOIN item WHERE WishlistID = ItemID AND WishlistName = ?;";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.setString(1, wishlistName);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int itemID = rs.getInt("ItemID");
                String itemName = rs.getString("ItemName");
                Item item = new Item(itemID, itemName);
                items.add(item);
                return items;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}
