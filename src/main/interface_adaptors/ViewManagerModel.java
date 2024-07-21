package interface_adaptors;

import view.*;
import app.*;

import javax.swing.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.*;

public class ViewManagerModel {

    private String userId;
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
    public String getUserId() {
        return this.userId;
    }

    public void setActiveViewName(String activeViewName) {
        String oldActiveViewName = this.activeViewName;
        this.activeViewName = activeViewName;
        support.firePropertyChange("activeViewName", oldActiveViewName, this.activeViewName);
    }
    public void setUserId(String id) {
        this.userId = id;
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
        this.setActiveViewName(viewName);
    }

    private void createView(String viewName) {
        switch (viewName) {
            case "home page":
                HomePageViewModel homePageViewModel = new HomePageViewModel();
                HomePageView homePageView = HomePageUseCaseFactory.create(this, homePageViewModel);
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
            case "Transaction":
                TransactionViewModel transactionViewModel = new TransactionViewModel();
                TransactionView transactionView = TransactionUseCaseFactory.createTransactionView(this,
                        transactionViewModel);
                views.put("Transaction", transactionView);
                currentView = transactionView;
                break;
            case "One Time Transaction":
                OneTimeTransactionViewModel oneTimeTransactionViewModel = new OneTimeTransactionViewModel();
                OneTimeTransactionView oneTimeTransactionView = OneTimeTransactionUseCaseFactory.create(this,
                        oneTimeTransactionViewModel);
                views.put("One Time Transaction", oneTimeTransactionView);
                currentView = oneTimeTransactionView;
                break;
            case "Periodic Transaction":
                PeriodicTransactionViewModel periodicTransactionViewModel = new PeriodicTransactionViewModel();
                PeriodicTransactionView periodicTransactionView = PeriodicTransactionUseCaseFactory.create(this,
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