package bo.custom.impl;

import bo.custom.CustomerBO;
import dao.DAOFactory;
import dao.custom.CustomerDAO;
import dto.CustomerDTO;
import entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {
    CustomerDAO customerDAO= (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public boolean deleteCustomer(String id) throws SQLException{
        return customerDAO.delete(id);
    }
    @Override
    public boolean saveCustomer(CustomerDTO dto) throws SQLException{
        return customerDAO.save(new Customer(dto.getCustomerId(),dto.getName(),dto.getAddress(),dto.getTelNum(),dto.getEmail()));
    }
    @Override
    public boolean updateCustomer(CustomerDTO dto) throws SQLException{
        return customerDAO.update(new Customer(dto.getCustomerId(),dto.getName(),dto.getAddress(),dto.getTelNum(),dto.getEmail()));
    }
    @Override
    public CustomerDTO searchCustomer(String customerId) throws SQLException{
        return customerDAO.search(customerId);
    }
    @Override
    public ArrayList<CustomerDTO> getAllCustomer() throws SQLException{
        ArrayList<CustomerDTO>customerDTOS=new ArrayList<>();
        ArrayList<Customer>customers=customerDAO.getAll();
        for (Customer customer:customers) {
            customerDTOS.add(new CustomerDTO(customer.getCustomerId(),customer.getName(),customer.getAddress(),customer.getTelNum(), customer.getEmail()));
        }
        return customerDTOS;
    }
}
