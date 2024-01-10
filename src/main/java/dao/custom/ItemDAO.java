package dao.custom;

import dao.CrudDAO;
import view.tdm.CartTM;
import entity.Item;

import java.sql.SQLException;
import java.util.List;

public interface ItemDAO extends CrudDAO<Item> {
    boolean updateItem(List<CartTM> cartTmList) throws SQLException;
    boolean updateQty(String code, int qty) throws SQLException;
}
