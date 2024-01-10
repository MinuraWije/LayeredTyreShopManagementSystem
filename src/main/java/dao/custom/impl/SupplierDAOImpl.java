package dao.custom.impl;

import dao.SQLUtil;
import dao.custom.SupplierDAO;
import entity.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierDAOImpl implements SupplierDAO {
    @Override
    public boolean delete(String supplierId) throws SQLException {
        /*String sql = "DELETE FROM supplier WHERE supplierId = ?";
        return SQLUtil.execute(sql,supplierId);*/
        return SQLUtil.execute("DELETE FROM supplier WHERE supplierId = ?",supplierId);
    }
    @Override
    public boolean save(Supplier entity) throws SQLException {
        /*String sql = "INSERT INTO supplier(supplierId,name,address,telNum,email) VALUES(?,?,?,?,?)";
        return SQLUtil.execute(sql, entity.getSupplierId(),entity.getName(),entity.getAddress(),entity.getTelNum(),entity.getEmail());*/
        return SQLUtil.execute("INSERT INTO supplier(supplierId,name,address,telNum,email) VALUES(?,?,?,?,?)",
                entity.getSupplierId(),entity.getName(),entity.getAddress(),entity.getTelNum(),entity.getEmail());
    }
    @Override
    public boolean update(Supplier entity) throws SQLException {
        /*String sql = "UPDATE supplier set name=?,address=?,telNum=?,email=? WHERE supplierId = ?";
        return SQLUtil.execute(sql,entity.getName(),entity.getAddress(),entity.getTelNum(),entity.getEmail(),entity.getSupplierId());*/
        return SQLUtil.execute("UPDATE supplier set name=?,address=?,telNum=?,email=? WHERE supplierId = ?",
                entity.getName(),entity.getAddress(),entity.getTelNum(),entity.getEmail(),entity.getSupplierId());
    }
    @Override
    public Supplier search(String supplierId) throws SQLException {
        /*String sql = "SELECT * FROM supplier where supplierId = ?";

        ResultSet resultSet = SQLUtil.execute(sql, supplierId);

        if (resultSet.next()){
            SupplierDTO supplierDTO= new SupplierDTO();
            supplierDTO.setSupplierId(resultSet.getString(1));
            supplierDTO.setName(resultSet.getString(2));
            supplierDTO.setAddress(resultSet.getString(3));
            supplierDTO.setTelNum(resultSet.getInt(4));
            supplierDTO.setEmail(resultSet.getString(5));

            return supplierDTO;
        }
        return null;*/
        ResultSet rst = SQLUtil.execute("SELECT * FROM supplier where supplierId = ?",supplierId);
        rst.next();
        return new Supplier(supplierId+"",rst.getString("name"),
                rst.getString("address"),rst.getInt("telNum"),rst.getString("email"));
    }
    @Override
    public ArrayList<Supplier> getAll() throws SQLException {
        /*String sql = "SELECT * FROM supplier";
        ResultSet resultSet = SQLUtil.execute(sql);
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
        return data;*/
        ResultSet rst = SQLUtil.execute("SELECT * FROM supplier");
        ArrayList<Supplier> getAllSupplier = new ArrayList<>();
        while(rst.next()){
            Supplier entity = new Supplier(rst.getString("supplierId"),rst.getString("name"),
                    rst.getString("address"),rst.getInt("telNum"),rst.getString("email"));
            getAllSupplier.add(entity);
        }
        return getAllSupplier;
    }
}
