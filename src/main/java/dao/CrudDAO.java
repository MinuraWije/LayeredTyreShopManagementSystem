package dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T> extends SuperDAO{
    boolean save(T DTO) throws SQLException;
    boolean update(T DTO) throws SQLException;
    boolean delete(String id) throws SQLException;
    T search(String id) throws SQLException;
    ArrayList<T> getAll() throws SQLException;

}
