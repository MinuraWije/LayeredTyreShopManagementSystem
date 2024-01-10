package dao.custom.impl;

import dao.SQLUtil;
import dao.custom.ItemDAO;
import db.DbConnection;
import view.tdm.CartTM;
import entity.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public boolean delete(String itemId) throws SQLException {
        /*String sql = "DELETE FROM item WHERE itemId = ?";
        return SQLUtil.execute(sql,itemId);*/
        return SQLUtil.execute("DELETE FROM item WHERE itemId = ?",itemId);
    }
    @Override
    public boolean save(Item entity) throws SQLException {
        /*String sql = "INSERT INTO item(itemId,brand,model,unitPrice,qtyOnHand) VALUES(?,?,?,?,?)";
        return SQLUtil.execute(sql, entity.getItemId(),entity.getBrand(),entity.getModel(),entity.getUnitPrice(),entity.getQtyOnHand());*/
        return SQLUtil.execute("INSERT INTO item(itemId,brand,model,unitPrice,qtyOnHand) VALUES(?,?,?,?,?)",
                entity.getItemId(),entity.getBrand(),entity.getModel(),entity.getUnitPrice(),entity.getQtyOnHand());
    }
    @Override
    public boolean update(Item entity) throws SQLException {
        /*String sql = "UPDATE item set brand=?,model=?,unitPrice=?,qtyOnHand=? WHERE itemId = ?";
        return SQLUtil.execute(sql,entity.getBrand(),entity.getModel(),entity.getUnitPrice(),entity.getQtyOnHand(),entity.getItemId());*/
        return SQLUtil.execute("UPDATE item set brand=?,model=?,unitPrice=?,qtyOnHand=? WHERE itemId = ?",
                entity.getBrand(),entity.getModel(),entity.getUnitPrice(),entity.getQtyOnHand(),entity.getItemId());
    }
    @Override
    public Item search(String itemId) throws SQLException {
        /*String sql = "SELECT * FROM item where itemId = ?";

        ResultSet resultSet = SQLUtil.execute(sql, itemId);

        if (resultSet.next()){
            ItemDTO itemDTO= new ItemDTO();
            itemDTO.setItemId(resultSet.getString(1));
            itemDTO.setBrand(resultSet.getString(2));
            itemDTO.setModel(resultSet.getString(3));
            itemDTO.setUnitPrice(Double.valueOf(resultSet.getString(4)));
            itemDTO.setQtyOnHand(resultSet.getInt(5));

            return itemDTO;
        }
        return null;*/
        ResultSet rst = SQLUtil.execute("SELECT * FROM item where itemId = ?",itemId);
        rst.next();
        return new Item(itemId+"", rst.getString("brand"), rst.getString("model"),
                rst.getDouble("unitPrice"), rst.getInt("qtyOnHand"));
    }
    @Override
    public ArrayList<Item> getAll() throws SQLException {
        /*String sql = "SELECT * FROM item";
        ResultSet resultSet = SQLUtil.execute(sql);
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
        return data;*/
        ResultSet rst = SQLUtil.execute("SELECT * FROM item");
        ArrayList<Item> getAllItem = new ArrayList<>();
        while(rst.next()){
            Item entity = new Item(rst.getString("itemId"), rst.getString("brand"),rst.getString("model"),
                    rst.getDouble("unitPrice"),rst.getInt("qtyOnHand"));
            getAllItem.add(entity);
        }
        return getAllItem;
    }
    @Override
    public boolean updateItem(List<CartTM> cartTmList) throws SQLException {
        for(CartTM tm : cartTmList) {
            System.out.println("Item: " + tm);
            if(!updateQty(tm.getItemId(), tm.getQty())) {
                return false;
            }
        }
        return true;
    }
    @Override
    public boolean updateQty(String code, int qty) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE item SET qtyOnHand = qtyOnHand - ? WHERE ItemId = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setInt(1, qty);
        pstm.setString(2, code);

        return pstm.executeUpdate() > 0; //false
    }
}
