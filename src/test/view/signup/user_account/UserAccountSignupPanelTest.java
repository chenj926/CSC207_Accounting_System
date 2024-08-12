package view.signup.user_account;

import interface_adaptors.ViewManagerModel;
import interface_adaptors.signup.shared_account.SharedAccountSignupController;
import interface_adaptors.signup.user_account.UserAccountSignupController;
import interface_adaptors.signup.user_account.UserAccountSignupViewModel;
import use_case.signup.user_account.UserAccountSignupInputData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UserAccountSignupPanelTest {

    private UserAccountSignupViewModel viewModel;
    private ViewManagerModel viewManager;
    private UserAccountSignupController controller;
    private UserAccountSignupPanel panel;

    @BeforeEach
    void setUp() {
        viewModel = new UserAccountSignupViewModel();
        viewManager = new ViewManagerModel(); // 0-argument constructor
        controller = new SharedAccountSignupController();
        panel = new UserAccountSignupPanel(viewModel, controller, viewManager);
    }

    @Test
    void testUserAccountSignupPanelInitialization() {
        assertNotNull(panel);
        assertEquals("User Account Signup", ((JLabel) panel.getComponent(0)).getText()); // Check title
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
        JPanel buttonsPanel = (JPanel) panel.getComponent(8);
        JButton signupButton = (JButton) buttonsPanel.getComponent(0);
        signupButton.doClick();

        // Verify if the fields are cleared after clicking the button
        assertEquals("", usernameTextField.getText());
        assertEquals("", new String(passwordField.getPassword()));
        assertEquals("", idenficationField.getText());
    }

    @Test
    void testCancelButtonActionListener() {
        JPanel buttonsPanel = (JPanel) panel.getComponent(8);
        JButton cancelButton = (JButton) buttonsPanel.getComponent(1);
        cancelButton.doClick();

        assertEquals("home page", viewManager.getActiveViewName());
    }
}

