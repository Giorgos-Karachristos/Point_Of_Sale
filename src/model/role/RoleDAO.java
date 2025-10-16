package model.role;

import entities.Role;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public interface RoleDAO {
    void getRoleTable(DefaultTableModel tableModel);

    List<Role> getRoleList(DefaultComboBoxModel<String> boxModel);

    void getRoleName(DefaultComboBoxModel<String> boxModel);

    void getPOSAccess(DefaultComboBoxModel<String> boxModel);

    String insertRole(String roleName, String access);

    String updateRole(String roleName, String access);

    String deleteRole(String roleName);
}
