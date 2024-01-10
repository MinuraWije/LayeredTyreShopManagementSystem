package dao.custom.impl;


import dao.SQLUtil;
import dao.custom.CustomerDAO;
import entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public boolean save(Customer entity) throws SQLException {
        /*String sql = "INSERT INTO customer(customerId,name,address,telNum,email) VALUES(?,?,?,?,?)";
        return SQLUtil.execute(sql, entity.getCustomerId(),entity.getName(),entity.getAddress(),entity.getTelNum(),entity.getEmail());*/
        return SQLUtil.execute("INSERT INTO customer(customerId,name,address,telNum,email) VALUES(?,?,?,?,?)",
                entity.getCustomerId(),entity.getName(),entity.getAddress(),entity.getTelNum(),entity.getEmail());
    }

    @Override
    public boolean update(Customer entity) throws SQLException {
        /*String sql = "UPDATE customer set name=?,address=?,telNum=?,email=? WHERE customerId = ?";
        return SQLUtil.execute(sql,entity.getName(),entity.getAddress(),entity.getTelNum(),entity.getEmail(),entity.getCustomerId());*/
        return SQLUtil.execute("UPDATE customer set name=?,address=?,telNum=?,email=? WHERE customerId = ?",
                entity.getName(),entity.getAddress(),entity.getTelNum(),entity.getEmail(),entity.getCustomerId());
    }
    @Override
    public boolean delete(String customerId) throws SQLException {
        /*String sql = "DELETE FROM customer WHERE customerId = ?";
        return SQLUtil.execute(sql,customerId);*/
        return SQLUtil.execute("DELETE FROM customer WHERE customerId = ?", customerId);
    }
    @Override
    public Customer search(String customerId) throws SQLException {
        /*String sql = "SELECT * FROM customer where customerId = ?";

        ResultSet resultSet = SQLUtil.execute(sql, customerId);

        if (resultSet.next()){
            CustomerDTO customerDTO= new CustomerDTO();
            customerDTO.setCustomerId(resultSet.getString(1));
            customerDTO.setName(resultSet.getString(2));
            customerDTO.setAddress(resultSet.getString(3));
            customerDTO.setTelNum(resultSet.getInt(4));
            customerDTO.setEmail(resultSet.getString(5));

            return customerDTO;
        }
        return null;*/
        ResultSet rst = SQLUtil.execute("SELECT * FROM customer where customerId = ?", customerId);
        rst.next();
        return new Customer(customerId+"",rst.getString("name"), rst.getString("address"), rst.getInt("telNum"), rst.getString("email"));
    }
    @Override
    public ArrayList<Customer> getAll() throws SQLException {
        /*String sql = "SELECT * FROM customer";
        ResultSet resultSet = SQLUtil.execute(sql);
        List<CustomerTM> data = new ArrayList<>();
        while (resultSet.next()) {
            CustomerTM customerTM = new CustomerTM(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getString(5)

            );
            data.add(customerTM);
        }
        return data;*/
        ResultSet rst = SQLUtil.execute("SELECT * FROM customer");
        ArrayList<Customer> getAllCustomer=new ArrayList<>();
        while (rst.next()){
            Customer entity=new Customer(rst.getString("customerId"),
                    rst.getString("name"), rst.getString("address"), rst.getInt("telNum"), rst.getString("email"));
            getAllCustomer.add(entity);
        }
        return getAllCustomer;
    }
}
