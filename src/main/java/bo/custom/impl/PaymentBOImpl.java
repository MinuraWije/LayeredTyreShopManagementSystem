package bo.custom.impl;

import bo.custom.PaymentBO;
import dao.DAOFactory;
import dao.custom.CustomerDAO;
import dao.custom.PaymentDAO;
import dto.CustomerDTO;
import dto.PaymentDTO;
import entity.Customer;
import entity.Payment;

import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentBOImpl implements PaymentBO {
    PaymentDAO paymentDAO= (PaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);

    @Override
    public boolean deletePayment(String id) throws SQLException {
        return paymentDAO.delete(id);
    }
    @Override
    public boolean savePayment(PaymentDTO dto) throws SQLException{
        return paymentDAO.save(new Payment(dto.getPaymentId(),dto.getOrderId(),dto.getAmount(),dto.getPaymentDate(),dto.getDescription()));
    }
    @Override
    public boolean updatePayment(PaymentDTO dto) throws SQLException{
        return paymentDAO.update(new Payment(dto.getPaymentId(),dto.getOrderId(),dto.getAmount(),dto.getPaymentDate(),dto.getDescription()));
    }
    @Override
    public PaymentDTO searchPayment(String paymentId) throws SQLException{
        return paymentDAO.search(paymentId);
    }
    @Override
    public ArrayList<PaymentDTO> getAllPayment() throws SQLException{
        ArrayList<PaymentDTO>paymentDTOS=new ArrayList<>();
        ArrayList<Payment>payments=paymentDAO.getAll();
        for (Payment payment:payments) {
            paymentDTOS.add(new PaymentDTO(payment.getPaymentId(),payment.getOrderId(),payment.getAmount(),payment.getPaymentDate(),payment.getDescription()));
        }
        return paymentDTOS;
    }
}
