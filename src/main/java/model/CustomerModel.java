package model;


import dto.CustomerDTO;
import dto.tm.CustomerTM;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerModel {
    public static boolean save(CustomerDTO customerDTO) throws SQLException {
        String sql = "INSERT INTO customer(customerId,name,address,telNum,email) VALUES(?,?,?,?,?)";
        return CrudUtil.execute(sql, customerDTO.getCustomerId(),customerDTO.getName(),customerDTO.getAddress(),customerDTO.getTelNum(),customerDTO.getEmail());
    }


    public static boolean update(CustomerDTO customerDTO) throws SQLException {
        String sql = "UPDATE customer set name=?,address=?,telNum=?,email=? WHERE customerId = ?";
        return CrudUtil.execute(sql,customerDTO.getName(),customerDTO.getAddress(),customerDTO.getTelNum(),customerDTO.getEmail(),customerDTO.getCustomerId());

    }

    public static boolean delete(String customerId) throws SQLException {
        String sql = "DELETE FROM customer WHERE customerId = ?";
        return CrudUtil.execute(sql,customerId);
    }

    public static CustomerDTO search(String customerId) throws SQLException {
        String sql = "SELECT * FROM customer where customerId = ?";

        ResultSet resultSet = CrudUtil.execute(sql, customerId);

        if (resultSet.next()){
            CustomerDTO customerDTO= new CustomerDTO();
            customerDTO.setCustomerId(resultSet.getString(1));
            customerDTO.setName(resultSet.getString(2));
            customerDTO.setAddress(resultSet.getString(3));
            customerDTO.setTelNum(resultSet.getInt(4));
            customerDTO.setEmail(resultSet.getString(5));

            return customerDTO;
        }
        return null;
    }

    public static List<CustomerTM> getAll() throws SQLException {
        String sql = "SELECT * FROM customer";
        ResultSet resultSet = CrudUtil.execute(sql);
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
        return data;
    }
}
