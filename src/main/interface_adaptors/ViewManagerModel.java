package interface_adaptors;

import app.authentication.LoginUseCaseFactory;
//import app.authentication.LogoutUseCaseFactory;
import app.authentication.SignupUseCaseFactory;
import app.home_page.HomePageUseCaseFactory;
import app.home_page.HomepageTwoUseCaseFactory;
import app.transaction.OneTimeTransactionUseCaseFactory;
import app.transaction.PeriodicTransactionUseCaseFactory;
//import app.transaction.TransactionUseCaseFactory;
import app.FinancialReport.FinancialReportUseCaseFactory;
import app.transaction.SharedAccountOneTimeTransactionUseCaseFactory;
import app.transaction.SharedAccountPeriodicTransactionUseCaseFactory;
import interface_adaptors.financial_report.UserAccountFinancialReportViewModel;
import interface_adaptors.homepage.SharedAccountHomepageTwoViewModel;
import interface_adaptors.homepage.UserAccountHomepageTwoViewModel;
import interface_adaptors.login.UserAccountLoginViewModel;
import interface_adaptors.login.SharedAccountLoginViewModel; // Import the SharedAccountLoginViewModel
//import interface_adaptors.logout.LogoutViewModel;
import interface_adaptors.signup.UserAccountSignupViewModel;
import interface_adaptors.signup.SharedAccountSignupViewModel;
import interface_adaptors.transaction.one_time.UserAccountOneTimeTransactionViewModel;
import interface_adaptors.transaction.one_time.SharedAccountOneTimeTransactionViewModel;
import interface_adaptors.transaction.periodic.UserAccountPeriodicTransactionViewModel;
import interface_adaptors.transaction.periodic.SharedAccountPeriodicTransactionViewModel;
import view.financial_report.FinancialReportView;
import view.home_page.HomePageView;
import view.home_page.HomepageTwoView;
import view.home_page.SharedAccountHomepageTwoView;
import view.login.LoginView;
import view.login.SharedAccountLoginView; // Import the SharedAccountLoginView
//import view.logout.LogoutView;
import view.signup.SignupView;
import view.signup.SharedAccountSignupView;
//import view.transaction.TransactionView;
import view.transaction.one_time.OneTimeTransactionView;
import view.transaction.one_time.SharedAccountOneTimeTransactionView;
import view.transaction.periodic.PeriodicTransactionView;
import view.transaction.periodic.SharedAccountPeriodicTransactionView;
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
//    private String sharedAccountId;
    private String activeViewName;
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private JFrame currentView;
    private final Map<String, JFrame> views;
    private final Map<String, ViewModel> viewModels;

    /**
     * Constructs a ViewManagerModel object with no initial views.
     */
    public ViewManagerModel() {
        this.views = new HashMap<>();
        this.viewModels = new HashMap<>();
        this.initViewModels();
    }

    // 2种方式，一种是set好viewModel， 然后新创建的时候，viewModel里就已近有了id/shareId
    //还有一种就是一个个加
    private void initViewModels() {
        // init every view in the beginning
        HomePageViewModel homePageViewModel = new HomePageViewModel();
        this.viewModels.put("home page", homePageViewModel);

        UserAccountSignupViewModel userAccountSignupViewModel = new UserAccountSignupViewModel();
        this.viewModels.put("sign up", userAccountSignupViewModel);

        SharedAccountSignupViewModel sharedSignupViewModel = new SharedAccountSignupViewModel();
        this.viewModels.put("shared account sign up", sharedSignupViewModel);

        UserAccountLoginViewModel loginViewModel =  new UserAccountLoginViewModel();
        this.viewModels.put("log in", loginViewModel);

        SharedAccountLoginViewModel sharedAccountLoginViewModel = new SharedAccountLoginViewModel();
        this.viewModels.put("shared account log in", sharedAccountLoginViewModel);

        UserAccountHomepageTwoViewModel userAccountHomepageTwoViewModel = new UserAccountHomepageTwoViewModel();
        this.viewModels.put("Homepage Two", userAccountHomepageTwoViewModel);

        SharedAccountHomepageTwoViewModel sharedAccounthomepageTwoViewModel = new SharedAccountHomepageTwoViewModel();
        this.viewModels.put("Share Account Homepage Two", sharedAccounthomepageTwoViewModel);

        UserAccountOneTimeTransactionViewModel userAccountOneTimeTransactionViewModel = new UserAccountOneTimeTransactionViewModel();
        this.viewModels.put("One Time Transaction", userAccountOneTimeTransactionViewModel);

        SharedAccountOneTimeTransactionViewModel sharedAccountOneTimeTransactionViewModel = new SharedAccountOneTimeTransactionViewModel();
        this.viewModels.put("Shared Account One Time Transaction", sharedAccountOneTimeTransactionViewModel);

        UserAccountPeriodicTransactionViewModel userAccountPeriodicTransactionViewModel = new UserAccountPeriodicTransactionViewModel();
        this.viewModels.put("Periodic Transaction", userAccountPeriodicTransactionViewModel);

        SharedAccountPeriodicTransactionViewModel sharedAccountperiodicTransactionViewModel = new SharedAccountPeriodicTransactionViewModel();
        this.viewModels.put("Shared Account Periodic Transaction", sharedAccountperiodicTransactionViewModel);

        UserAccountFinancialReportViewModel userAccountFinancialReportViewModel = new UserAccountFinancialReportViewModel();
        this.viewModels.put("Financial Report", userAccountFinancialReportViewModel);
    }

    public <V extends ViewModel> void updateViewModel(String viewModelName, V viewModel) {
        if (this.viewModels.containsKey(viewModelName)) {
            this.viewModels.put(viewModelName, viewModel);
        } else {
            throw new IllegalArgumentException("No ViewModel found with the name: " + viewModelName);
        }
    }

    /**
     * Gets the name of the active view.
     *
     * @return the name of the active view
     */
    public String getActiveViewName() {
        return activeViewName;
    }

    public ViewModel getViewModel(String viewModelName) {
        return this.viewModels.get(viewModelName);
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

//    public String getSharedAccountId() {
//        return this.sharedAccountId;
//    }

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

//    public void setSharedAccountId(String sharedAccountId) {
//        this.sharedAccountId = sharedAccountId;
//    }

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
                HomePageViewModel homePageViewModel = (HomePageViewModel) this.viewModels.get("home page");
                HomePageView homePageView = HomePageUseCaseFactory.create(this, homePageViewModel);
                views.put("home page", homePageView);
                currentView = homePageView;
                break;
            case "sign up":
                UserAccountSignupViewModel userAccountSignupViewModel = (UserAccountSignupViewModel) this.viewModels.get("sign up");
                SignupView signupView = SignupUseCaseFactory.create(this, userAccountSignupViewModel);
                views.put("sign up", signupView);
                currentView = signupView;
                break;
            case "shared account sign up":
                SharedAccountSignupViewModel sharedSignupViewModel =
                        (SharedAccountSignupViewModel) this.viewModels.get("shared account sign up");
                SharedAccountSignupView sharedSignupView = SignupUseCaseFactory.createSharedAccount(this,
                        sharedSignupViewModel);
                views.put("shared account sign up", sharedSignupView);
                currentView = sharedSignupView;
                break;
            case "log in":
                UserAccountLoginViewModel loginViewModel =  (UserAccountLoginViewModel) this.viewModels.get("log in");
                LoginView loginView = LoginUseCaseFactory.create(this, loginViewModel);
                views.put("log in", loginView);
                currentView = loginView;
                break;
            case "shared account log in":
                SharedAccountLoginViewModel sharedAccountLoginViewModel =
                        (SharedAccountLoginViewModel) this.viewModels.get("shared account log in");
                SharedAccountLoginView sharedAccountLoginView = LoginUseCaseFactory.create(this,
                        sharedAccountLoginViewModel);
                views.put("shared account log in", sharedAccountLoginView);
                currentView = sharedAccountLoginView;
                break;
            case "Homepage Two":
                UserAccountHomepageTwoViewModel userAccountHomepageTwoViewModel = (UserAccountHomepageTwoViewModel) this.viewModels.get("Homepage Two");
                HomepageTwoView homepageTwoView = HomepageTwoUseCaseFactory.create(this, userAccountHomepageTwoViewModel);
                views.put("Homepage Two", homepageTwoView);
                currentView = homepageTwoView;
            case "Shared Account Homepage Two":
                SharedAccountHomepageTwoViewModel sharedAccountHomepageTwoViewModel = (SharedAccountHomepageTwoViewModel) this.viewModels.get("Shared Account Homepage Two");
                SharedAccountHomepageTwoView sharedAccountHomepageTwoView = HomepageTwoUseCaseFactory.create(this, sharedAccountHomepageTwoViewModel);
                views.put("Shared Account Homepage Two", sharedAccountHomepageTwoView);
                currentView = sharedAccountHomepageTwoView;
            case "One Time Transaction":
                UserAccountOneTimeTransactionViewModel userAccountOneTimeTransactionViewModel =
                        (UserAccountOneTimeTransactionViewModel) this.viewModels.get("One Time Transaction");
                OneTimeTransactionView oneTimeTransactionView = OneTimeTransactionUseCaseFactory.create(
                        this, userAccountOneTimeTransactionViewModel);
                views.put("One Time Transaction", oneTimeTransactionView);
                currentView = oneTimeTransactionView;
                break;
            case "Shared Account One Time Transaction":
                SharedAccountOneTimeTransactionViewModel sharedAccountoneTimeTransactionViewModel =
                        (SharedAccountOneTimeTransactionViewModel) this.viewModels.get("Shared Account One Time Transaction");
                SharedAccountOneTimeTransactionView sharedAccountoneTimeTransactionView = SharedAccountOneTimeTransactionUseCaseFactory.create(
                        this, sharedAccountoneTimeTransactionViewModel);
                views.put("One Time Transaction", sharedAccountoneTimeTransactionView);
                currentView = sharedAccountoneTimeTransactionView;
                break;
            case "Periodic Transaction":
                UserAccountPeriodicTransactionViewModel userAccountPeriodicTransactionViewModel =
                        (UserAccountPeriodicTransactionViewModel) this.viewModels.get("Periodic Transaction");
                PeriodicTransactionView periodicTransactionView = PeriodicTransactionUseCaseFactory.create(
                        this, userAccountPeriodicTransactionViewModel);
                views.put("Periodic Transaction", periodicTransactionView);
                currentView = periodicTransactionView;
                break;
            case "Shared Account Periodic Transaction":
                SharedAccountPeriodicTransactionViewModel sharedAccountperiodicTransactionViewModel =
                        (SharedAccountPeriodicTransactionViewModel) this.viewModels.get("Periodic Transaction");
                SharedAccountPeriodicTransactionView sharedAccountperiodicTransactionView = SharedAccountPeriodicTransactionUseCaseFactory.create(
                        this, sharedAccountperiodicTransactionViewModel);
                views.put("Shared Account Periodic Transaction", sharedAccountperiodicTransactionView);
                currentView = sharedAccountperiodicTransactionView;
                break;
            case "Financial Report":
                UserAccountFinancialReportViewModel userAccountFinancialReportViewModel = (UserAccountFinancialReportViewModel) this.viewModels.get("Financial Report");
                FinancialReportView financialReportView = FinancialReportUseCaseFactory.create(this,
                        userAccountFinancialReportViewModel);
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
