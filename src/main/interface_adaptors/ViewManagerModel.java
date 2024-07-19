package interface_adaptors;

import view.LoginView;

import javax.swing.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.*;

public class ViewManagerModel {

    private String activeViewName;
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private JFrame currentView;
    private Map<String, JFrame> views;


    public ViewManagerModel() {
        this.views = new HashMap<>();
    }

    public String getActiveViewName() {
        return activeViewName;
    }

    public void setActiveViewName(String activeViewName) {
        String oldActiveViewName = this.activeViewName;
        this.activeViewName = activeViewName;
        support.firePropertyChange("activeViewName", oldActiveViewName, this.activeViewName);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    // remove
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

    public void changeView(String viewName) {
        if (currentView != null) {
            currentView.dispose();
        }

        currentView = views.get(viewName);
        if (currentView != null) {
            currentView.setVisible(true);
        } else {
            creatView(viewName);
        }
    }

    private void createView(String viewName) {
        switch (viewName) {
            case "sign up":
                break;
            case "log in":
                LoginViewModel loginViewModel = new LoginViewModel();
                LoginController loginController = LoginUseCaseFactory.createLoginController(this, loginViewModel);
                LoginView loginView = new LoginView(loginViewModel, loginController);
                views.put("log in", loginView);
                currentView = loginView;
                break;
            case "one time":
                break;
            case "periodic":
                break;
            case "log out":
                break;
        }
        if (currentView != null) {
            currentView.setVisible(true);
        }
        setActiveViewName(viewName);
    }
}