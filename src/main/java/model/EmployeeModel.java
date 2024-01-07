package model;

import dto.EmployeeDTO;
import dto.tm.EmployeeTM;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeModel {
    public static boolean save(EmployeeDTO employeeDTO) throws SQLException {
        String sql = "INSERT INTO employee(employeeId,name,address,telNum,email,role) VALUES(?,?,?,?,?,?)";
        return CrudUtil.execute(sql, employeeDTO.getEmployeeId(),employeeDTO.getName(),employeeDTO.getAddress(),employeeDTO.getTelNum(),employeeDTO.getEmail(), employeeDTO.getRole());
    }

    public static boolean update(EmployeeDTO employeeDTO) throws SQLException {
        String sql = "UPDATE employee set name=?,address=?,telNum=?,email=?,role=? WHERE employeeId = ?";
        return CrudUtil.execute(sql,employeeDTO.getName(),employeeDTO.getAddress(),employeeDTO.getTelNum(),employeeDTO.getEmail(),employeeDTO.getRole(),employeeDTO.getEmployeeId());
    }

    public static boolean delete(String employeeId) throws SQLException {
        String sql = "DELETE FROM employee WHERE employeeId = ?";
        return CrudUtil.execute(sql,employeeId);
    }

    public static List<EmployeeTM> getAll() throws SQLException {
        String sql = "SELECT * FROM employee";
        ResultSet resultSet = CrudUtil.execute(sql);
        List<EmployeeTM> data = new ArrayList<>();
        while (resultSet.next()) {
            EmployeeTM employeeTM = new EmployeeTM(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getString(5),
                    resultSet.getString(6)

            );
            data.add(employeeTM);
        }
        return data;
    }

    public static EmployeeDTO search(String employeeId) throws SQLException {
        String sql = "SELECT * FROM employee where employeeId = ?";

        ResultSet resultSet = CrudUtil.execute(sql, employeeId);

        if (resultSet.next()){
            EmployeeDTO employeeDTO= new EmployeeDTO();
            employeeDTO.setEmployeeId(resultSet.getString(1));
            employeeDTO.setName(resultSet.getString(2));
            employeeDTO.setAddress(resultSet.getString(3));
            employeeDTO.setTelNum(resultSet.getInt(4));
            employeeDTO.setEmail(resultSet.getString(5));
            employeeDTO.setRole(resultSet.getString(6));

            return employeeDTO;
        }
        return null;
    }
}
