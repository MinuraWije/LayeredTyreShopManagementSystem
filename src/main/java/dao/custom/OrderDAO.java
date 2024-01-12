package dao.custom;

import dao.CrudDAO;
import entity.Order;

import java.sql.SQLException;
import java.time.LocalDate;

public interface OrderDAO extends CrudDAO<Order> {
    boolean saveOrder(String orderId, String customerId, LocalDate pickupDate) throws SQLException;
    String splitOrderId(String currentOrderId);
    String generateNextOrderId() throws SQLException;
    }
