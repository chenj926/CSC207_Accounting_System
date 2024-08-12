package view.signup.shared_account;

import interface_adaptors.ViewManagerModel;
import interface_adaptors.signup.shared_account.SharedAccountSignupController;
import interface_adaptors.signup.shared_account.SharedAccountSignupViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SharedAccountSignupPanelTest {

    private SharedAccountSignupViewModel viewModel;
    private SharedAccountSignupController controller;
    private TestViewManagerModel viewManager;
    private SharedAccountSignupPanel panel;

    @BeforeEach
    void setUp() {
        viewModel = new SharedAccountSignupViewModel();
        viewManager = new TestViewManagerModel("testUserId");
        controller = new SharedAccountSignupController();
        panel = new SharedAccountSignupPanel(viewModel, controller, viewManager);
    }

    @Test
    void testInitializeComponents() {
        assertEquals("Shared Account Signup", ((JLabel) panel.getComponent(0)).getText()); // Check title
    }

    @Test
    void testSignupButtonActionListener() {
        JTextField sharedAccountIdField = (JTextField) panel.getComponent(1);
        JPasswordField sharedAccountPasswordField = (JPasswordField) panel.getComponent(3);
        JTextField user1IdField = (JTextField) panel.getComponent(5);
        JTextField user2IdField = (JTextField) panel.getComponent(7);

        // Set values for testing
        sharedAccountIdField.setText("sharedAccount123");
        sharedAccountPasswordField.setText("password");
        user1IdField.setText("user1");
        user2IdField.setText("user2");

        // Find the signup button
        JPanel buttonsPanel = (JPanel) panel.getComponent(10); // Adjusted for correct indexing
        JButton signupButton = (JButton) buttonsPanel.getComponent(0);
        signupButton.doClick();

        // Verify if the fields are cleared after clicking the button
        assertEquals("", sharedAccountIdField.getText());
        assertEquals("", new String(sharedAccountPasswordField.getPassword()));
        assertEquals("", user1IdField.getText());
        assertEquals("", user2IdField.getText());
    }

    @Test
    void testAddUserButtonActionListener() {
        JPanel buttonsPanel = (JPanel) panel.getComponent(10); // Corrected indexing
        JButton addUserButton = (JButton) buttonsPanel.getComponent(2);
        int initialUserFieldCount = panel.additionalUserFields.size();

        addUserButton.doClick();

        assertEquals(initialUserFieldCount + 1, panel.additionalUserFields.size());
    }

    @Test
    void testDeleteUserButtonActionListener() {
        JPanel buttonsPanel = (JPanel) panel.getComponent(10); // Corrected indexing
        JButton deleteUserButton = (JButton) buttonsPanel.getComponent(3);

        // Add a user to be deleted
        JButton addUserButton = (JButton) buttonsPanel.getComponent(2);
        addUserButton.doClick();

        int userFieldCount = panel.additionalUserFields.size();
        deleteUserButton.doClick();

        assertEquals(userFieldCount - 1, panel.additionalUserFields.size());
    }

    @Test
    void testCancelButtonActionListener() {
        JPanel buttonsPanel = (JPanel) panel.getComponent(10); // Corrected indexing
        JButton cancelButton = (JButton) buttonsPanel.getComponent(1);
        cancelButton.doClick();

        assertEquals("home page", viewManager.getActiveViewName());
    }

    static class TestViewManagerModel extends ViewManagerModel {
        private final String userId;

        public TestViewManagerModel(String userId) {
            this.userId = userId;
        }

        @Override
        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            // Provide a method to manually set the user ID in the test
        }
    }
}






















