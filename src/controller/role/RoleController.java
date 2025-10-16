package controller.role;


import entities.Role;
import model.role.RoleDAO;
import model.role.RoleModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class RoleController {

    private final RoleDAO roleDAO;

    public RoleController() {
        this.roleDAO = new RoleModel();
    }

    public void getRoleTable(DefaultTableModel tableModel) {
        roleDAO.getRoleTable(tableModel);
    }

    public List<Role> getRoleList(DefaultComboBoxModel<String> boxModel) {
        return roleDAO.getRoleList(boxModel);
    }

    public void getRoleName(DefaultComboBoxModel<String> boxModel) {
        roleDAO.getRoleName(boxModel);
    }

    public void getPOSAccess(DefaultComboBoxModel<String> boxModel) {
        roleDAO.getPOSAccess(boxModel);
    }

    public String insertRole(String roleName, String access) {
        return roleDAO.insertRole(roleName,access);
    }

    public String updateRole(String roleName, String access) {
        return roleDAO.updateRole(roleName,access);
    }

    public String deleteRole(String roleName) {
        return roleDAO.deleteRole(roleName);
    }
}
