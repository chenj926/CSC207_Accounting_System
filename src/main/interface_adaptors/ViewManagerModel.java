package interface_adaptors;

import app.authentication.LoginUseCaseFactory;
import app.authentication.LogoutUseCaseFactory;
import app.authentication.SignupUseCaseFactory;
import app.home_page.HomePageUseCaseFactory;
import app.home_page.HomepageTwoUseCaseFactory;
import app.transaction.OneTimeTransactionUseCaseFactory;
import app.transaction.PeriodicTransactionUseCaseFactory;
//import app.transaction.TransactionUseCaseFactory;
import app.FinancialReport.FinancialReportUseCaseFactory;
import interface_adaptors.financial_report.FinancialReportViewModel;
import interface_adaptors.homepage.HomepageTwoViewModel;
import interface_adaptors.login.LoginViewModel;
import interface_adaptors.logout.LogoutViewModel;
import interface_adaptors.signup.SignupViewModel;
import interface_adaptors.transaction.TransactionViewModel;
import interface_adaptors.transaction.one_time.OneTimeTransactionViewModel;
import interface_adaptors.transaction.periodic.PeriodicTransactionViewModel;
import view.financial_report.FinancialReportView;
import view.home_page.HomePageView;
import view.home_page.HomepageTwoView;
import view.login.LoginView;
import view.logout.LogoutView;
import view.signup.SignupView;
import view.transaction.one_time.OneTimeTransactionView;
import view.transaction.periodic.PeriodicTransactionView;
//import view.transaction.TransactionView;

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
    private String[] basicUserInfo;
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

    public String[] getBasicUserInfo() {
        return this.basicUserInfo;
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
     * @param userId the new user ID
     */
    public void setUserId(String userId) {
//        String oldUserId = this.userId;
        this.userId = userId;
//        support.firePropertyChange("userId", oldUserId, userId);
    }

    public void setBasicUserInfo(String[] basicUserInfo) {
        this.basicUserInfo = basicUserInfo;
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

    // Reset method to clear all state
    public void reset() {
        this.userId = null;
//        this.activeViewName = "home page"; // Reset to home page view
//        support.firePropertyChange("reset", null, null); // Notify listeners of reset
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
        // 让他加了（新的viewModel）好像也没用……非常无奈啊
        switch (viewName) {
            case "home page":
                HomePageViewModel homePageViewModel = new HomePageViewModel();
                HomePageView homePageView = HomePageUseCaseFactory.create(this, homePageViewModel);
                views.put("home page", homePageView);
                currentView = homePageView;
                break;
            case "sign up":
                SignupViewModel signupViewModel =  new SignupViewModel();
                SignupView signupView = SignupUseCaseFactory.create(this, signupViewModel);
                views.put("sign up", signupView);
                currentView = signupView;
                break;
            case "log in":
                LoginViewModel loginViewModel =  new LoginViewModel();
                LoginView loginView = LoginUseCaseFactory.create(this, loginViewModel);
                views.put("log in", loginView);
                currentView = loginView;
                break;
            case "Homepage Two":
                HomepageTwoViewModel homepageTwoViewModel = new HomepageTwoViewModel();
                HomepageTwoView homepageTwoView = HomepageTwoUseCaseFactory.create(this, homepageTwoViewModel);
                views.put("Homepage Two", homepageTwoView);
                currentView = homepageTwoView;
                break;
            case "One Time Transaction":
                OneTimeTransactionViewModel oneTimeTransactionViewModel = new OneTimeTransactionViewModel();
                OneTimeTransactionView oneTimeTransactionView = OneTimeTransactionUseCaseFactory.create(this, oneTimeTransactionViewModel);
                views.put("One Time Transaction", oneTimeTransactionView);
                System.out.println("manger: update once"); // debug
                currentView = oneTimeTransactionView;
                break;
            case "Periodic Transaction":
                PeriodicTransactionViewModel periodicTransactionViewModel = new PeriodicTransactionViewModel();
                PeriodicTransactionView periodicTransactionView = PeriodicTransactionUseCaseFactory.create(this, periodicTransactionViewModel);
                views.put("Periodic Transaction", periodicTransactionView);
                currentView = periodicTransactionView;
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