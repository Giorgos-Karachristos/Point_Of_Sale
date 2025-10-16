package model.inventory;

import entities.InventoryItem;
import entities.Item;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public interface InventoryDAO {
    void getInventoryTable(DefaultTableModel tableModel);

    List<InventoryItem> getInventoryList(DefaultComboBoxModel<String> boxModel);

    List<Item> getInventoryComboBox(DefaultComboBoxModel<String> boxModel);

    String insertInventory(int id, String location, int quantity, int minQuantity, int maxQuantity);

    String updateInventory(int id, String location, int quantity, int minQuantity, int maxQuantity);

    String deleteInventory(int id);
}
