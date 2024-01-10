package dao.custom.impl;

import dao.SQLUtil;
import dao.custom.EmployeeDAO;
import entity.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public boolean save(Employee entity) throws SQLException {
        /*String sql = "INSERT INTO employee(employeeId,name,address,telNum,email,role) VALUES(?,?,?,?,?,?)";
        return SQLUtil.execute(sql, employeeDTO.getEmployeeId(),employeeDTO.getName(),employeeDTO.getAddress(),employeeDTO.getTelNum(),employeeDTO.getEmail(), employeeDTO.getRole());*/
        return SQLUtil.execute("INSERT INTO employee(employeeId,name,address,telNum,email,role) VALUES(?,?,?,?,?,?)",
                entity.getEmployeeId(),entity.getName(),entity.getAddress(),entity.getTelNum(),entity.getEmail(), entity.getRole());
    }
    @Override
    public boolean update(Employee entity) throws SQLException {
        /*String sql = "UPDATE employee set name=?,address=?,telNum=?,email=?,role=? WHERE employeeId = ?";
        return SQLUtil.execute(sql,employeeDTO.getName(),employeeDTO.getAddress(),employeeDTO.getTelNum(),employeeDTO.getEmail(),employeeDTO.getRole(),employeeDTO.getEmployeeId());*/
        return SQLUtil.execute("UPDATE employee set name=?,address=?,telNum=?,email=?,role=? WHERE employeeId = ?",
                entity.getName(),entity.getAddress(),entity.getTelNum(),entity.getEmail(),entity.getRole(),entity.getEmployeeId());
    }
    @Override
    public boolean delete(String employeeId) throws SQLException {
        /*String sql = "DELETE FROM employee WHERE employeeId = ?";
        return SQLUtil.execute(sql,employeeId);*/
        return SQLUtil.execute("DELETE FROM employee WHERE employeeId = ?", employeeId);
    }
    @Override
    public ArrayList<Employee> getAll() throws SQLException {
        /*String sql = "SELECT * FROM employee";
        ResultSet resultSet = SQLUtil.execute(sql);
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
        return data;*/
        ResultSet rst = SQLUtil.execute("SELECT * FROM employee");
        ArrayList<Employee> getAllEmployee =  new ArrayList<>();
        while(rst.next()){
            Employee entity= new Employee(rst.getString("employeeId"), rst.getString("name"),
                    rst.getString("address"), rst.getInt("telNum"), rst.getString("email"), rst.getString("role"));
            getAllEmployee.add(entity);
        }
        return getAllEmployee;
    }
    @Override
    public Employee search(String employeeId) throws SQLException {
        /*String sql = "SELECT * FROM employee where employeeId = ?";

        ResultSet resultSet = SQLUtil.execute(sql, employeeId);

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
        return null;*/
        ResultSet rst = SQLUtil.execute("SELECT * FROM employee where employeeId = ?", employeeId);
        rst.next();
        return new Employee(employeeId+"",rst.getString("name"),rst.getString("address"),
                rst.getInt("telNum"), rst.getString("email"), rst.getString("role"));
    }
}
