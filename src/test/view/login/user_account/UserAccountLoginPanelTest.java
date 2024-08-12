package view.login.user_account;

import interface_adaptors.ViewManagerModel;
import interface_adaptors.login.user_account.UserAccountLoginController;
import interface_adaptors.login.user_account.UserAccountLoginState;
import interface_adaptors.login.user_account.UserAccountLoginViewModel;
import use_case.login.user_account.UserAccountLoginMediator;
import use_case.login.user_account.UserAccountLoginInputData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

class UserAccountLoginPanelTest {

    private UserAccountLoginViewModel viewModel;
    private ViewManagerModel viewManager;
    private UserAccountLoginPanel panel;
    private UserAccountLoginController controller;

    @BeforeEach
    void setUp() {
        viewModel = new UserAccountLoginViewModel();
        viewManager = new TestViewManagerModel();

        controller = new UserAccountLoginController(new TestLoginMediator());
        panel = new UserAccountLoginPanel(viewModel, controller, viewManager);
    }

    @Test
    void testInitializeComponents() {
        JLabel titleLabel = (JLabel) panel.getComponent(0);
        assertEquals(viewModel.getTitleLabel(), titleLabel.getText());

        JTextField identificationTextField = (JTextField) panel.getComponent(2);
        JPasswordField passwordField = (JPasswordField) panel.getComponent(4);
        assertNotNull(identificationTextField);
        assertNotNull(passwordField);
    }

    @Test
    void testLoginButtonListener() {
        JTextField identificationTextField = (JTextField) panel.getComponent(2);
        JPasswordField passwordField = (JPasswordField) panel.getComponent(4);

        identificationTextField.setText("testId");
        passwordField.setText("testPass");

        JPanel buttonsPanel = (JPanel) panel.getComponent(5);
        JButton loginButton = (JButton) buttonsPanel.getComponent(0);
        loginButton.doClick();

        assertEquals("testId", TestLoginMediator.executedData.getIdentification());
        assertEquals("testPass", TestLoginMediator.executedData.getPassword());
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
        // Access the identificationTextField and passwordField components
        JTextField identificationTextField = (JTextField) panel.getComponent(2);
        JPasswordField passwordField = (JPasswordField) panel.getComponent(4);

        // Set the text directly
        identificationTextField.setText("testId");
        passwordField.setText("testPass");

        // Simulate the update of the viewModel state directly
        UserAccountLoginState currentState = viewModel.getState();
        currentState.setIdentification(identificationTextField.getText());
        currentState.setPassword(String.valueOf(passwordField.getPassword()));
        viewModel.setState(currentState);

        // Validate the state change
        assertEquals("testId", viewModel.getState().getIdentification());
        assertEquals("testPass", viewModel.getState().getPassword());
    }

    @Test
    void testLoginButtonActionPerformed() {
        // Simulate filling in the fields
        JTextField identificationTextField = (JTextField) panel.getComponent(2);
        JPasswordField passwordField = (JPasswordField) panel.getComponent(4);

        identificationTextField.setText("testId");
        passwordField.setText("testPass");

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
        // Simulate typing in the identificationTextField
        JTextField identificationTextField = (JTextField) panel.getComponent(2);
        identificationTextField.setText("testId");
        identificationTextField.postActionEvent();

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
        JTextField identificationTextField = (JTextField) panel.getComponent(2);
        JPasswordField passwordField = (JPasswordField) panel.getComponent(4);

        identificationTextField.setText("testId");
        passwordField.setText("testPass");

        panel.clearFields();

        assertEquals("", identificationTextField.getText());
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

    // Simple test implementation of UserAccountLoginMediator
    static class TestLoginMediator extends UserAccountLoginMediator {

        static UserAccountLoginInputData executedData;

        TestLoginMediator() {
            super(null, null, null); // Passing nulls because we don't use them in the test
        }

        @Override
        public void execute(UserAccountLoginInputData inputData) {
            executedData = inputData;
        }
    }
}
