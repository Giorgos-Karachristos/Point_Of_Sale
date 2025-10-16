package controller.category;

import entities.Category;
import model.category.CategoryDAO;
import model.category.CategoryModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class CategoryController {

    private final CategoryDAO categoryDAO;

    public CategoryController() {
        this.categoryDAO = new CategoryModel();
    }

    public void getCategoryTable(DefaultTableModel tableModel) {
        categoryDAO.getCategoryTable(tableModel);
    }

    public List<Category> getCategoryList(DefaultComboBoxModel<String> boxModel) {
        return categoryDAO.getCategoryList(boxModel);
    }

    public void getCategoryName(DefaultComboBoxModel<String> boxModel) {
        categoryDAO.getCategoryName(boxModel);
    }

    public String insertCategory(String name, double tax, String color, String isActive) {
        return categoryDAO.insertCategory(name, tax, color, isActive);
    }

    public String updateCategory(String name, double tax, String color, String isActive) {
        return categoryDAO.updateCategory(name, tax, color, isActive);
    }

    public String deleteCategory(String name) {
        return categoryDAO.deleteCategory(name);
    }
}
