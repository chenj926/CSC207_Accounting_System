package view.transaction.periodic.user_account;

import interface_adaptors.ViewManagerModel;
import interface_adaptors.transaction.periodic.user_account.UserAccountPeriodicTransactionController;
import interface_adaptors.transaction.periodic.user_account.UserAccountPeriodicTransactionViewModel;
import use_case.transaction.periodic.user_account.UserAccountPeriodicTransactionInputBoundary;
import use_case.transaction.periodic.user_account.UserAccountPeriodicTransactionInputData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UserAccountPeriodicTransactionPanelTest {

    private UserAccountPeriodicTransactionViewModel viewModel;
    private ViewManagerModel viewManager;
    private UserAccountPeriodicTransactionController controller;
    private UserAccountPeriodicTransactionPanel panel;

    @BeforeEach
    void setUp() {
        viewModel = new UserAccountPeriodicTransactionViewModel();
        viewManager = new ViewManagerModel();

        // Create a dummy implementation of UserAccountPeriodicTransactionInputBoundary
        UserAccountPeriodicTransactionInputBoundary dummyInteractor = new UserAccountPeriodicTransactionInputBoundary() {
            @Override
            public void execute(UserAccountPeriodicTransactionInputData inputData) {
                // Dummy implementation: Do nothing
            }
        };

        controller = new UserAccountPeriodicTransactionController(dummyInteractor, viewModel);
        panel = new UserAccountPeriodicTransactionPanel(viewModel, controller, viewManager);
    }

    @Test
    void testUserAccountPeriodicTransactionPanelInitialization() {
        assertNotNull(panel);
        assertEquals("Periodic Transaction", ((JLabel) panel.getComponent(0)).getText()); // Check title
    }

    @Test
    void testSubmitButtonActionListener() {
        JTextField amountField = (JTextField) panel.getComponent(2);
        JTextField startDateField = (JTextField) panel.getComponent(4);
        JTextField endDateField = (JTextField) panel.getComponent(6);
        JTextField descriptionField = (JTextField) panel.getComponent(8);
        JComboBox<String> periodComb = (JComboBox<String>) panel.getComponent(10);
        JComboBox<String> categoryComb = (JComboBox<String>) panel.getComponent(12);

        // Set values for testing
        amountField.setText("300.00");
        startDateField.setText("2024-09-01");
        endDateField.setText("2025-09-01");
        descriptionField.setText("Rent payment");
        periodComb.setSelectedItem("month");
        categoryComb.setSelectedItem("Home");

        // Find the submit button
        JPanel buttonsPanel = (JPanel) panel.getComponent(13);
        JButton submitButton = (JButton) buttonsPanel.getComponent(0);
        submitButton.doClick();
    }

    @Test
    void testCancelButtonActionListener() {
        JPanel buttonsPanel = (JPanel) panel.getComponent(13);
        JButton cancelButton = (JButton) buttonsPanel.getComponent(1);
        cancelButton.doClick();

        assertEquals("Homepage Two", viewManager.getActiveViewName());
    }

    @Test
    void testClearFields() {
        JTextField amountField = (JTextField) panel.getComponent(2);
        JTextField startDateField = (JTextField) panel.getComponent(4);
        JTextField endDateField = (JTextField) panel.getComponent(6);
        JTextField descriptionField = (JTextField) panel.getComponent(8);
        JComboBox<String> periodComb = (JComboBox<String>) panel.getComponent(10);
        JComboBox<String> categoryComb = (JComboBox<String>) panel.getComponent(12);

        // Set initial values
        amountField.setText("300.00");
        startDateField.setText("2024-09-01");
        endDateField.setText("2025-09-01");
        descriptionField.setText("Rent payment");
        periodComb.setSelectedItem("month");
        categoryComb.setSelectedItem("Home");

        // Clear fields
        panel.clearFields();

        // Verify fields are cleared
        assertEquals("", amountField.getText());
        assertEquals("", startDateField.getText());
        assertEquals("", endDateField.getText());
        assertEquals("", descriptionField.getText());
    }
}

