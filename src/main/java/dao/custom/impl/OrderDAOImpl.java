package dao.custom.impl;

import dao.SQLUtil;
import dao.custom.OrderDAO;
import db.DbConnection;
import dto.OrderDTO;
import entity.Order;
import view.tdm.OrderTM;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public Order search(String orderId) throws SQLException {
        /*String sql = "SELECT * FROM orders where orderId = ?";

        ResultSet resultSet = SQLUtil.execute(sql, orderId);

        if (resultSet.next()){
            OrderDTO orderDTO= new OrderDTO();
            orderDTO.setOrderId(resultSet.getString(1));
            orderDTO.setCustomerId(resultSet.getString(2));
            orderDTO.setOrderDate(resultSet.getDate(2).toLocalDate());

            return orderDTO;
        }
        return null;*/
        ResultSet rst = SQLUtil.execute("SELECT * FROM orders where orderId = ?",orderId);
        rst.next();
        return new Order(orderId+"",rst.getString("customerId"), rst.getDate("orderDate").toLocalDate());
    }
    @Override
    public ArrayList<Order> getAll() throws SQLException {
        /*String sql = "SELECT * FROM orders";
        ResultSet resultSet = SQLUtil.execute(sql);
        List<OrderTM> data = new ArrayList<>();
        while (resultSet.next()) {
            OrderTM orderTM = new OrderTM(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDate(3).toLocalDate()

            );
            data.add(orderTM);
        }
        return data;*/
        ResultSet rst = SQLUtil.execute("SELECT * FROM orders");
        ArrayList<Order> getAllOrder = new ArrayList<>();
        while(rst.next()){
            Order entity = new Order(rst.getString("orderId"),rst.getString("customerId"), rst.getDate("orderDate").toLocalDate());
            getAllOrder.add(entity);
        }
        return getAllOrder;
    }
    @Override
    public boolean save(Order entity) throws SQLException {
        return SQLUtil.execute("INSERT INTO orders(orderId,customerID,orderDate) VALUES(?,?,?)",
                entity.getOrderId(),entity.getCustomerId(),entity.getOrderDate());
    }
    @Override
    public boolean update(Order entity) throws SQLException {
        return SQLUtil.execute("UPDATE orders set customerId=?,orderDate=? WHERE orderId = ?",
                entity.getCustomerId(),entity.getOrderDate(),entity.getOrderId());
    }
    @Override
    public boolean delete(String orderId) throws SQLException {
        return SQLUtil.execute("DELETE FROM orders WHERE orderId = ?",orderId);
    }
    @Override
    public String generateNextOrderId() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT orderId FROM orders ORDER BY orderId DESC LIMIT 1";
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()) {
            return splitOrderId(resultSet.getString(1));
        }
        return splitOrderId(null);
    }
    @Override
    public String splitOrderId(String currentOrderId) {
        if(currentOrderId != null) {
            String[] split = currentOrderId.split("O0");

            int id = Integer.parseInt(split[1]); //01
            id++;
            return "O00" + id;
        } else {
            return "O001";
        }
    }
    @Override
    public boolean saveOrder(String orderId, String customerId, LocalDate pickupDate) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO orders VALUES(?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, orderId);
        pstm.setString(2, customerId);
        pstm.setDate(3, Date.valueOf(pickupDate));

        return pstm.executeUpdate() > 0;
        /*return SQLUtil.execute("INSERT INTO orders VALUES(?, ?, ?)",
                orderId,customerId,pickupDate);*/
    }
}
