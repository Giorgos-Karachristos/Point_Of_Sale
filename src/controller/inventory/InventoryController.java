package controller.inventory;

import entities.InventoryItem;
import entities.Item;
import model.inventory.InventoryDAO;
import model.inventory.InventoryModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class InventoryController {
    private final InventoryDAO inventoryDAO;

    public InventoryController() {
        this.inventoryDAO = new InventoryModel();
    }

    public void getInventoryTable(DefaultTableModel tableModel) {
        inventoryDAO.getInventoryTable(tableModel);
    }

    public List<InventoryItem> getInventoryList(DefaultComboBoxModel<String> boxModel) {
        return inventoryDAO.getInventoryList(boxModel);
    }

    public List<Item> getInventoryComboBox(DefaultComboBoxModel<String> boxModel) {
        return inventoryDAO.getInventoryComboBox(boxModel);
    }

    public String insertInventory(int id, String location, int quantity, int minQuantity, int maxQuantity) {
        return inventoryDAO.insertInventory(id, location, quantity, minQuantity, maxQuantity);
    }

    public String updateInventory(int id, String location, int quantity, int minQuantity, int maxQuantity) {
        return inventoryDAO.updateInventory(id, location, quantity, minQuantity, maxQuantity);
    }

    public String deleteInventory(int id) {
        return inventoryDAO.deleteInventory(id);
    }
}
