package interface_adaptors;

import view.*;
import app.*;

import javax.swing.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.*;

/**
 * The ViewManagerModel class manages the views in the application.
 * It keeps track of the current view and handles the switching between different views.
 *
 * @author Xile
 * @author Rita
 * @author Eric
 * @author Dana
 */
public class ViewManagerModel {

    private String userId;
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