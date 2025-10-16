package model.item;

import entities.Item;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public interface ItemDAO {
    void getItemTable(DefaultTableModel tableModel);

    List<Item> getItemList(DefaultComboBoxModel<String> boxModel);

    String insertItem(String code, String name, String description, double price, double cost, String category, String color, String isActive);

    String updateItem(int id, String code, String name, String description, double price, double cost, String category, String color, String isActive);

    String deleteItem(int id);
}
