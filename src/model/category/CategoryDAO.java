package model.category;

import entities.Category;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public interface CategoryDAO {
    void getCategoryTable(DefaultTableModel tableModel);

    List<Category> getCategoryList(DefaultComboBoxModel<String> boxModel);

    void getCategoryName(DefaultComboBoxModel<String> boxModel);

    String insertCategory(String name, double tax, String color, String isActive);

    String updateCategory(String name, double tax, String color, String isActive);

    String deleteCategory(String name);
}
