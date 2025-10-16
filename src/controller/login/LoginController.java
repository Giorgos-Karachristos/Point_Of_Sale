package controller.login;

import entities.Employee;
import model.login.LoginDAO;
import model.login.LoginModel;
import controller.pos.POSController;

import javax.swing.*;

public class LoginController {
    private final LoginModel model;
    private final POSController posController;

    public LoginController(POSController posController) {
        this.posController = posController;
        this.model = new LoginModel();
    }

    public void handleLogin(String username, String password) {
        String result = model.signIn(username, password);

        if ("Invalid username or password".equals(result) || "An error occurred".equals(result)) {
            JOptionPane.showMessageDialog(null, result, "Error", JOptionPane.ERROR_MESSAGE);
        } else if (result != null) {
            String[] words = result.split(" ");
            if (words.length >= 3) {
                Employee user = new Employee(words[0], words[1], words[2]);
                posController.onLoginSuccess(user);
            }
        }
    }
}
