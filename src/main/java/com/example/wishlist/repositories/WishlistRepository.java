package com.example.wishlist.repositories;

import com.example.wishlist.model.Wishlist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.stereotype.Repository;


@Repository
public class WishlistRepository {

    private final UserRepository userRepository;

    public WishlistRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Wishlist> getWishlists(int ID) {
        try(Connection con = DBManager.getConnection()) {
            String SQL = "SELECT * FROM user JOIN wishlist WHERE user.userID = wishlistID";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int wishlistID = rs.getInt("wishlistID");
                String wishlistNamevar = rs.getString("wishlistName");
                //int userID = rs.getInt("userID");
                Wishlist wishlist = new Wishlist(wishlistID, wishlistNamevar);
                userRepository.fetchUser(ID).addWishlist(wishlist);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userRepository.fetchUser(ID).getWishlists();
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


/*    public void addWishlist(int ID, String name, int userID){
        try(Connection con = DBManager.getConnection()) {
            String SQL = "INSERT INTO wishlist(wishlistID, wishlistName, userID) VALUES(?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.setInt(1, ID);
            pstmt.setString(2, name);
            pstmt.setInt(3, userID);
            pstmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }*/

    public void createWishlist(Wishlist wishlist){
        try(Connection con = DBManager.getConnection()) {
            String SQL = "INSERT INTO wishlist(wishlistID, wishlistName, userID) VALUES(?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.setInt(1, wishlist.getID());
            pstmt.setString(2, wishlist.getName());
            pstmt.setInt(3, wishlist.getUserID());
            pstmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateWishlist(Wishlist wishlist, String name) {
        try(Connection con = DBManager.getConnection()) {
            String SQL = "UPDATE wishlist SET wishlistName = ?, WHERE userID = ? AND wishlistID = ?;";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.setString(1, name);
            pstmt.setInt(2, wishlist.getUserID());
            pstmt.setInt(3, wishlist.getID());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void deleteWishlist(int ID) {
        try(Connection con = DBManager.getConnection()) {
            String SQL = "DELETE FROM wishlist WHERE wishlistID = ?";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.setInt(1, ID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
