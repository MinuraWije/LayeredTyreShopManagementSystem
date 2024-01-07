package dao;

import db.DbConnection;
import dto.OrderDTO;
import dto.tm.OrderTM;
import util.CrudUtil;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderModel {
    public static OrderDTO search(String orderId) throws SQLException {
        String sql = "SELECT * FROM orders where orderId = ?";

        ResultSet resultSet = CrudUtil.execute(sql, orderId);

        if (resultSet.next()){
            OrderDTO orderDTO= new OrderDTO();
            orderDTO.setOrderId(resultSet.getString(1));
            orderDTO.setCustomerId(resultSet.getString(2));
            orderDTO.setOrderDate(resultSet.getDate(2).toLocalDate());

            return orderDTO;
        }
        return null;
    }

    public static List<OrderTM> getAll() throws SQLException {
        String sql = "SELECT * FROM orders";
        ResultSet resultSet = CrudUtil.execute(sql);
        List<OrderTM> data = new ArrayList<>();
        while (resultSet.next()) {
            OrderTM orderTM = new OrderTM(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDate(3).toLocalDate()

            );
            data.add(orderTM);
        }
        return data;
    }

    public static boolean save(OrderDTO orderDTO) throws SQLException {
        String sql = "INSERT INTO orders(orderId,customerID,orderDate) VALUES(?,?,?)";
        return CrudUtil.execute(sql, orderDTO.getOrderId(),orderDTO.getCustomerId(),orderDTO.getOrderDate());
    }

    public static boolean update(OrderDTO orderDTO) throws SQLException {
        String sql = "UPDATE orders set customerId=?,orderDate=? WHERE orderId = ?";
        return CrudUtil.execute(sql,orderDTO.getCustomerId(),orderDTO.getOrderDate(),orderDTO.getOrderId());
    }

    public static boolean delete(String orderId) throws SQLException {
        String sql = "DELETE FROM orders WHERE orderId = ?";
        return CrudUtil.execute(sql,orderId);
    }

    public static String generateNextOrderId() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT orderId FROM orders ORDER BY orderId DESC LIMIT 1";
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()) {
            return splitOrderId(resultSet.getString(1));
        }
        return splitOrderId(null);
    }

    private static String splitOrderId(String currentOrderId) {
        if(currentOrderId != null) {
            String[] split = currentOrderId.split("O0");

            int id = Integer.parseInt(split[1]); //01
            id++;
            return "O00" + id;
        } else {
            return "O001";
        }
    }
    public boolean save(String orderId, String customerId, LocalDate pickupDate) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO orders VALUES(?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, orderId);
        pstm.setString(2, customerId);
        pstm.setDate(3, Date.valueOf(pickupDate));

        return pstm.executeUpdate() > 0;
    }
}
