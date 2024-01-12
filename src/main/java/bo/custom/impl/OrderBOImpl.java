package bo.custom.impl;

import bo.custom.OrderBO;
import dao.DAOFactory;
import dao.custom.OrderDAO;
import db.DbConnection;
import dto.CustomerDTO;
import dto.OrderDTO;
import entity.Customer;
import entity.Order;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class OrderBOImpl implements OrderBO {
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    @Override
    public boolean deleteOrder(String id) throws SQLException {
        return orderDAO.delete(id);
    }
    @Override
    public boolean saveOrder(OrderDTO dto) throws SQLException{
        return orderDAO.save(new Order(dto.getOrderId(),dto.getCustomerId(),dto.getOrderDate()));
    }
    @Override
    public boolean updateOrder(OrderDTO dto) throws SQLException{
        return orderDAO.update(new Order(dto.getOrderId(),dto.getCustomerId(),dto.getOrderDate()));
    }
    @Override
    public OrderDTO searchOrder(String orderId) throws SQLException{
        return orderDAO.search(orderId);
    }
    @Override
    public ArrayList<OrderDTO> getAllOrder() throws SQLException{
        ArrayList<OrderDTO>orderDTOS=new ArrayList<>();
        ArrayList<Order>orders=orderDAO.getAll();
        for (Order order:orders) {
            orderDTOS.add(new OrderDTO(order.getOrderId(),order.getCustomerId(),order.getOrderDate()));
        }
        return orderDTOS;
    }
    @Override
    public String generateNextOrderId() throws SQLException {
        return orderDAO.generateNextOrderId();
    }
    @Override
    public String splitOrderId(String currentOrderId) {
        /*if(currentOrderId != null) {
            String[] split = currentOrderId.split("O0");

            int id = Integer.parseInt(split[1]); //01
            id++;
            return "O00" + id;
        } else {
            return "O001";
        }*/
        return orderDAO.splitOrderId(currentOrderId);
    }
    @Override
    public boolean saveOrder(String orderId, String customerId, LocalDate pickupDate) throws SQLException {
        /*Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO orders VALUES(?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, orderId);
        pstm.setString(2, customerId);
        pstm.setDate(3, Date.valueOf(pickupDate));

        return pstm.executeUpdate() > 0;*/
        /*return SQLUtil.execute("INSERT INTO orders VALUES(?, ?, ?)",
                orderId,customerId,pickupDate);*/
        return orderDAO.saveOrder(orderId,customerId,pickupDate);
    }
}
