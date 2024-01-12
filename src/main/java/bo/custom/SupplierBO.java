package bo.custom;

import bo.SuperBO;
import dto.CustomerDTO;
import dto.SupplierDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierBO extends SuperBO {
    boolean deleteSupplier(String supplierId) throws SQLException;
    boolean saveSupplier(SupplierDTO dto) throws SQLException;
    boolean updateSupplier(SupplierDTO supplierDTO) throws SQLException;
    SupplierDTO searchSupplier(String supplierId) throws SQLException;
    ArrayList<SupplierDTO> getAllSupplier() throws SQLException;
}
