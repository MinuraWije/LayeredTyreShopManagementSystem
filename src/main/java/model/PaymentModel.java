package model;

import dto.PaymentDTO;
import dto.tm.PaymentTM;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentModel {
    public static boolean save(PaymentDTO paymentDTO) throws SQLException {
        String sql = "INSERT INTO payment(paymentId,orderId,amount,paymentDate,description) VALUES(?,?,?,?,?)";
        return CrudUtil.execute(sql, paymentDTO.getPaymentId(),paymentDTO.getOrderId(),paymentDTO.getAmount(),paymentDTO.getPaymentDate(),paymentDTO.getDescription());
    }

    public static boolean delete(String paymentId) throws SQLException {
        String sql = "DELETE FROM payment WHERE paymentId = ?";
        return CrudUtil.execute(sql,paymentId);
    }

    public static boolean update(PaymentDTO paymentDTO) throws SQLException {
        String sql = "UPDATE payment set orderId=?,amount=?,paymentDate=?,description=? WHERE paymentId = ?";
        return CrudUtil.execute(sql,paymentDTO.getOrderId(),paymentDTO.getAmount(),paymentDTO.getPaymentDate(),paymentDTO.getDescription(),paymentDTO.getPaymentId());

    }

    public static PaymentDTO search(String paymentId) throws SQLException {
        String sql = "SELECT * FROM payment where paymentId = ?";

        ResultSet resultSet = CrudUtil.execute(sql, paymentId);

        if (resultSet.next()){
            PaymentDTO paymentDTO= new PaymentDTO();
            paymentDTO.setPaymentId(resultSet.getString(1));
            paymentDTO.setOrderId(resultSet.getString(2));
            paymentDTO.setAmount(resultSet.getDouble(3));
            paymentDTO.setPaymentDate(resultSet.getDate(4).toLocalDate());
            paymentDTO.setDescription(resultSet.getString(5));

            return paymentDTO;
        }
        return null;
    }

    public static List<PaymentTM> getAll() throws SQLException {
        String sql = "SELECT * FROM payment";
        ResultSet resultSet = CrudUtil.execute(sql);
        List<PaymentTM> data = new ArrayList<>();
        while (resultSet.next()) {
            PaymentTM paymentTM = new PaymentTM(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                    resultSet.getDate(4).toLocalDate(),
                    resultSet.getString(5)
            );
            data.add(paymentTM);
        }
        return data;

    }
}
