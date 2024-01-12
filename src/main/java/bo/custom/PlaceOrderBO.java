package bo.custom;

import bo.SuperBO;
import dto.PlaceOrderDTO;

import java.sql.SQLException;

public interface PlaceOrderBO extends SuperBO {
    public boolean placeOrder(PlaceOrderDTO placeOrderDto) throws SQLException;
}
