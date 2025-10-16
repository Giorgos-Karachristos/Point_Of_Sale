package controller.employee;

import entities.Employee;
import model.employee.EmployeeDAO;
import model.employee.EmployeeModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class EmployeeController {
    private final EmployeeDAO employeeDAO;

    public EmployeeController() {
        this.employeeDAO = new EmployeeModel();
    }

    public void getEmployeeTable(DefaultTableModel tableModel) {
        employeeDAO.getEmployeeTable(tableModel);
    }

    public List<Employee> getEmployeeList(DefaultComboBoxModel<String> boxModel) {
        return employeeDAO.getEmployeeList(boxModel);
    }

    public String insertEmployee(String name, String surname, String phone, String email, String role, String ssn, String idn, String username, String password) {
        return employeeDAO.insertEmployee(name, surname, phone, email, role, ssn, idn, username, password);
    }

    public String updateEmployee(int id, String name, String surname, String phone, String email, String role, String ssn, String idn, String username, String password) {
        return employeeDAO.updateEmployee(id, name, surname, phone, email, role, ssn, idn, username, password);
    }

    public String deleteEmployee(int id) {
        return employeeDAO.deleteEmployee(id);
    }
}
