package model.employee;

import entities.Employee;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public interface EmployeeDAO {
     void getEmployeeTable(DefaultTableModel tableModel);

     List<Employee> getEmployeeList(DefaultComboBoxModel<String> boxModel);

     String insertEmployee(String name, String surname, String phone, String email, String role, String ssn, String idn, String username, String password);

     String updateEmployee(int id, String name, String surname, String phone, String email, String role, String ssn, String idn, String username, String password);

     String deleteEmployee(int id);
}
