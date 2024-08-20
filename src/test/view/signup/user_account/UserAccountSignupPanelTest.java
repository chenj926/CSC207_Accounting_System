package view.signup.user_account;

import data_access.account.user_account.InMemoryUserAccountDataAccessObject;
import data_access.authentication.user_account.UserSignupDataAccessInterface;
import use_case.signup.user_account.UserAccountSignupOutputBoundary;
import use_case.signup.user_account.UserAccountSignupInteractor;
import interface_adaptors.ViewManagerModel;
import interface_adaptors.signup.user_account.UserAccountSignupController;
import interface_adaptors.signup.user_account.UserAccountSignupPresenter;
import interface_adaptors.signup.user_account.UserAccountSignupViewModel;
import entity.account.AccountFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UserAccountSignupPanelTest {

    private UserAccountSignupViewModel viewModel;
    private UserAccountSignupController controller;
    private ViewManagerModel viewManager;
    private UserAccountSignupPanel panel;

    @BeforeEach
    void setUp() {
        viewModel = new UserAccountSignupViewModel();
        viewManager = new ViewManagerModel(); // 0-argument constructor

        // Initialize the dependencies similar to shared account
        UserSignupDataAccessInterface userSignupDataAccess = new InMemoryUserAccountDataAccessObject(); // Available in your structure

        // Instantiate the presenter, which implements UserAccountSignupOutputBoundary
        UserAccountSignupOutputBoundary signupOutputBoundary = new UserAccountSignupPresenter(viewManager, viewModel); // Pass the required objects

        // Provide a basic implementation for AccountFactory
        AccountFactory accountFactory = new AccountFactory(); // Assuming you have or can create this

        // Create the interactor with the required dependencies
        UserAccountSignupInteractor interactor = new UserAccountSignupInteractor(
                userSignupDataAccess,
                signupOutputBoundary,
                accountFactory
        );

        // Create the controller with the interactor
        controller = new UserAccountSignupController(interactor);

        // Initialize the panel
        panel = new UserAccountSignupPanel(viewModel, controller, viewManager);
    }

    @Test
    void testUserAccountSignupPanelInitialization() {
        assertNotNull(panel);
        assertEquals("User Account Sign Up", ((JLabel) panel.getComponent(0)).getText()); // Check title
    }

    @Test
    void testSignupButtonActionListener() {
        JTextField usernameTextField = (JTextField) panel.getComponent(2);
        JPasswordField passwordField = (JPasswordField) panel.getComponent(4);
        JTextField idenficationField = (JTextField) panel.getComponent(6);

        // Set values for testing
        usernameTextField.setText("testUsername");
        passwordField.setText("testPassword");
        idenficationField.setText("testUserId");

        // Find the signup button
        JPanel buttonsPanel = (JPanel) panel.getComponent(7);
        JButton signupButton = (JButton) buttonsPanel.getComponent(0);
        signupButton.doClick();

        // Verify if the fields are get correctly
        assertEquals("testUsername", usernameTextField.getText());
        assertEquals("testPassword", new String(passwordField.getPassword()));
        assertEquals("testUserId", idenficationField.getText());
    }

    @Test
    void testCancelButtonActionListener() {
        JPanel buttonsPanel = (JPanel) panel.getComponent(7);
        JButton cancelButton = (JButton) buttonsPanel.getComponent(1);
        cancelButton.doClick();

        assertEquals("home page", viewManager.getActiveViewName());
    }
}

