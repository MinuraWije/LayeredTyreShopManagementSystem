package bo.custom;

import bo.SuperBO;
import dto.OrderDTO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface OrderBO extends SuperBO {
    boolean deleteOrder(String id) throws SQLException;
    boolean saveOrder(OrderDTO dto) throws SQLException;
    boolean updateOrder(OrderDTO dto) throws SQLException;
    OrderDTO searchOrder(String orderId) throws SQLException;
    ArrayList<OrderDTO> getAllOrder() throws SQLException;
    boolean saveOrder(String orderId, String customerId, LocalDate pickupDate) throws SQLException;
    String splitOrderId(String currentOrderId);
    String generateNextOrderId() throws SQLException;
}
