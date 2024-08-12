package view.transaction.periodic.shared_account;

import interface_adaptors.ViewManagerModel;
import interface_adaptors.transaction.periodic.shared_account.SharedAccountPeriodicTransactionController;
import interface_adaptors.transaction.periodic.shared_account.SharedAccountPeriodicTransactionViewModel;
import use_case.transaction.periodic.shared_account.SharedAccountPeriodicTransactionInputBoundary;
import use_case.transaction.periodic.shared_account.SharedAccountPeriodicTransactionInputData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SharedAccountPeriodicTransactionPanelTest {

    private SharedAccountPeriodicTransactionViewModel viewModel;
    private ViewManagerModel viewManager;
    private SharedAccountPeriodicTransactionController controller;
    private SharedAccountPeriodicTransactionPanel panel;

    @BeforeEach
    void setUp() {
        viewModel = new SharedAccountPeriodicTransactionViewModel();
        viewManager = new ViewManagerModel();

        // Create a dummy implementation of SharedAccountPeriodicTransactionInputBoundary
        SharedAccountPeriodicTransactionInputBoundary dummyInteractor = new SharedAccountPeriodicTransactionInputBoundary() {
            @Override
            public void execute(SharedAccountPeriodicTransactionInputData inputData) {
                // Dummy implementation: Do nothing
            }
        };

        controller = new SharedAccountPeriodicTransactionController(dummyInteractor, viewModel);
        panel = new SharedAccountPeriodicTransactionPanel(viewModel, controller, viewManager);
    }

    @Test
    void testSharedAccountPeriodicTransactionPanelInitialization() {
        assertNotNull(panel);
        assertEquals("Shared Account Periodic Transaction", ((JLabel) panel.getComponent(0)).getText()); // Check title
    }

    @Test
    void testSubmitButtonActionListener() {
        JTextField amountField = (JTextField) panel.getComponent(2);
        JTextField startDateField = (JTextField) panel.getComponent(4);
        JTextField endDateField = (JTextField) panel.getComponent(6);
        JTextField descriptionField = (JTextField) panel.getComponent(8);
        JTextField userIdField = (JTextField) panel.getComponent(10);
        JComboBox<String> periodComb = (JComboBox<String>) panel.getComponent(12);
        JComboBox<String> categoryComb = (JComboBox<String>) panel.getComponent(14);

        // Set values for testing
        amountField.setText("200.00");
        startDateField.setText("2024-08-12");
        endDateField.setText("2025-08-12");
        descriptionField.setText("Gym membership");
        userIdField.setText("user456");
        periodComb.setSelectedItem("month");
        categoryComb.setSelectedItem("Fitness");

        // Find the submit button
        JPanel buttonsPanel = (JPanel) panel.getComponent(15);
        JButton submitButton = (JButton) buttonsPanel.getComponent(0);
        submitButton.doClick();
    }

    @Test
    void testCancelButtonActionListener() {
        JPanel buttonsPanel = (JPanel) panel.getComponent(15);
        JButton cancelButton = (JButton) buttonsPanel.getComponent(1);
        cancelButton.doClick();

        assertEquals("Shared Account Homepage Two", viewManager.getActiveViewName());
    }

    @Test
    void testClearFields() {
        JTextField amountField = (JTextField) panel.getComponent(2);
        JTextField startDateField = (JTextField) panel.getComponent(4);
        JTextField endDateField = (JTextField) panel.getComponent(6);
        JTextField descriptionField = (JTextField) panel.getComponent(8);
        JTextField userIdField = (JTextField) panel.getComponent(10);
        JComboBox<String> periodComb = (JComboBox<String>) panel.getComponent(12);
        JComboBox<String> categoryComb = (JComboBox<String>) panel.getComponent(14);

        // Set initial values
        amountField.setText("200.00");
        startDateField.setText("2024-08-12");
        endDateField.setText("2025-08-12");
        descriptionField.setText("Gym membership");
        userIdField.setText("user456");
        periodComb.setSelectedItem("month");
        categoryComb.setSelectedItem("Fitness");

        // Clear fields
        panel.clearFields();

        // Verify fields are cleared
        assertEquals("", amountField.getText());
        assertEquals("", startDateField.getText());
        assertEquals("", endDateField.getText());
        assertEquals("", descriptionField.getText());
        assertEquals("", userIdField.getText());
    }
}

