package com.example.wishlist.repositories;

import com.example.wishlist.model.Item;
import com.example.wishlist.model.Wishlist;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ItemRepository {

    WishlistRepository wishlistRepository;

    public ItemRepository(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }

    public List<Item> getItemList(int ID) {
        Wishlist wishlist = wishlistRepository.fetchWishlist(ID);
        try(Connection con = DBManager.getConnection()) {
            String SQL = "SELECT * FROM wishlist JOIN item WHERE wishlistID = itemID;";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int itemID = rs.getInt("itemID");
                String itemNamevar = rs.getString("itemName");
                Item item = new Item(itemID, itemNamevar);
                wishlist.addItem(item);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return wishlist.getItems();
    }


    public Item fetchItem(int ID) {
        try(Connection con = DBManager.getConnection()) {
            String SQL = "SELECT * FROM item WHERE itemID = ?;";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.setInt(1, ID);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int itemIDVar = rs.getInt("itemID");
                String itemNameVar = rs.getString("itemName");
                return new Item(itemIDVar, itemNameVar);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    public void addItem(Item item){
        try(Connection con = DBManager.getConnection()) {
            String SQL = "INSERT INTO item(itemID, itemName, wishlistID) VALUES(?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.setInt(1, item.getID());
            pstmt.setString(2, item.getName());
            pstmt.setInt(3, item.getWishlistID());
            pstmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void updateItem(int ID, String name, int wishlistID) {
        try(Connection con = DBManager.getConnection()) {
            String SQL = "UPDATE item SET itemName = ? WHERE wishlistID = ? AND itemID = ?;";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.setString(1, name);
            pstmt.setInt(2, wishlistID);
            pstmt.setInt(3, ID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void deleteItem(int ID) {
        try(Connection con = DBManager.getConnection()) {
            String SQL = "DELETE FROM item WHERE itemID = ?";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.setInt(1, ID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
