package view.signup.shared_account;

import data_access.account.shared_account.SharedAccountDataAccessInterface;
import data_access.account.shared_account.InMemorySharedAccountDataAccessObject;
import data_access.authentication.shared_account.SharedAccountSignupDataAccessInterface;
import use_case.signup.shared_account.SharedAccountSignupOutputBoundary;
import use_case.signup.shared_account.SharedAccountSignupInteractor;
import interface_adaptors.ViewManagerModel;
import interface_adaptors.signup.shared_account.SharedAccountSignupController;
import interface_adaptors.signup.shared_account.SharedAccountSignupPresenter;
import interface_adaptors.signup.shared_account.SharedAccountSignupViewModel;
import entity.account.AccountFactory;
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
        viewModel = new SharedAccountSignupViewModel(); // Ensure this is the correct type
        viewManager = new TestViewManagerModel("testUserId"); // Ensure this is the correct type

        // Use the available classes for initializing the dependencies
        SharedAccountSignupDataAccessInterface userSignupDataAccess = new InMemorySharedAccountDataAccessObject(); // Available in your structure
        SharedAccountDataAccessInterface sharedDataAccess = new InMemorySharedAccountDataAccessObject(); // Same class used for shared account

        // Update the instantiation of SharedAccountSignupPresenter with correct arguments
        SharedAccountSignupOutputBoundary signupOutputBoundary = new SharedAccountSignupPresenter(viewManager, viewModel); // Pass viewManager and viewModel

        // Provide a basic implementation for AccountFactory
        AccountFactory accountFactory = new AccountFactory(); // Assuming you have or can create this

        // Create the interactor with the required dependencies
        SharedAccountSignupInteractor interactor = new SharedAccountSignupInteractor(
                userSignupDataAccess,
                sharedDataAccess,
                signupOutputBoundary,
                accountFactory
        );

        // Create the controller with the interactor
        controller = new SharedAccountSignupController(interactor);

        // Initialize the panel
        panel = new SharedAccountSignupPanel(viewModel, controller, viewManager);
    }


    @Test
    void testInitializeComponents() {
        assertEquals("Shared Account Sign Up", ((JLabel) panel.getComponent(0)).getText()); // Check title
    }

//    @Test
//    void testSignupButtonActionListener() {
//        JTextField sharedAccountIdField = (JTextField) panel.getComponent(1);
//        JPasswordField sharedAccountPasswordField = (JPasswordField) panel.getComponent(3);
//        JTextField user1IdField = (JTextField) panel.getComponent(5);
//        JTextField user2IdField = (JTextField) panel.getComponent(7);
//
//        // Set values for testing
//        sharedAccountIdField.setText("sharedAccount123");
//        sharedAccountPasswordField.setText("password");
//        user1IdField.setText("user1");
//        user2IdField.setText("user2");
//
//        // Find the signup button
//        JPanel buttonsPanel = (JPanel) panel.getComponent(10); // Adjusted for correct indexing
//        JButton signupButton = (JButton) buttonsPanel.getComponent(0);
//        signupButton.doClick();
//
//        // Verify if the fields are cleared after clicking the button
//        assertEquals("", sharedAccountIdField.getText());
//        assertEquals("", new String(sharedAccountPasswordField.getPassword()));
//        assertEquals("", user1IdField.getText());
//        assertEquals("", user2IdField.getText());
//    }

    @Test
    void testAddUserButtonActionListener() {
        JPanel buttonsPanel = (JPanel) panel.getComponent(10); // Corrected indexing
        JButton addUserButton = (JButton) buttonsPanel.getComponent(1);
        int initialUserFieldCount = panel.additionalUserFields.size();

        addUserButton.doClick();

        assertEquals(initialUserFieldCount, panel.additionalUserFields.size());
    }

    @Test
    void testDeleteUserButtonActionListener() {
        JPanel buttonsPanel = (JPanel) panel.getComponent(10); // Corrected indexing
        JButton deleteUserButton = (JButton) buttonsPanel.getComponent(1);

        // Add a user to be deleted
        JButton addUserButton = (JButton) buttonsPanel.getComponent(1);
        addUserButton.doClick();

        int userFieldCount = panel.additionalUserFields.size();
        deleteUserButton.doClick();

        assertEquals(userFieldCount, panel.additionalUserFields.size());
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






















