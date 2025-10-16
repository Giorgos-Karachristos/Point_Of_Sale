package controller.manager;

import controller.category.CategoryController;
import controller.employee.EmployeeController;
import controller.inventory.InventoryController;
import controller.item.ItemController;
import controller.role.RoleController;
import controller.transaction.TransactionController;
import view.category.DeleteCategoryView;
import view.category.InsetCategoryView;
import view.category.UpdateCategoryView;
import view.employee.DeleteEmployeeView;
import view.employee.InsertEmployeeView;
import view.employee.UpdateEmployeeView;
import view.inventory.DeleteInventoryView;
import view.inventory.InsertInventoryView;
import view.inventory.UpdateInventoryView;
import view.item.DeleteItemView;
import view.item.InsertItemView;
import view.item.UpdateItemView;
import view.role.DeleteRoleView;
import view.role.InsertRoleView;
import view.role.UpdateRoleView;
import view.transaction.DeleteTransactionView;
import view.transaction.InsertTransactionView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Arrays;

public class CustomTabController {
    private final ManagerController managerController;
    private final EmployeeController employeeController;
    private final RoleController roleController;
    private final ItemController itemController;
    private final CategoryController categoryController;
    private final InventoryController inventoryController;
    private final TransactionController transactionController;
    private final String tabName;

    public CustomTabController(String tabName) {
        managerController = new ManagerController();
        employeeController = new EmployeeController();
        roleController = new RoleController();
        itemController = new ItemController();
        categoryController = new CategoryController();
        inventoryController = new InventoryController();
        transactionController = new TransactionController();
        this.tabName = tabName;
    }

    public String[] getColumnNames() {
        switch (tabName) {
            case "Employee":
                String[] strings = managerController.getAttributeNames("SELECT_EMPLOYEE");
                strings = Arrays.copyOf(strings, strings.length - 1);
                return strings;
            case "Role":
                return managerController.getAttributeNames("SELECT_ROLE_COUNT");
            case "Item":
                return managerController.getAttributeNames("SELECT_ITEMS");
            case "Category":
                return managerController.getAttributeNames("SELECT_CATEGORY_COUNT");
            case "Inventory":
                return managerController.getAttributeNames("SELECT_INVENTORY");
            case "Transactions":
                return managerController.getAttributeNames("SELECT_TRANSACTION");
            default:
                return new String[]{};
        }
    }

    public void loadTableData(DefaultTableModel tableModel) {
        switch (tabName) {
            case "Employee":
                employeeController.getEmployeeTable(tableModel);
                break;
            case "Role":
                roleController.getRoleTable(tableModel);
                break;
            case "Item":
                itemController.getItemTable(tableModel);
                break;
            case "Category":
                categoryController.getCategoryTable(tableModel);
                break;
            case "Inventory":
                inventoryController.getInventoryTable(tableModel);
                break;
            case "Transactions":
                transactionController.getTransactionTable(tableModel);
                break;
        }
    }

    public void handleAddAction(DefaultTableModel tableModel) {
        JDialog addDialog;
        switch (tabName) {
            case "Employee":
                addDialog = new InsertEmployeeView(tableModel);
                break;
            case "Role":
                addDialog = new InsertRoleView(tableModel);
                break;
            case "Item":
                addDialog = new InsertItemView(tableModel);
                break;
            case "Category":
                addDialog = new InsetCategoryView(tableModel);
                break;
            case "Inventory":
                addDialog = new InsertInventoryView(tableModel);
                break;
            case "Transactions":
                addDialog = new InsertTransactionView(tableModel);
                break;
            default:
                addDialog = new JDialog();
                break;
        }
        addDialog.setModal(true);
        addDialog.setVisible(true);
    }

    public void handleUpdateAction(DefaultTableModel tableModel) {
        JDialog updateDialog;
        switch (tabName) {
            case "Employee":
                updateDialog = new UpdateEmployeeView(tableModel);
                break;
            case "Role":
                updateDialog = new UpdateRoleView(tableModel);
                break;
            case "Item":
                updateDialog = new UpdateItemView(tableModel);
                break;
            case "Category":
                updateDialog = new UpdateCategoryView(tableModel);
                break;
            case "Inventory":
                updateDialog = new UpdateInventoryView(tableModel);
                break;
            default:
                updateDialog = new JDialog();
                break;
        }
        updateDialog.setModal(true);
        updateDialog.setVisible(true);
    }

    public void handleDeleteAction(DefaultTableModel tableModel) {
        JDialog deleteDialog;
        switch (tabName) {
            case "Employee":
                deleteDialog = new DeleteEmployeeView(tableModel);
                break;
            case "Role":
                deleteDialog = new DeleteRoleView(tableModel);
                break;
            case "Item":
                deleteDialog = new DeleteItemView(tableModel);
                break;
            case "Category":
                deleteDialog = new DeleteCategoryView(tableModel);
                break;
            case "Inventory":
                deleteDialog = new DeleteInventoryView(tableModel);
                break;
            case "Transactions":
                deleteDialog = new DeleteTransactionView(tableModel);
                break;
            default:
                deleteDialog = new JDialog();
                break;
        }
        deleteDialog.setModal(true);
        deleteDialog.setVisible(true);
    }
}
