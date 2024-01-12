package bo.custom.impl;

import bo.custom.ItemBO;
import dao.DAOFactory;
import dao.custom.CustomerDAO;
import dao.custom.ItemDAO;
import dto.CustomerDTO;
import dto.ItemDTO;
import entity.Customer;
import entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBOImpl implements ItemBO{
    ItemDAO itemDAO= (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);

    @Override
    public boolean deleteItem(String id) throws SQLException {
        return itemDAO.delete(id);
    }
    @Override
    public boolean saveItem(ItemDTO dto) throws SQLException{
        return itemDAO.save(new Item(dto.getItemId(),dto.getBrand(),dto.getModel(),dto.getUnitPrice(),dto.getQtyOnHand()));
    }
    @Override
    public boolean updateItem(ItemDTO dto) throws SQLException{
        return itemDAO.update(new Item(dto.getItemId(),dto.getBrand(),dto.getModel(),dto.getUnitPrice(),dto.getQtyOnHand()));
    }
    @Override
    public ItemDTO searchItem(String itemId) throws SQLException{
        return itemDAO.search(itemId);
    }
    @Override
    public ArrayList<ItemDTO> getAllItem() throws SQLException{
        ArrayList<ItemDTO>itemDTOS=new ArrayList<>();
        ArrayList<Item>items=itemDAO.getAll();
        for (Item item:items) {
            itemDTOS.add(new ItemDTO(item.getItemId(),item.getBrand(),item.getModel(),item.getUnitPrice(),item.getQtyOnHand()));
        }
        return itemDTOS;
    }
}
