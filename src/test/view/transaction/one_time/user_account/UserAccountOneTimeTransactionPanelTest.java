package view.transaction.one_time.user_account;

import interface_adaptors.ViewManagerModel;
import interface_adaptors.transaction.one_time.user_account.UserAccountOneTimeTransactionController;
import interface_adaptors.transaction.one_time.user_account.UserOneTimeTransactionViewModel;
import use_case.transaction.one_time.user_account.UserAccountOneTimeTransactionInputBoundary;
import use_case.transaction.one_time.user_account.UserAccountOneTimeTransactionInputData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UserAccountOneTimeTransactionPanelTest {

    private UserOneTimeTransactionViewModel viewModel;
    private ViewManagerModel viewManager;
    private UserAccountOneTimeTransactionController controller;
    private UserAccountOneTimeTransactionPanel panel;

    @BeforeEach
    void setUp() {
        viewModel = new UserOneTimeTransactionViewModel();
        viewManager = new ViewManagerModel();

        // Create a dummy implementation of UserAccountOneTimeTransactionInputBoundary
        UserAccountOneTimeTransactionInputBoundary dummyInteractor = new UserAccountOneTimeTransactionInputBoundary() {
            @Override
            public void execute(UserAccountOneTimeTransactionInputData inputData) {
                // Dummy implementation: Do nothing
            }
        };

        controller = new UserAccountOneTimeTransactionController(dummyInteractor, viewModel);
        panel = new UserAccountOneTimeTransactionPanel(viewModel, controller, viewManager);
    }

    @Test
    void testUserAccountOneTimeTransactionPanelInitialization() {
        assertNotNull(panel);
        assertEquals("One Time Transaction", ((JLabel) panel.getComponent(0)).getText()); // Check title
    }

    @Test
    void testSubmitButtonActionListener() {
        JTextField amountField = (JTextField) panel.getComponent(2);
        JTextField dateField = (JTextField) panel.getComponent(4);
        JTextField descriptionField = (JTextField) panel.getComponent(6);
        JComboBox<String> categoryButton = (JComboBox<String>) panel.getComponent(8);

        // Set values for testing
        amountField.setText("100.00");
        dateField.setText("2024-08-12");
        descriptionField.setText("Lunch with team");
        categoryButton.setSelectedItem("Food");

        // Find the submit button
        JPanel buttonsPanel = (JPanel) panel.getComponent(9);
        JButton submitButton = (JButton) buttonsPanel.getComponent(0);
        submitButton.doClick();

        // No assertion here, since it's just triggering the action
    }

    @Test
    void testCancelButtonActionListener() {
        JPanel buttonsPanel = (JPanel) panel.getComponent(9);
        JButton cancelButton = (JButton) buttonsPanel.getComponent(1);
        cancelButton.doClick();

        assertEquals("Homepage Two", viewManager.getActiveViewName());
    }

    @Test
    void testClearFields() {
        JTextField amountField = (JTextField) panel.getComponent(2);
        JTextField dateField = (JTextField) panel.getComponent(4);
        JTextField descriptionField = (JTextField) panel.getComponent(6);
        JComboBox<String> categoryButton = (JComboBox<String>) panel.getComponent(8);

        // Set initial values
        amountField.setText("100.00");
        dateField.setText("2024-08-12");
        descriptionField.setText("Lunch with team");
        categoryButton.setSelectedItem("Food");

        // Clear fields
        panel.clearFields();

        // Verify fields are cleared
        assertEquals("", amountField.getText());
        assertEquals("", dateField.getText());
        assertEquals("", descriptionField.getText());
//        assertEquals(0, categoryButton.getSelectedIndex());  // Assuming the first category is the default
    }
}

