package bo.custom.impl;

import bo.custom.SupplierBO;
import dao.DAOFactory;
import dao.custom.CustomerDAO;
import dao.custom.SupplierDAO;
import dto.CustomerDTO;
import dto.SupplierDTO;
import entity.Customer;
import entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierBOImpl implements SupplierBO {
    SupplierDAO supplierDAO= (SupplierDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUPPLIER);

    @Override
    public boolean deleteSupplier(String id) throws SQLException {
        return supplierDAO.delete(id);
    }
    @Override
    public boolean saveSupplier(SupplierDTO dto) throws SQLException{
        return supplierDAO.save(new Supplier(dto.getSupplierId(),dto.getName(),dto.getAddress(),dto.getTelNum(),dto.getEmail()));
    }
    @Override
    public boolean updateSupplier(SupplierDTO dto) throws SQLException{
        return supplierDAO.update(new Supplier(dto.getSupplierId(),dto.getName(),dto.getAddress(),dto.getTelNum(),dto.getEmail()));
    }
    @Override
    public SupplierDTO searchSupplier(String supplierId) throws SQLException{
        return supplierDAO.search(supplierId);
    }
    @Override
    public ArrayList<SupplierDTO> getAllSupplier() throws SQLException{
        ArrayList<SupplierDTO>supplierDTOS=new ArrayList<>();
        ArrayList<Supplier>suppliers=supplierDAO.getAll();
        for (Supplier supplier:suppliers) {
            supplierDTOS.add(new SupplierDTO(supplier.getSupplierId(),supplier.getName(),supplier.getAddress(),supplier.getTelNum(),supplier.getEmail()));
        }
        return supplierDTOS;
    }
}
