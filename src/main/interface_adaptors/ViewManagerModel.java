package interface_adaptors;

import view.LoginView;
import app.*;
import view.LogoutView;
import view.SignupView;
import view.TransactionView;

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
            this.createView(viewName);
        }
    }

    private void createView(String viewName) {
        switch (viewName) {
            case "home page":
                HomePageViewModel homePageViewModel = new HomePageViewModel();
                HomePageView homePageView = homePageUseCaseFactory.create(this, homePageViewModel);
                views.put("home page", homePageView);
                currentView = homePageView;
                break;
            case "sign up":
                SignupViewModel signupViewModel = new SignupViewModel();
                SignupView signupView = SignupUseCaseFactory.create(this, signupViewModel);
                views.put("sign up", signupView);
                currentView = signupView;
                break;
            case "log in":
                LoginViewModel loginViewModel = new LoginViewModel();
                LoginView loginView = LoginUseCaseFactory.create(this, loginViewModel);
                views.put("log in", loginView);
                currentView = loginView;
                break;
//            case "transaction":
//                TransactionViewModel transactionViewModel = new TransactionViewModel();
//                TransactionView transactionView = TransactionUseCaseFactory.createTransactionView(this,
//                        transactionViewModel);
//                views.put("transaction", transactionView);
//                currentView = transactionView;
//                break;
            case "One-Time Transaction":
                TransactionViewModel oneTimeTransactionViewModel = new TransactionViewModel("One-Time Transaction");
                TransactionView oneTimeTransactionView = TransactionUseCaseFactory.createTransactionView(this,
                        oneTimeTransactionViewModel);
                views.put("One-Time Transaction", oneTimeTransactionView);
                currentView = oneTimeTransactionView;
                break;
            case "Periodic Transaction":
                TransactionViewModel periodicTransactionViewModel = new TransactionViewModel("Periodic Transaction");
                TransactionView periodicTransactionView = TransactionUseCaseFactory.createTransactionView(this,
                        periodicTransactionViewModel);
                views.put("Periodic Transaction", periodicTransactionView);
                currentView = periodicTransactionView;
                break;
            case "log out":
                LogoutViewModel logoutViewModel = new LogoutViewModel();
                LogoutView logoutView = LogoutUseCaseFactory.create(this, logoutViewModel);
                views.put("log out", logoutView);
                currentView = logoutView;
                break;
        }
        if (currentView != null) {
            currentView.setVisible(true);
        }
        setActiveViewName(viewName);
    }
}