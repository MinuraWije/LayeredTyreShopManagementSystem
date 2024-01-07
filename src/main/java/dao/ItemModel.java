package dao;

import db.DbConnection;
import dto.ItemDTO;
import dto.tm.CartTM;
import dto.tm.ItemTM;
import util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemModel {
    public static boolean delete(String itemId) throws SQLException {
        String sql = "DELETE FROM item WHERE itemId = ?";
        return CrudUtil.execute(sql,itemId);
    }

    public static boolean save(ItemDTO itemDTO) throws SQLException {
        String sql = "INSERT INTO item(itemId,brand,model,unitPrice,qtyOnHand) VALUES(?,?,?,?,?)";
        return CrudUtil.execute(sql, itemDTO.getItemId(),itemDTO.getBrand(),itemDTO.getModel(),itemDTO.getUnitPrice(),itemDTO.getQtyOnHand());
    }

    public static boolean update(ItemDTO itemDTO) throws SQLException {
        String sql = "UPDATE item set brand=?,model=?,unitPrice=?,qtyOnHand=? WHERE itemId = ?";
        return CrudUtil.execute(sql,itemDTO.getBrand(),itemDTO.getModel(),itemDTO.getUnitPrice(),itemDTO.getQtyOnHand(),itemDTO.getItemId());

    }

    public static ItemDTO search(String itemId) throws SQLException {
        String sql = "SELECT * FROM item where itemId = ?";

        ResultSet resultSet = CrudUtil.execute(sql, itemId);

        if (resultSet.next()){
            ItemDTO itemDTO= new ItemDTO();
            itemDTO.setItemId(resultSet.getString(1));
            itemDTO.setBrand(resultSet.getString(2));
            itemDTO.setModel(resultSet.getString(3));
            itemDTO.setUnitPrice(Double.valueOf(resultSet.getString(4)));
            itemDTO.setQtyOnHand(resultSet.getInt(5));

            return itemDTO;
        }
        return null;
    }

    public static List<ItemTM> getAll() throws SQLException {
        String sql = "SELECT * FROM item";
        ResultSet resultSet = CrudUtil.execute(sql);
        List<ItemTM> data = new ArrayList<>();
        while (resultSet.next()) {
            ItemTM itemTM = new ItemTM(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4),
                    resultSet.getInt(5)

            );
            data.add(itemTM);
        }
        return data;
    }
    public boolean updateItem(List<CartTM> cartTmList) throws SQLException {
        for(CartTM tm : cartTmList) {
            System.out.println("Item: " + tm);
            if(!updateQty(tm.getItemId(), tm.getQty())) {
                return false;
            }
        }
        return true;
    }

    public boolean updateQty(String code, int qty) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE item SET qtyOnHand = qtyOnHand - ? WHERE ItemId = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setInt(1, qty);
        pstm.setString(2, code);

        return pstm.executeUpdate() > 0; //false
    }
}
