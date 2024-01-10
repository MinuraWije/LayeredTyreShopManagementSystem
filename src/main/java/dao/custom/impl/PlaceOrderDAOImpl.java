package dao.custom.impl;

import db.DbConnection;
import dto.PlaceOrderDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

public class PlaceOrderDAOImpl {
    private OrderDAOImpl orderDAOImpl = new OrderDAOImpl();
    private ItemDAOImpl itemDAOImpl = new ItemDAOImpl();
    public boolean placeOrder(PlaceOrderDTO placeOrderDto) throws SQLException {

        String orderId = placeOrderDto.getOrderId();
        String customerId = placeOrderDto.getCustomerId();
        LocalDate pickupDate = placeOrderDto.getPickupDate();

        Connection connection = null;
        try {
            connection = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            boolean isOrderSaved =  orderDAOImpl.save(orderId, customerId,pickupDate);
            if (isOrderSaved) {
                boolean isUpdated = itemDAOImpl.updateItem(placeOrderDto.getCartTmList());
                if (isUpdated) {
                    connection.commit();
                }
            }
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }
        return true;
    }
}
