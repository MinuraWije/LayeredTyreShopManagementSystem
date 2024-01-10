package bo.custom;

import bo.SuperBO;
import dto.CustomerDTO;
import dto.EmployeeDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeBO extends SuperBO {
    boolean deleteEmployee(String employeeId) throws SQLException;
    boolean saveEmployee(EmployeeDTO dto) throws SQLException;
    boolean updateEmployee(EmployeeDTO employeeDTO) throws SQLException;
    EmployeeDTO searchEmployee(String employeeId) throws SQLException;
    ArrayList<EmployeeDTO> getAllEmployee() throws SQLException;
}
