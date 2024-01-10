package dao.custom.impl;

import dao.SQLUtil;
import dao.custom.PaymentDAO;
import entity.Payment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentDAOImpl implements PaymentDAO {
    @Override
    public boolean save(Payment entity) throws SQLException {
        /*String sql = "INSERT INTO payment(paymentId,orderId,amount,paymentDate,description) VALUES(?,?,?,?,?)";
        return SQLUtil.execute(sql, entity.getPaymentId(),entity.getOrderId(),entity.getAmount(),entity.getPaymentDate(),entity.getDescription());*/
        return SQLUtil.execute("INSERT INTO payment(paymentId,orderId,amount,paymentDate,description) VALUES(?,?,?,?,?)",
                entity.getPaymentId(),entity.getOrderId(),entity.getAmount(),entity.getPaymentDate(),entity.getDescription());
    }
    @Override
    public boolean delete(String paymentId) throws SQLException {
        /*String sql = "DELETE FROM payment WHERE paymentId = ?";
        return SQLUtil.execute(sql,paymentId);*/
        return SQLUtil.execute("DELETE FROM payment WHERE paymentId = ?",paymentId);
    }
    @Override
    public boolean update(Payment entity) throws SQLException {
        /*String sql = "UPDATE payment set orderId=?,amount=?,paymentDate=?,description=? WHERE paymentId = ?";
        return SQLUtil.execute(sql,entity.getOrderId(),entity.getAmount(),entity.getPaymentDate(),entity.getDescription(),entity.getPaymentId());*/
        return SQLUtil.execute("UPDATE payment set orderId=?,amount=?,paymentDate=?,description=? WHERE paymentId = ?",
                entity.getOrderId(),entity.getAmount(),entity.getPaymentDate(),entity.getDescription(),entity.getPaymentId());
    }
    @Override
    public Payment search(String paymentId) throws SQLException {
        /*String sql = "SELECT * FROM payment where paymentId = ?";

        ResultSet resultSet = SQLUtil.execute(sql, paymentId);

        if (resultSet.next()){
            PaymentDTO paymentDTO= new PaymentDTO();
            paymentDTO.setPaymentId(resultSet.getString(1));
            paymentDTO.setOrderId(resultSet.getString(2));
            paymentDTO.setAmount(resultSet.getDouble(3));
            paymentDTO.setPaymentDate(resultSet.getDate(4).toLocalDate());
            paymentDTO.setDescription(resultSet.getString(5));

            return paymentDTO;
        }
        return null;*/
        ResultSet rst = SQLUtil.execute("SELECT * FROM payment where paymentId = ?",paymentId);
        rst.next();
        return new Payment(paymentId+"",rst.getString("orderId"),rst.getDouble("amount"),
                rst.getDate("paymentDate").toLocalDate(),rst.getString("description"));
    }
    @Override
    public ArrayList<Payment> getAll() throws SQLException {
        /*String sql = "SELECT * FROM payment";
        ResultSet resultSet = SQLUtil.execute(sql);
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
        return data;*/
        ResultSet rst = SQLUtil.execute("SELECT * FROM payment");
        ArrayList<Payment> getAllPayment = new ArrayList<>();
        while(rst.next()){
            Payment entity = new Payment(rst.getString("paymentId"),rst.getString("orderId"),rst.getDouble("amount"),
                    rst.getDate("paymentDate").toLocalDate(),rst.getString("description"));
            getAllPayment.add(entity);
        }
        return getAllPayment;
    }
}
