package interface_adaptors;

import app.authentication.LoginUseCaseFactory;
import app.authentication.LogoutUseCaseFactory;
import app.authentication.SignupUseCaseFactory;
import app.home_page.HomePageUseCaseFactory;
import app.transaction.*;
import app.FinancialReport.FinancialReportUseCaseFactory;
import interface_adaptors.FinancialReport.FinancialReportViewModel;
import interface_adaptors.login.LoginViewModel;
import interface_adaptors.login.SharedAccountLoginViewModel; // Import the SharedAccountLoginViewModel
import interface_adaptors.logout.LogoutViewModel;
import interface_adaptors.signup.SignupViewModel;
import interface_adaptors.signup.SharedAccountSignupViewModel;
import interface_adaptors.transaction.TransactionViewModel;
import interface_adaptors.transaction.SharedAccountTransactionViewModel;
import interface_adaptors.transaction.one_time.OneTimeTransactionViewModel;
import interface_adaptors.transaction.one_time.SharedAccountOneTimeTransactionViewModel;
import interface_adaptors.transaction.periodic.PeriodicTransactionViewModel;
import interface_adaptors.transaction.periodic.SharedAccountPeriodicTransactionViewModel;
import view.FinancialReport.FinancialReportView;
import view.home_page.HomePageView;
import view.login.LoginView;
import view.login.SharedAccountLoginView; // Import the SharedAccountLoginView
import view.logout.LogoutView;
import view.signup.SignupView;
import view.signup.SharedAccountSignupView;
import view.transaction.TransactionView;
import view.transaction.SharedAccountTransactionView;
import view.transaction.one_time.OneTimeTransactionView;
import view.transaction.one_time.SharedAccountOneTimeTransactionView;
import view.transaction.periodic.PeriodicTransactionView;
import view.transaction.periodic.SharedAccountPeriodicTransactionView;

import javax.swing.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.*;

/**
 * The ViewManagerModel class manages the views in the application.
 * It keeps track of the current view and handles the switching between different views.
 */
public class ViewManagerModel {

    private String userId;
    private String sharedAccountId;
    private String activeViewName;
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private JFrame currentView;
    private Map<String, JFrame> views;

    /**
     * Constructs a ViewManagerModel object with no initial views.
     */
    public ViewManagerModel() {
        this.views = new HashMap<>();
    }

    /**
     * Gets the name of the active view.
     *
     * @return the name of the active view
     */
    public String getActiveViewName() {
        return activeViewName;
    }

    /**
     * Gets the user ID.
     *
     * @return the user ID
     */
    public String getUserId() {
        return this.userId;
    }

    public String getSharedAccountId() {
        return this.sharedAccountId;
    }

    /**
     * Sets the name of the active view and notifies listeners of the change.
     *
     * @param activeViewName the name of the new active view
     */
    public void setActiveViewName(String activeViewName) {
        String oldActiveViewName = this.activeViewName;
        this.activeViewName = activeViewName;
        support.firePropertyChange("activeViewName", oldActiveViewName, this.activeViewName);
    }

    /**
     * Sets the user ID.
     *
     * @param id the new user ID
     */
    public void setUserId(String id) {
        this.userId = id;
    }

    public void setSharedAccountId(String sharedAccountId) {
        this.sharedAccountId = sharedAccountId;
    }

    /**
     * Adds a property change listener to the listener list.
     *
     * @param listener the PropertyChangeListener to be added
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Removes a property change listener from the listener list.
     *
     * @param listener the PropertyChangeListener to be removed
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

    /**
     * Changes the current view to the specified view.
     *
     * @param viewName the name of the view to change to
     */
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

    /**
     * Creates a new view based on the specified view name.
     *
     * @param viewName the name of the view to create
     */
    private void createView(String viewName) {
        switch (viewName) {
            case "home page":
                HomePageViewModel homePageViewModel = new HomePageViewModel();
                HomePageView homePageView = HomePageUseCaseFactory.create(this, homePageViewModel);
                views.put("home page", homePageView);
                currentView = homePageView;
                break;
            case "sign up":
                SignupViewModel signupViewModel = new SignupViewModel("sign up");
                SignupView signupView = SignupUseCaseFactory.create(this, signupViewModel);
                views.put("sign up", signupView);
                currentView = signupView;
                break;
            case "shared account sign up":
                SharedAccountSignupViewModel sharedSignupViewModel = new SharedAccountSignupViewModel("shared account sign up");
                SharedAccountSignupView sharedSignupView = SignupUseCaseFactory.createSharedAccount(this, sharedSignupViewModel);
                views.put("shared account sign up", sharedSignupView);
                currentView = sharedSignupView;
                break;
            case "log in":
                LoginViewModel loginViewModel = new LoginViewModel();
                LoginView loginView = LoginUseCaseFactory.create(this, loginViewModel);
                views.put("log in", loginView);
                currentView = loginView;
                break;
            case "shared account log in":
                SharedAccountLoginViewModel sharedAccountLoginViewModel = new SharedAccountLoginViewModel();
                SharedAccountLoginView sharedAccountLoginView = LoginUseCaseFactory.createSharedAccount(this, sharedAccountLoginViewModel);
                views.put("shared account log in", sharedAccountLoginView);
                currentView = sharedAccountLoginView;
                break;
            case "Transaction":
                TransactionViewModel transactionViewModel = new TransactionViewModel();
                TransactionView transactionView = TransactionUseCaseFactory.createTransactionView(this, transactionViewModel);
                views.put("Transaction", transactionView);
                currentView = transactionView;
                break;
            case "One Time Transaction":
                OneTimeTransactionViewModel oneTimeTransactionViewModel = new OneTimeTransactionViewModel();
                OneTimeTransactionView oneTimeTransactionView = OneTimeTransactionUseCaseFactory.create(this, oneTimeTransactionViewModel);
                views.put("One Time Transaction", oneTimeTransactionView);
                currentView = oneTimeTransactionView;
                break;
            case "Periodic Transaction":
                PeriodicTransactionViewModel periodicTransactionViewModel = new PeriodicTransactionViewModel();
                PeriodicTransactionView periodicTransactionView = PeriodicTransactionUseCaseFactory.create(this, periodicTransactionViewModel);
                views.put("Periodic Transaction", periodicTransactionView);
                currentView = periodicTransactionView;
                break;
            case "Shared Account Transaction":
                SharedAccountTransactionViewModel sharedAccountTransactionViewModel = new SharedAccountTransactionViewModel();
                SharedAccountTransactionView sharedAccountTransactionView = SharedAccountTransactionUseCaseFactory.createTransactionView(this, sharedAccountTransactionViewModel);
                views.put("Shared Account Transaction", sharedAccountTransactionView);
                currentView = sharedAccountTransactionView;
                break;
            case "Shared Account One Time Transaction":
                SharedAccountOneTimeTransactionViewModel sharedAccountOneTimeTransactionViewModel = new SharedAccountOneTimeTransactionViewModel();
                SharedAccountOneTimeTransactionView sharedAccountOneTimeTransactionView = SharedAccountOneTimeTransactionUseCaseFactory.create(this, sharedAccountOneTimeTransactionViewModel);
                views.put("Shared Account One Time Transaction", sharedAccountOneTimeTransactionView);
                currentView = sharedAccountOneTimeTransactionView;
                break;
            case "Shared Account Periodic Transaction":
                SharedAccountPeriodicTransactionViewModel sharedAccountPeriodicTransactionViewModel = new SharedAccountPeriodicTransactionViewModel();
                SharedAccountPeriodicTransactionView sharedAccountPeriodicTransactionView = SharedAccountPeriodicTransactionUseCaseFactory.create(this, sharedAccountPeriodicTransactionViewModel);
                views.put("Shared Account Periodic Transaction", sharedAccountPeriodicTransactionView);
                currentView = sharedAccountPeriodicTransactionView;
                break;
            case "log out":
                LogoutViewModel logoutViewModel = new LogoutViewModel();
                LogoutView logoutView = LogoutUseCaseFactory.create(this, logoutViewModel);
                views.put("log out", logoutView);
                currentView = logoutView;
                break;
            case "Financial Report":
                FinancialReportViewModel financialReportViewModel = new FinancialReportViewModel();
                FinancialReportView financialReportView = FinancialReportUseCaseFactory.create(this, financialReportViewModel);
                views.put("Financial Report", financialReportView);
                currentView = financialReportView;
                break;
        }
        if (currentView != null) {
            currentView.setVisible(true);
        }
        setActiveViewName(viewName);
    }
}
