package bo.custom.impl;

import bo.custom.EmployeeBO;
import dao.DAOFactory;
import dao.custom.EmployeeDAO;
import dto.EmployeeDTO;
import entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeBOImpl implements EmployeeBO {
    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE);
    @Override
    public boolean deleteEmployee(String id) throws SQLException {
        return employeeDAO.delete(id);
    }
    @Override
    public boolean saveEmployee(EmployeeDTO dto) throws SQLException{
        return employeeDAO.save(new Employee(dto.getEmployeeId(),dto.getName(),dto.getAddress(),dto.getTelNum(),dto.getEmail(),dto.getRole()));
    }
    @Override
    public boolean updateEmployee(EmployeeDTO dto) throws SQLException{
        return employeeDAO.update(new Employee(dto.getEmployeeId(),dto.getName(),dto.getAddress(),dto.getTelNum(),dto.getEmail(),dto.getRole()));
    }
    @Override
    public EmployeeDTO searchEmployee(String employeeId) throws SQLException{
        return employeeDAO.search(employeeId);
    }
    @Override
    public ArrayList<EmployeeDTO> getAllEmployee() throws SQLException{
        ArrayList<EmployeeDTO>employeeDTOS=new ArrayList<>();
        ArrayList<Employee>employees=employeeDAO.getAll();
        for (Employee employee:employees) {
            employeeDTOS.add(new EmployeeDTO(employee.getEmployeeId(),employee.getName(),employee.getAddress(),employee.getTelNum(),employee.getEmail(),employee.getRole()));
        }
        return employeeDTOS;
    }
}
