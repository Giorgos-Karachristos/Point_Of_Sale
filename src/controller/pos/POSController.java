package controller.pos;

import entities.Employee;
import view.pos.POSView;

public class POSController {

    private final POSView posView;

    public POSController() {
        posView = new POSView(this);
        posView.showLoginView();
    }

    // Called by LoginController when login succeeds
    public void onLoginSuccess(Employee user) {
        posView.showMainView(user);
    }

    public void onSignOut() {
        posView.showLoginView();
    }
}

