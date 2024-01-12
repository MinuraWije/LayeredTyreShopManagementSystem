package bo.custom;

import bo.SuperBO;
import dto.CustomerDTO;
import dto.PaymentDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PaymentBO extends SuperBO {
    boolean deletePayment(String paymentId) throws SQLException;
    boolean savePayment(PaymentDTO dto) throws SQLException;
    boolean updatePayment(PaymentDTO paymentDTO) throws SQLException;
    PaymentDTO searchPayment(String paymentId) throws SQLException;

    ArrayList<PaymentDTO> getAllPayment() throws SQLException;
}
