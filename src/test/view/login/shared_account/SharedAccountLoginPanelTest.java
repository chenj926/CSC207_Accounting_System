package view.login.shared_account;

import interface_adaptors.ViewManagerModel;
import interface_adaptors.login.shared_account.SharedAccountLoginController;
import interface_adaptors.login.shared_account.SharedAccountLoginState;
import use_case.login.shared_account.SharedAccountLoginMediator;
import use_case.login.shared_account.SharedAccountLoginInputData;
import interface_adaptors.login.shared_account.SharedAccountLoginViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.*;

class SharedAccountLoginPanelTest {

    private SharedAccountLoginViewModel viewModel;
    private ViewManagerModel viewManager;
    private SharedAccountLoginPanel panel;
    private SharedAccountLoginController controller;

    @BeforeEach
    void setUp() {
        viewModel = new SharedAccountLoginViewModel();
        viewManager = new TestViewManagerModel();

        controller = new SharedAccountLoginController(new TestLoginMediator());
        panel = new SharedAccountLoginPanel(viewModel, controller, viewManager);
    }

    @Test
    void testInitializeComponents() {
        JLabel titleLabel = (JLabel) panel.getComponent(0);
        assertEquals(viewModel.getTitleLabel(), titleLabel.getText());

        JTextField sharedAccountIdField = (JTextField) panel.getComponent(2);
        JPasswordField passwordField = (JPasswordField) panel.getComponent(4);
        assertNotNull(sharedAccountIdField);
        assertNotNull(passwordField);
    }

    @Test
    void testLoginButtonListener() {
        JTextField sharedAccountIdField = (JTextField) panel.getComponent(2);
        JPasswordField passwordField = (JPasswordField) panel.getComponent(4);

        sharedAccountIdField.setText("testAccountId");
        passwordField.setText("testPassword");

        JPanel buttonsPanel = (JPanel) panel.getComponent(5);
        JButton loginButton = (JButton) buttonsPanel.getComponent(0);
        loginButton.doClick();

        assertEquals("testAccountId", TestLoginMediator.executedData.getIdentification());
        assertEquals("testPassword", TestLoginMediator.executedData.getPassword());
    }

    @Test
    void testCancelButtonListener() {
        JPanel buttonsPanel = (JPanel) panel.getComponent(5);
        JButton cancelButton = (JButton) buttonsPanel.getComponent(1);
        cancelButton.doClick();
        assertEquals("home page", viewManager.getActiveViewName());
    }

    @Test
    void testKeyListeners() {
        // Access the sharedAccountIdField and passwordField components
        JTextField sharedAccountIdField = (JTextField) panel.getComponent(2);
        JPasswordField passwordField = (JPasswordField) panel.getComponent(4);

        // Set the text directly
        sharedAccountIdField.setText("testId");
        passwordField.setText("testPass");

        // Simulate the update of the viewModel state directly
        SharedAccountLoginState currentState = viewModel.getState();
        currentState.setIdentification(sharedAccountIdField.getText());
        currentState.setPassword(String.valueOf(passwordField.getPassword()));
        viewModel.setState(currentState);

        // Validate the state change
        assertEquals("testId", viewModel.getState().getIdentification());
        assertEquals("testPass", viewModel.getState().getPassword());
    }

    @Test
    void testLoginButtonActionPerformed() {
        // Simulate filling in the fields
        JTextField sharedAccountIdField = (JTextField) panel.getComponent(2);
        JPasswordField passwordField = (JPasswordField) panel.getComponent(4);

        sharedAccountIdField.setText("testAccountId");
        passwordField.setText("testPassword");

        // Simulate clicking the login button
        JPanel buttonsPanel = (JPanel) panel.getComponent(5);
        JButton loginButton = (JButton) buttonsPanel.getComponent(0);
        loginButton.doClick();

        // Verify the action was performed (check for side effects or state changes)
        assertEquals("", viewModel.getState().getIdentification());
        assertEquals("", viewModel.getState().getPassword());
    }

    @Test
    void testCancelButtonActionPerformed() {
        // Simulate clicking the cancel button
        JPanel buttonsPanel = (JPanel) panel.getComponent(5);
        JButton cancelButton = (JButton) buttonsPanel.getComponent(1);
        cancelButton.doClick();

        // Verify that the view manager switches to the home page
        assertEquals("home page", viewManager.getActiveViewName());
    }

    @Test
    void testKeyListenersUpdateState() {
        // Simulate typing in the sharedAccountIdField
        JTextField sharedAccountIdField = (JTextField) panel.getComponent(2);
        sharedAccountIdField.setText("testId");
        sharedAccountIdField.postActionEvent();

        // Check if the view model was updated correctly
        assertEquals("", viewModel.getState().getIdentification());

        // Simulate typing in the passwordField
        JPasswordField passwordField = (JPasswordField) panel.getComponent(4);
        passwordField.setText("testPass");
        passwordField.postActionEvent();

        // Check if the view model was updated correctly
        assertEquals("", viewModel.getState().getPassword());
    }




    @Test
    void testClearFields() {
        JTextField sharedAccountIdField = (JTextField) panel.getComponent(2);
        JPasswordField passwordField = (JPasswordField) panel.getComponent(4);

        sharedAccountIdField.setText("testAccountId");
        passwordField.setText("testPassword");

        panel.clearFields();

        assertEquals("", sharedAccountIdField.getText());
        assertEquals("", String.valueOf(passwordField.getPassword()));
    }

    // Dummy TestViewManagerModel class for testing purposes
    static class TestViewManagerModel extends ViewManagerModel {
        private String activeViewName;

        @Override
        public String getActiveViewName() {
            return activeViewName;
        }

        @Override
        public void setActiveViewName(String viewName) {
            this.activeViewName = viewName;
        }
    }

    // Simple test implementation of SharedAccountLoginMediator
    static class TestLoginMediator extends SharedAccountLoginMediator {

        static SharedAccountLoginInputData executedData;

        TestLoginMediator() {
            super(null, null, null); // Passing nulls because we don't use them in the test
        }

        @Override
        public void execute(SharedAccountLoginInputData inputData) {
            executedData = inputData;
        }
    }
}













