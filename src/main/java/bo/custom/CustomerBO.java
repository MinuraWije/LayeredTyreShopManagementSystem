package bo.custom;

import bo.SuperBO;
import dto.CustomerDTO;
import entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBO {
    boolean deleteCustomer(String customerId) throws SQLException;
    boolean saveCustomer(CustomerDTO dto) throws SQLException;
    boolean updateCustomer(CustomerDTO customerDTO) throws SQLException;
    CustomerDTO searchCustomer(String customerId) throws SQLException;

    ArrayList<CustomerDTO> getAllCustomer() throws SQLException;
}
