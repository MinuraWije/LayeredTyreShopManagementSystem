package bo.custom;

import bo.SuperBO;
import dto.CustomerDTO;
import dto.ItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemBO extends SuperBO {
    boolean deleteItem(String itemId) throws SQLException;
    boolean saveItem(ItemDTO dto) throws SQLException;
    boolean updateItem(ItemDTO itemDTO) throws SQLException;
    ItemDTO searchItem(String itemId) throws SQLException;
    ArrayList<ItemDTO> getAllItem() throws SQLException;
}
