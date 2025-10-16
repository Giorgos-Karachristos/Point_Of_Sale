package controller.item;

import entities.Item;
import model.item.ItemDAO;
import model.item.ItemModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ItemController {
    private final ItemDAO itemDAO;

    public ItemController() {
        this.itemDAO = new ItemModel();
    }

    public void getItemTable(DefaultTableModel tableModel) {
        itemDAO.getItemTable(tableModel);
    }

    public List<Item> getItemList(DefaultComboBoxModel<String> boxModel) {
        return itemDAO.getItemList(boxModel);
    }

    public String insertItem(String code, String name, String description, double price, double cost, String category, String color, String isActive) {
        return itemDAO.insertItem(code, name, description, price, cost, category, color, isActive);
    }

    public String updateItem(int id, String code, String name, String description, double price, double cost, String category, String color, String isActive) {
        return itemDAO.updateItem(id, code, name, description, price, cost, category, color, isActive);
    }

    public String deleteItem(int id) {
        return itemDAO.deleteItem(id);
    }
}
