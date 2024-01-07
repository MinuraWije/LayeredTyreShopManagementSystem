package model;

import dto.SupplierDTO;
import dto.tm.SupplierTM;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierModel {
    public static boolean delete(String supplierId) throws SQLException {
        String sql = "DELETE FROM supplier WHERE supplierId = ?";
        return CrudUtil.execute(sql,supplierId);
    }

    public static boolean save(SupplierDTO supplierDTO) throws SQLException {
        String sql = "INSERT INTO supplier(supplierId,name,address,telNum,email) VALUES(?,?,?,?,?)";
        return CrudUtil.execute(sql, supplierDTO.getSupplierId(),supplierDTO.getName(),supplierDTO.getAddress(),supplierDTO.getTelNum(),supplierDTO.getEmail());
    }

    public static boolean update(SupplierDTO supplierDTO) throws SQLException {
        String sql = "UPDATE supplier set name=?,address=?,telNum=?,email=? WHERE supplierId = ?";
        return CrudUtil.execute(sql,supplierDTO.getName(),supplierDTO.getAddress(),supplierDTO.getTelNum(),supplierDTO.getEmail(),supplierDTO.getSupplierId());

    }

    public static SupplierDTO search(String supplierId) throws SQLException {
        String sql = "SELECT * FROM supplier where supplierId = ?";

        ResultSet resultSet = CrudUtil.execute(sql, supplierId);

        if (resultSet.next()){
            SupplierDTO supplierDTO= new SupplierDTO();
            supplierDTO.setSupplierId(resultSet.getString(1));
            supplierDTO.setName(resultSet.getString(2));
            supplierDTO.setAddress(resultSet.getString(3));
            supplierDTO.setTelNum(resultSet.getInt(4));
            supplierDTO.setEmail(resultSet.getString(5));

            return supplierDTO;
        }
        return null;
    }

    public static List<SupplierTM> getAll() throws SQLException {
        String sql = "SELECT * FROM supplier";
        ResultSet resultSet = CrudUtil.execute(sql);
        List<SupplierTM> data = new ArrayList<>();
        while (resultSet.next()) {
            SupplierTM supplierTM = new SupplierTM(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getString(5)

            );
            data.add(supplierTM);
        }
        return data;
    }
}
